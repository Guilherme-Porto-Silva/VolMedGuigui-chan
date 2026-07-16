package met.vol.api.DTO.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import met.vol.api.model.Medico;
import met.vol.api.service.Tecnico;

public record DadosAtualizacaoMedico (@NotNull Long id, @NotBlank String nome, @NotBlank String telefone, @NotBlank @Email String email) {

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