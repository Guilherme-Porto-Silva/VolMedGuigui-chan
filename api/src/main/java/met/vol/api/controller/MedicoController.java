package met.vol.api.controller;

import jakarta.validation.Valid;
import met.vol.api.DTO.DadosAtualizacaoMedico;
import met.vol.api.DTO.DadosCadastroMedico;
import met.vol.api.DTO.DadosExibicaoMedico;
import met.vol.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/medicos")

public class MedicoController {

    @Autowired private MedicoService service;

    @PostMapping public void cadastrar (@RequestBody @Valid DadosCadastroMedico json) {

        service.cadastrar(json);
    }

    @GetMapping public Page<DadosExibicaoMedico> ler (@PageableDefault(size = 5, sort = {"name", "email"}) Pageable p) {

        return service.ler(p);
    }

//    O verbo "PUT" substitui todos os dados atuais de um recurso, pelos dados passados na requisição.
//    É uma atualização integral, uma atualização total de um recurso em apenas uma requisição.
//    O mais comum nas aplicações é utilizar o verbo PUT para requisições de atualização de recursos.

    @PutMapping public void atualizar (@RequestBody @Valid DadosAtualizacaoMedico json) {

        service.atualizar(json);
    }

//    O verbo "PATCH" aplica modificações parciais em um recurso.
//    Modifica apenas uma parte de um recurso, atualizações parciais.
//    Isso torna as opções de atualização mais flexíveis.

    @DeleteMapping("/{id}") public void exclusaoLogica (@PathVariable Long id) {

        service.exclusaoLogica(id);
    }
}