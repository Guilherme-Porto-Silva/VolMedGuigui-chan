package met.vol.api.infra.filtros;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import met.vol.api.domain.exception.TokenInvalido;
import met.vol.api.domain.repository.UsuarioRepository;
import met.vol.api.domain.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService TS;

    private final UsuarioRepository BANCO;

    public SecurityFilter (TokenService tokenService, UsuarioRepository banco) {

        TS = tokenService;

        BANCO = banco;
    }

    private String getToken (HttpServletRequest requisicao) {

        var cabecalho = requisicao.getHeader("Authorization");

        if (cabecalho != null) return cabecalho.replace("Bearer ", "");

        return null;
    }

    @Override protected void doFilterInternal

      (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)

        throws ServletException, IOException {

          var token = getToken(request);

          if (token != null) {

              var nomeUsuario = TS.validar(token);

              var usuario = BANCO.findByLogin(nomeUsuario);

              var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

              SecurityContextHolder.getContext().setAuthentication(autenticacao);

              System.out.println('\n' + nomeUsuario + " fez uma requisição.");

              filterChain.doFilter(request, response);
          }

          else throw new TokenInvalido();
    }
}