package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component public class ValidacaoHorarioFuncionamento implements ValidadorAgendamentoConsulta {

    private final byte HORARIO_ABERTURA_CLINICA = 7;

    private final byte HORARIO_ULTIMA_CONSULTA = 18;

    @Override public void testar (DadosAgendamentoConsulta agendamento) {

        LocalDateTime dataAgendada = agendamento.data();

        if (dataAgendada.getDayOfWeek().equals(DayOfWeek.SUNDAY))

            throw new IllegalArgumentException("Não consultamos aos domingos.");

        if (dataAgendada.getHour() < HORARIO_ABERTURA_CLINICA)

            throw new IllegalArgumentException("A clínica só é aberta às " + HORARIO_ABERTURA_CLINICA + " horas.");

        if (dataAgendada.getHour() > HORARIO_ULTIMA_CONSULTA)

            throw new IllegalArgumentException("O horário mais tarde em que consultas podem ser marcadas é " + HORARIO_ULTIMA_CONSULTA + " horas.");
    }
}