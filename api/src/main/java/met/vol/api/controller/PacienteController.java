package met.vol.api.controller;

import jakarta.validation.Valid;
import met.vol.api.DTO.DadosAtualizacaoPaciente;
import met.vol.api.DTO.DadosCadastroPaciente;
import met.vol.api.DTO.DadosListagemPaciente;
import met.vol.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/pacientes")

public class PacienteController {

    @Autowired private PacienteService service;

    @PostMapping public void cadastrar (@RequestBody DadosCadastroPaciente json) {

        service.cadastrar(json);
    }

    @GetMapping public Page<DadosListagemPaciente> ler (@PageableDefault(size = 5) Pageable p) {

        return service.ler(p);
    }

    @PutMapping public void atualizar (@RequestBody @Valid DadosAtualizacaoPaciente dados) {

        service.atualizar(dados);
    }

    @DeleteMapping("/{id}") public void exclusaoLogica (@PathVariable Long id) {

        service.exclusaoLogica(id);
    }
}