package met.vol.api.domain.DTO.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import met.vol.api.domain.model.Medico;
import met.vol.api.domain.service.Tecnico;

public record DadosAtualizacaoMedico (@NotNull Long id,

    @NotBlank(message = "Qual é o nome do médico PRECISA ser sabido, para atualizá-lo.") String nome,

       @NotBlank(message = "É necessário conhecer o telefone de um médico, para atualizá-lo.") String telefone,

           @NotBlank(message = "Não é possível atualizar o email de um médico sem conhecer o novo endereço que será inserido.")

              @Email(message = "O formato do email PRECISA ser usuario@dominio.serviço (ex: guilherme@volmed.com)") String email) {

                public DadosAtualizacaoMedico (Medico medico) {

                    this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEmail());
                }

                public DadosAtualizacaoMedico (Long id, String nome, String telefone, String email) {

                    this.id = id;

                    this.nome = Tecnico.titulacao(nome);

                    this.telefone = telefone;

                    this.email = email;
                }
}