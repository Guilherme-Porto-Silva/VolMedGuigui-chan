package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component public class ValidacaoColisaoDatasMedico implements ValidadorAgendamentoConsulta {

    private final MedicoRepository BANCO;

    public ValidacaoColisaoDatasMedico(MedicoRepository banco) { BANCO = banco; }

    @Override public void testar (DadosAgendamentoConsulta agendamento) {

        if (BANCO.existsByMedicoIdAndData(agendamento.idMedico(), agendamento.data()))

         throw new IllegalArgumentException("Cada médico pode consultar apenas um paciente por dia.");
    }
}