package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;

public record dadosCadastroPaciente(
        @NotBlank @Size(min = 8, max = 30)
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank @Size(max = 11)
        String telefone,
        @NotBlank @Size(min = 11, max = 14)
        String cpf,
        @NotNull @Valid
        DadosEndereco endereco) {
}
