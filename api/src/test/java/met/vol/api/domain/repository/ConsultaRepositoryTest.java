package met.vol.api.domain.repository;

import met.vol.api.domain.model.Consulta;
import met.vol.api.domain.model.Especialidade;
import met.vol.api.domain.model.Medico;
import met.vol.api.domain.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@ActiveProfiles("teste")

class ConsultaRepositoryTest {

    @Autowired private ConsultaRepository agenda;

    @Autowired private TestEntityManager tem;

    private Medico criarMedico (String nome, String email, String crm, Especialidade especialidade) {

        Medico medico = new Medico(nome, email, crm, especialidade);

        tem.persist(medico);// insersão temporária

        return medico;
    }

    private Paciente criarPaciente (String nome, String email, String cpf) {

        Paciente paciente = new Paciente(nome, email, cpf);

        tem.persist(paciente);// insersão temporária

        return paciente;
    }

    private Consulta criarConsulta (Medico consultante, Paciente consultado, LocalDateTime data) {

        Consulta consulta = new Consulta(consultado, consultante, data);

        tem.persist(consulta);// insersão temporária

        return consulta;
    }

    @Test
    @DisplayName("Devia devolver null quando o único médico disponível não está livre na data.")
    void escolherMedicoDandoErrado () {

//        ARRANGE

        LocalDateTime data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        Medico consultante = criarMedico("Igor Porto e Silva", "igorportosilva@volmed.org", "123456", Especialidade.CARDIOLOGIA);

        Paciente consultado = criarPaciente("Guilherme Porto e Silva", "guiportosilva2@gmail.com", "10037129678");

//        ACT

        criarConsulta(consultante, consultado, data);// marco uma consulta com o médico

        Medico escolhido = agenda.escolherMedico(Especialidade.CARDIOLOGIA, data);// tento marcar outra consulta com o mesmo médico na mesma data

//        ASSERT

        Assertions.assertThat(escolhido).isNull();// olho se ele falou que não pode
    }

    @Test
    @DisplayName("Devia devolver Médico quando ele estiver disponível na data.")
    void escolherMedicoDandoCerto () {

//        ARRANGE

        LocalDateTime data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        Medico consultante = criarMedico("Igor Porto e Silva", "igorportosilva@volmed.org", "123456", Especialidade.CARDIOLOGIA);

//        ACT

        Medico escolhido = agenda.escolherMedico(Especialidade.CARDIOLOGIA, data);

//        ASSERT

        Assertions.assertThat(escolhido).isEqualTo(consultante);// devolveu o mesmo médico que eu tinha cadastrado
    }
}