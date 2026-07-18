package met.vol.api.domain.exception;

public class TokenInvalido extends IllegalArgumentException {

    public TokenInvalido (String message) {
        super(message);
    }

    public TokenInvalido () {

        super("Confira a data de validade do token (ou se ele foi enviado corretamente).");
    }
}