package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<dadosListagemMedicos> listarMedico(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(dadosListagemMedicos::new);
        //stream -> faz o mapeamento, conversão de Medico para dadosListagemMedico(map())
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid dadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); //getReferencyById carrega os dados do banco
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        //repository.deleteById(id); Aqui apaga definitivamente do banco de dados
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
