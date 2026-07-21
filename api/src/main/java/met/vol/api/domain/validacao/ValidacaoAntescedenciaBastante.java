package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component public class ValidacaoAntescedenciaBastante implements ValidadorAgendamentoConsulta {

    @Override public void testar (DadosAgendamentoConsulta agendamento) {

        LocalDateTime agora = LocalDateTime.now();

        LocalDateTime dataAgendada = agendamento.data();

        var diferencaEmMinutos = Duration.between(agora, dataAgendada).toMinutes();

        if (diferencaEmMinutos < 30)

         throw new IllegalArgumentException(diferencaEmMinutos + " minutos não é tempo suficiente para preparar o espaço. Precisa haver uma diferença mínima de 30 minutos entre o agendamento e a consulta.");
    }
}