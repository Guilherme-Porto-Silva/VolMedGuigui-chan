package met.vol.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
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
}