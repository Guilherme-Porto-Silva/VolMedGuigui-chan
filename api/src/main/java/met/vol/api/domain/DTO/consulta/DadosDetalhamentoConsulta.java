package met.vol.api.domain.DTO.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import met.vol.api.domain.model.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta (Long id, Long idPaciente, Long idMedico, @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {

    public DadosDetalhamentoConsulta (Consulta agendada) {

        this(agendada.getId(), agendada.getPaciente().getId(),

        agendada.getMedico().getId(), agendada.getData());
    }
}