package met.vol.api.domain.DTO.paciente;

import jakarta.validation.constraints.NotNull;
import met.vol.api.domain.model.Paciente;

public record DadosAtualizacaoPaciente (@NotNull Long id, String nome, String telefone) {

    public DadosAtualizacaoPaciente (Paciente p) {

        this(p.getId(), p.getNome(), p.getTelefone());
    }
}