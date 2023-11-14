package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder) {
        //Código 201 no cadastro
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<dadosListagemMedicos>> listarMedico(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        //Código 200 no listagem
        var page = repository.findAllByAtivoTrue(paginacao).map(dadosListagemMedicos::new);
        //stream -> faz o mapeamento, conversão de Medico para dadosListagemMedico(map())
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid dadosAtualizacaoMedico dados) {
        //Código 200 para o atualizar
        var medico = repository.getReferenceById(dados.id()); //getReferencyById carrega os dados do banco
        medico.atualizarInformacoes(dados);
        //É bom devolver os detalhes do medico que foi atualizado!
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        //Não é recomendado devolver e receber entidades JPA no Controller
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id) {
        //Código 204 no excluir
        //repository.deleteById(id); Aqui apaga definitivamente do banco de dados
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
}