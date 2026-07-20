package met.vol.api.domain.DTO.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import met.vol.api.domain.model.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long idMedico,

        @NotNull(message = "O paciente precisa ser IDentificado, para o agendamento de uma consulta.") Long idPaciente,

        @NotNull(message = "A data em que a consulta ocorre precisa ser IDentificada.")

        @Future(message = "Consultas não podem ser agendadas no passado.")

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data,

        Especialidade necessidade) {
}