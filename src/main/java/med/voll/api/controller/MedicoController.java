package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.dadosListagemMedicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Page<dadosListagemMedicos> listarMedico(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(dadosListagemMedicos::new);
        //stream -> faz o mapeamento, conversão de Medico para dadosListagemMedico(map())
    }
}
