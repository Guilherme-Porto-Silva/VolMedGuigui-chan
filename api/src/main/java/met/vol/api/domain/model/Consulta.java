package met.vol.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "consultas") @Getter @Setter

@EqualsAndHashCode(of = "id") public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "paciente_id") private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "medico_id") private Medico medico;

    private LocalDateTime data;

    private Boolean cancelada = false;

    public Consulta (Paciente consultado, Medico consultante, LocalDateTime dataConsulta) {

        paciente = consultado;

        medico = consultante;

        data = dataConsulta;
    }

    public Consulta () {}
}