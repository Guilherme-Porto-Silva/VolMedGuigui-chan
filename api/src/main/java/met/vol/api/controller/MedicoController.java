package met.vol.api.controller;

import jakarta.validation.Valid;
import met.vol.api.DTO.medico.DadosAtualizacaoMedico;
import met.vol.api.DTO.medico.DadosCadastroMedico;
import met.vol.api.DTO.medico.DadosDetalhamentoMedico;
import met.vol.api.DTO.medico.DadosExibicaoMedico;
import met.vol.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController @RequestMapping("/medicos")

public class MedicoController {

    @Autowired private MedicoService service;

    @PostMapping public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroMedico json, UriComponentsBuilder Bob) {

        var cadastrado = service.cadastrar(json);

        return ResponseEntity.created(Bob.path("/medicos/{id}")

          .buildAndExpand(cadastrado.getId()).toUri())

            .body(new DadosDetalhamentoMedico(cadastrado));
    }

    @GetMapping public ResponseEntity<Page<DadosExibicaoMedico>> ler (@PageableDefault(size = 5, sort = {"name", "email"}) Pageable p) {

        return ResponseEntity.ok(service.ler(p));
    }

//    O verbo "PUT" substitui todos os dados atuais de um recurso, pelos dados passados na requisição.
//    É uma atualização integral, uma atualização total de um recurso em apenas uma requisição.
//    O mais comum nas aplicações é utilizar o verbo PUT para requisições de atualização de recursos.

    @PutMapping public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoMedico json) {

        return ResponseEntity.ok(new DadosDetalhamentoMedico(service.atualizar(json)));
    }

//    O verbo "PATCH" aplica modificações parciais em um recurso.
//    Modifica apenas uma parte de um recurso, atualizações parciais.
//    Isso torna as opções de atualização mais flexíveis.

    @DeleteMapping("/{id}") public ResponseEntity exclusaoLogica (@PathVariable Long id) {

        service.exclusaoLogica(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") public ResponseEntity detalhar (@PathVariable Long id) {

        return ResponseEntity.ok(new DadosDetalhamentoMedico(service.ler(id)));
    }
}