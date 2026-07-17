package met.vol.api.controller;

import jakarta.validation.Valid;
import met.vol.api.domain.DTO.DadosDoLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/login") public class IdentificadorController {

    @Autowired private AuthenticationManager A;

    @PostMapping public ResponseEntity logar (@RequestBody @Valid DadosDoLogin dados) {

        var token = new UsernamePasswordAuthenticationToken(dados.usuario(), dados.senha());

        var autenticacao = A.authenticate(token);

        return ResponseEntity.ok("Consegui autenticar " + dados.usuario() + '.');
    }
}