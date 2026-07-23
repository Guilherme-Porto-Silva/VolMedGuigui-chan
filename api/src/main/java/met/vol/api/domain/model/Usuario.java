package met.vol.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import met.vol.api.domain.DTO.DadosDoLogin;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Getter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity @Table(name = "usuarios") public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String nomeUsuario;

    private String senha;

    public Usuario (DadosDoLogin dados) {

        nomeUsuario = dados.usuario();

        senha = dados.senha();
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities () {

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override public @Nullable String getPassword () {

        return senha;
    }

    @Override public String getUsername () {

        return nomeUsuario;
    }
}