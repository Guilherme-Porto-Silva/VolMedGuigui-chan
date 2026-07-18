package met.vol.api.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import met.vol.api.domain.exception.TokenInvalido;
import met.vol.api.domain.model.Usuario;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service public class TokenService {

    private final String HMAC256_CHAVE = System.getenv("HMAC256_CHAVE");

    private final String OFFSET_ID = "-03:00";

    private Instant definirExpiracao () {

        return LocalDateTime.now().plusHours(2)

          .toInstant(ZoneOffset.of(OFFSET_ID));
    }

    public String gerar (Usuario u) {

        try {
            return JWT.create()
            
            .withIssuer("VolMed")// emissor do token
            
            .withSubject(u.getNomeUsuario())// receptor do token
            
            .withClaim("id", u.getId())

//            O método withClaim recebe dois parâmetros.
//            O primeiro, uma String, é o nome da propriedade armazenada.
//            O segundo é a informação armazenada pelo token.

            .withExpiresAt(definirExpiracao())// data de validade do token
            
            .sign(Algorithm.HMAC256(HMAC256_CHAVE));
        }

        catch (JWTCreationException exception) {

            throw new IllegalArgumentException(

            "Configuração de assinatura inválida. Não foi possível converter as declarações.",

            exception.getCause

            ());
        }
    }

    public String validar (String token) {

        try {
            return JWT.require(Algorithm.HMAC256(HMAC256_CHAVE))

              .withIssuer("VolMed")

                 .build()

                    .verify(token)

                      .getSubject();
        }

        catch (JWTVerificationException exception) {

            throw new TokenInvalido();
        }
    }
}