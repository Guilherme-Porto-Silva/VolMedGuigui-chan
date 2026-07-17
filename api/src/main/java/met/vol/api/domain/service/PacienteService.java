package met.vol.api.domain.service;

import met.vol.api.domain.DTO.paciente.DadosAtualizacaoPaciente;
import met.vol.api.domain.DTO.paciente.DadosCadastroPaciente;
import met.vol.api.domain.DTO.paciente.DadosListagemPaciente;
import met.vol.api.domain.model.Paciente;
import met.vol.api.domain.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class PacienteService {

    private PacienteRepository BANCO;

    public PacienteService (PacienteRepository banco) { BANCO = banco; }

    public Paciente cadastrar (DadosCadastroPaciente json) {

        Paciente paciente = new Paciente(json);

        BANCO.saveAndFlush(paciente);

        System.out.println("\nFoi guardado, no banco de dados, um paciente cadastrado da seguinte maneira:");

        System.out.println(paciente);

        return paciente;
    }

    public Page<DadosListagemPaciente> ler (Pageable p) {

        Page<DadosListagemPaciente> pacientes = BANCO.findAllByAtivoTrue(p).map(DadosListagemPaciente::new);

        System.out.println("\nFornecendo uma lista com os seguintes dados:");

        pacientes.forEach(System.out::println);

        return pacientes;
    }

    public Paciente ler (Long id) {

        var paciente = BANCO.getReferenceById(id);

        System.out.println("\nFornecendo o paciente " + paciente.getNome());

        return paciente;
    }

    @Transactional public void atualizar (DadosAtualizacaoPaciente dados) {

        String novoNome = dados.nome();

        String novoTelefone = dados.telefone();

        var paciente = BANCO.getReferenceById(dados.id());

        if (novoNome != null && !novoNome.isBlank()) {

            System.out.println("\nO nome do " + paciente.getNome() + " foi mudado para " + novoNome);

            paciente.setNome(novoNome);
        }

        if (novoNome != null && !novoTelefone.isBlank()) {

            System.out.println("\nO telefone do " + paciente.getNome() + " foi mudado de " + paciente.getTelefone() + " para " + novoTelefone);

            paciente.setTelefone(novoTelefone);
        }
    }

    public void exclusaoLogica (Long id) {

        BANCO.getReferenceById(id).setAtivo(false);

        System.out.println("\nO paciente com id " + id + " foi desativado.");
    }
}