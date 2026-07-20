package met.vol.api.domain.service;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.model.Consulta;
import met.vol.api.domain.model.Medico;
import met.vol.api.domain.model.Paciente;
import met.vol.api.domain.repository.ConsultaRepository;
import met.vol.api.domain.repository.MedicoRepository;
import met.vol.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service public class ConsultaService {

    @Autowired private ConsultaRepository agenda;

    @Autowired private MedicoRepository medicosCadastrados;

    @Autowired private PacienteRepository pacientesCadastrados;
    
    public Consulta agendar (DadosAgendamentoConsulta dados) {

        Optional<Medico> optionalMedico = medicosCadastrados.findById(dados.idMedico());
        
        Optional<Paciente> optionalPaciente = pacientesCadastrados.findById(dados.idPaciente());

        if (optionalPaciente.isEmpty())

            throw new IllegalArgumentException("Nenhum paciente foi cadastrado com o id " + dados.idPaciente());

        if (optionalMedico.isEmpty() && dados.necessidade() == null)

            throw new IllegalArgumentException("Não conseguimos escolher um médico aleatoriamente sem sabermos por qual especialidade o paciente demanda.");

        Medico consultante;

        if (optionalMedico.isEmpty()) consultante = agenda.escolherMedico(dados.necessidade(), dados.data());

        else consultante = optionalMedico.get();

        Paciente consultado = optionalPaciente.get();

        Consulta agendada = new Consulta(consultado, consultante, dados.data());

        agenda.save(agendada);

        System.out.println("\nAgendando uma consulta com os seguintes dados:");

        System.out.println(agendada);

        return agendada;
    }

    public void cancelar (Long id) {
        
        Optional<Consulta> optionalConsulta = agenda.findById(id);
        
        if (optionalConsulta.isPresent()) {

            Consulta apagada = optionalConsulta.get();

            apagada.setCancelada(true);

            System.out.println("\nA seguinte consulta foi cancelada:");

            System.out.println(apagada);
        }
    }
}