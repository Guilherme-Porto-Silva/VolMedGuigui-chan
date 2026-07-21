package met.vol.api.domain.repository;

import jakarta.validation.constraints.NotNull;
import met.vol.api.domain.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable p);

    boolean existsByPacienteIdAndData(Long id, LocalDateTime data);
}