package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.endereco.DadosEndereco;
import org.apache.logging.log4j.message.Message;

public record DadosCadastroMedico(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(min = 8, max = 30)
        String nome,
        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "Formato do email é inválido!")
        String email,
        @NotBlank(message = "O telefone é obrigatório!")
        @Size(max = 11, message = "Número de telefone inválido")
        String telefone,
        @NotBlank(message = "O CRM é obrigatório!")
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull(message = "Os dados do endereço são obrigatórios!")
        @Valid
        DadosEndereco endereco) {
}
