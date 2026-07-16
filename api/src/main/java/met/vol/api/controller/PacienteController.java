package met.vol.api.controller;

import jakarta.validation.Valid;
import met.vol.api.DTO.medico.DadosDetalhamentoMedico;
import met.vol.api.DTO.paciente.DadosAtualizacaoPaciente;
import met.vol.api.DTO.paciente.DadosCadastroPaciente;
import met.vol.api.DTO.paciente.DadosDetalhamentoPaciente;
import met.vol.api.DTO.paciente.DadosListagemPaciente;
import met.vol.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController @RequestMapping("/pacientes")

public class PacienteController {

    @Autowired private PacienteService service;

    @PostMapping public ResponseEntity cadastrar (@RequestBody DadosCadastroPaciente json, UriComponentsBuilder Bob) {

        var cadastrado = service.cadastrar(json);

        return ResponseEntity.created(Bob.path("/pacientes/{id}")

          .buildAndExpand(cadastrado.getId()).toUri())

            .body(new DadosDetalhamentoPaciente(cadastrado));
    }

    @GetMapping public Page<DadosListagemPaciente> ler (@PageableDefault(size = 5) Pageable p) {

        return service.ler(p);
    }

    @PutMapping public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoPaciente dados) {

        service.atualizar(dados);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") public ResponseEntity exclusaoLogica (@PathVariable Long id) {

        service.exclusaoLogica(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") public ResponseEntity detalhar (@PathVariable Long id) {

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(service.ler(id)));
    }
}