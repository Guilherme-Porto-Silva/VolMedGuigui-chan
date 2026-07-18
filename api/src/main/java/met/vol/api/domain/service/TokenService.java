package met.vol.api.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import met.vol.api.domain.model.Usuario;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service public class TokenService {

    private Instant definirExpiracao () {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String gerar (Usuario u) {

        try {
            return JWT.create()
            
            .withIssuer("VolMed")// emissor do token
            
            .withSubject(u.getLogin())// receptor do token
            
            .withClaim("id", u.getId())// uma das informações contidas pelo token

            .withExpiresAt(definirExpiracao())// data de validade do token
            
            .sign(Algorithm.HMAC256(System.getenv("HMAC256_CHAVE")));
        }

        catch (JWTCreationException exception){

            throw new IllegalArgumentException("Configuração de assinatura inválida. Não foi possível converter as declarações.");
        }
    }
}