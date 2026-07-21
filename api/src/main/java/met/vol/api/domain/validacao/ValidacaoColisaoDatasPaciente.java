package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component public class ValidacaoColisaoDatasPaciente implements ValidadorAgendamentoConsulta {

    private final PacienteRepository BANCO;

    public ValidacaoColisaoDatasPaciente(PacienteRepository banco) { BANCO = banco; }

    @Override public void testar (DadosAgendamentoConsulta agendamento) {

        if (BANCO.existsByPacienteIdAndData(agendamento.idPaciente(), agendamento.data()))

         throw new IllegalArgumentException("Cada paciente pode ser consultado apenas uma vez por dia.");
    }
}