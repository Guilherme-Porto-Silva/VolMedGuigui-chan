package met.vol.api.domain.repository;

import met.vol.api.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue (Pageable p);

    boolean existsByMedicoIdAndData(Long id, LocalDateTime data);
}