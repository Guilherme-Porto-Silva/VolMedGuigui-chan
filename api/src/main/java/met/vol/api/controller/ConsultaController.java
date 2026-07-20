package met.vol.api.controller;

import jakarta.validation.Valid;
import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import met.vol.api.domain.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/consultas") public class ConsultaController {

    @Autowired private ConsultaService servico;

    @PostMapping public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta dados) {

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(servico.agendar(dados)));
    }

    @DeleteMapping public ResponseEntity cancelar (@RequestBody Long id) {

        servico.cancelar(id);

        return ResponseEntity.noContent().build();
    }
}