package met.vol.api.DTO;

import jakarta.validation.constraints.NotNull;
import met.vol.api.model.Paciente;

public record DadosAtualizacaoPaciente (@NotNull Long id, String nome, String telefone) {

    public DadosAtualizacaoPaciente (Paciente p) {

        this(p.getId(), p.getNome(), p.getTelefone());
    }
}