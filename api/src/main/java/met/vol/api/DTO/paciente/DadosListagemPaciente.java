package met.vol.api.DTO.paciente;

import met.vol.api.model.Paciente;

public record DadosListagemPaciente (String nome, String email, String cpf) {

    public DadosListagemPaciente (Paciente paciente) {

        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}