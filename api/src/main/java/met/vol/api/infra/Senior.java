package met.vol.api.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import met.vol.api.domain.exception.TokenInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice public class Senior {

    private record DadosErroValidacao (String erro, String mensagem) {}

    @ExceptionHandler(EntityNotFoundException.class) public ResponseEntity

        recursoNaoEncontrado () {

            return ResponseEntity.notFound().build();
        }

    @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity

        requisicaoMalFeita (MethodArgumentNotValidException e) {

            return ResponseEntity.badRequest().body(e.getFieldErrors().stream()

              .map(erro -> new DadosErroValidacao(erro.getField(), erro.getDefaultMessage

                 ())).toList());
        }

    @ExceptionHandler(TokenInvalido.class) public ResponseEntity

      desvalidacaoDeToken (TokenInvalido t) {

        return ResponseEntity.badRequest().body(t.getMessage());
    }

    @ExceptionHandler(ServletException.class) public ResponseEntity

        nemTentei () {

            return ResponseEntity.badRequest().body(

                    "Se uma requisição POST com multipart/form-data vier corrompida, violar os limites rígidos de tamanho do Tomcat antes mesmo do Spring validar os parâmetros, ou se o container falhar ao abrir o stream temporário do arquivo, a API de Servlet subjacente pode lançar uma ServletException diretamente."
            );
        }

    @ExceptionHandler(HttpMessageNotReadableException.class) public ResponseEntity

        mensagemIlegivel (HttpMessageNotReadableException ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    @ExceptionHandler(BadCredentialsException.class) public ResponseEntity

        credenciaisRuins () {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

    @ExceptionHandler(AuthenticationException.class) public ResponseEntity

        problemaNaAutenticacao () {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }

    @ExceptionHandler(AccessDeniedException.class) public ResponseEntity

        negarAcesso () {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
        }

    @ExceptionHandler(Exception.class) public ResponseEntity

        avisarErroInterno (Exception e) {

            return ResponseEntity.internalServerError()

                .body(e.getMessage());
        }
}