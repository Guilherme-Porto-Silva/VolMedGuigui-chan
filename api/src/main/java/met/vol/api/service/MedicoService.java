package met.vol.api.service;

import jakarta.validation.Valid;
import met.vol.api.DTO.DadosAtualizacaoMedico;
import met.vol.api.DTO.DadosCadastroMedico;
import met.vol.api.DTO.DadosExibicaoMedico;
import met.vol.api.model.Medico;
import met.vol.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MedicoService {

    private MedicoRepository BANCO;

    public MedicoService (MedicoRepository banco) {
        
        BANCO = banco;
    }

    @Transactional public void cadastrar (DadosCadastroMedico json) {

        Medico medico = new Medico(json);

        // podemos forçar o Hibernate a fazer o INSERT imediatamente,
        // usando o comando saveAndFlush() ao invés de apenas save()

        BANCO.saveAndFlush(medico);

        System.out.println("\nFoi guardado, no banco de dados, um médico cadastrado da seguinte maneira:");

        System.out.println(medico);
    }

    public Page<DadosExibicaoMedico> ler (Pageable p) {

        Page<DadosExibicaoMedico> medicos = BANCO.findAllByAtivoTrue(p).map(DadosExibicaoMedico::new);

        System.out.println("\nFornecendo uma lista com os seguintes dados:");

        medicos.forEach(System.out::println);

        return medicos;
    }

    @Transactional public void atualizar (DadosAtualizacaoMedico json) {

        Medico atualizado = BANCO.getReferenceById(json.id());

        String novoNome = json.nome();

        String novoTelefone = json.telefone();

        String novoEmail = json.email();

        if (novoNome != null && !novoNome.isBlank()) {

            System.out.println("\nMudando o nome do " + atualizado.getNome() + " para " + novoNome);

            atualizado.setNome(novoNome);
        }

        if (novoNome != null && !novoTelefone.isBlank()) {

            System.out.println("\nMudando o telefone do " + atualizado.getNome() + " para " + novoTelefone);

            atualizado.setTelefone(novoTelefone);
        }

        if (novoNome != null && !novoEmail.isBlank()) {

            System.out.println("\nMudando o email do " + atualizado.getNome() + " para " + novoEmail);

            atualizado.setEmail(novoEmail);
        }
    }

    @Transactional public void exclusaoFisica (Long id) {

        BANCO.deleteById(id);

        System.out.println("\nO médico que tinha o id " + id + " não está mais no banco de dados.");
    }

    @Transactional public void exclusaoLogica (Long id) {

        BANCO.getReferenceById(id).setAtivo(false);

        System.out.println("\nO médico com id " + id + " foi desativado.");
    }
}