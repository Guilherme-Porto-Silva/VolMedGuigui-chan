package met.vol.api.domain.service;

import met.vol.api.domain.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service public class IdentificadorService implements UserDetailsService {

    private UsuarioRepository BANCO;

    public IdentificadorService (UsuarioRepository banco) { BANCO = banco; }

    @Override public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        return BANCO.findByLogin(username);
    }
}