package med.voll.api.medico;

public record dadosListagemMedicos(String nome, String email, String crm, Especialidade especialidade) {

    public dadosListagemMedicos(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
