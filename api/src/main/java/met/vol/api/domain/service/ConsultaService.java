package met.vol.api.domain.service;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.model.Consulta;
import met.vol.api.domain.model.Medico;
import met.vol.api.domain.model.Paciente;
import met.vol.api.domain.repository.ConsultaRepository;
import met.vol.api.domain.repository.MedicoRepository;
import met.vol.api.domain.repository.PacienteRepository;
import met.vol.api.domain.validacao.ValidadorAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service public class ConsultaService {

    private final ConsultaRepository AGENDA;

    private final MedicoRepository MEDICOS_CADASTRADOS;

    private final PacienteRepository PACIENTES_CADASTRADOS;

    @Autowired private List<ValidadorAgendamentoConsulta> validadores;

    public ConsultaService (ConsultaRepository agenda, MedicoRepository medicosCadastrados, PacienteRepository pacientesCadastrados) {

        AGENDA = agenda;

        MEDICOS_CADASTRADOS = medicosCadastrados;

        PACIENTES_CADASTRADOS = pacientesCadastrados;
    }

    public Consulta agendar (DadosAgendamentoConsulta dados) {

        Optional<Medico> optionalMedico = MEDICOS_CADASTRADOS.findById(dados.idMedico());
        
        Optional<Paciente> optionalPaciente = PACIENTES_CADASTRADOS.findById(dados.idPaciente());

        if (optionalPaciente.isEmpty())

            throw new IllegalArgumentException("Nenhum paciente foi cadastrado com o id " + dados.idPaciente());

        if (optionalMedico.isEmpty() && dados.necessidade() == null)

            throw new IllegalArgumentException("Não conseguimos escolher um médico aleatoriamente sem sabermos por qual especialidade o paciente demanda.");

        validadores.forEach(v -> v.testar(dados));

        Medico consultante = optionalMedico.orElseGet(() -> AGENDA.escolherMedico(dados.necessidade(), dados.data()));

        if (consultante == null) throw new IllegalArgumentException("Nenhum médico está livre nesse horário.");

        Paciente consultado = optionalPaciente.get();

        Consulta agendada = new Consulta(consultado, consultante, dados.data());

        AGENDA.save(agendada);

        System.out.println("\nAgendando uma consulta com os seguintes dados:");

        System.out.println(agendada);

        return agendada;
    }

    @Transactional public void cancelar (Long id) {
        
        Optional<Consulta> optionalConsulta = AGENDA.findById(id);
        
        if (optionalConsulta.isPresent()) {

            Consulta apagada = optionalConsulta.get();

            apagada.setCancelada(true);

            System.out.println("\nA seguinte consulta foi cancelada:");

            System.out.println(apagada);
        }

        else throw new IllegalArgumentException("Nenhuma consulta está IDentificada como " + id);
    }
}