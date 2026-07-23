package met.vol.api.domain.repository;

import met.vol.api.domain.model.Consulta;
import met.vol.api.domain.model.Especialidade;
import met.vol.api.domain.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("select m from Medico m where m.ativo = true and m.especialidade = :especialidade and m.id not in" +
    "(select c.medico.id from Consulta c where c.data = :data) order by rand() limit 1")
    Medico escolherMedico (Especialidade especialidade, LocalDateTime data);

    boolean existsByMedicoIdAndData(Long medicoId, LocalDateTime data);

    boolean existsByPacienteIdAndData(Long pacienteId, LocalDateTime data);
}