package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component public class ValidacaoPacienteAtivo implements ValidadorAgendamentoConsulta {

    private final PacienteRepository BANCO;

    public ValidacaoPacienteAtivo (PacienteRepository banco) { BANCO = banco; }

    @Override public void testar (DadosAgendamentoConsulta agendamento) {

        var id = agendamento.idPaciente();

        if (id == null)

         throw new IllegalArgumentException("O paciente a ser consultado precisa ser previamente IDentificado.");

        var consultado = BANCO.getReferenceById(id);

        if (!consultado.getAtivo())

         throw new IllegalArgumentException(consultado.getNome() + " não está ativo.");
    }
}