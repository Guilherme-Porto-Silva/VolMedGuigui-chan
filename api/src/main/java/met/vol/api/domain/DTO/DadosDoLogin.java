package met.vol.api.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosDoLogin (@NotBlank String usuario, @NotBlank String senha) {
}