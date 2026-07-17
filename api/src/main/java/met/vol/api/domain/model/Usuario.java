package met.vol.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity(name = "Usuario") @Table(name = "usuarios") public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String login;

    private String senha;
}