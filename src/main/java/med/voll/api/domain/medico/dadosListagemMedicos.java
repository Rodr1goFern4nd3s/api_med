package med.voll.api.domain.medico;

public record dadosListagemMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public dadosListagemMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
