package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank
        @Size(min = 8, max = 30)
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(max = 11)
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        Especialidade especialidade,
        @NotBlank
        @Valid
        DadosEndereco endereco) {
}
