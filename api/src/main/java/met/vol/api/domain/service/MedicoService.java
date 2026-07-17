package met.vol.api.domain.service;

import met.vol.api.domain.DTO.medico.DadosAtualizacaoMedico;
import met.vol.api.domain.DTO.medico.DadosCadastroMedico;
import met.vol.api.domain.DTO.medico.DadosExibicaoMedico;
import met.vol.api.domain.model.Medico;
import met.vol.api.domain.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service public class MedicoService {

    private MedicoRepository BANCO;

    public MedicoService (MedicoRepository banco) {
        
        BANCO = banco;
    }

    @Transactional public Medico cadastrar (DadosCadastroMedico json) {

        Medico medico = new Medico(json);

        // podemos forçar o Hibernate a fazer o INSERT imediatamente,
        // usando o comando saveAndFlush() ao invés de apenas save()

        BANCO.saveAndFlush(medico);

        System.out.println("\nFoi guardado, no banco de dados, um médico cadastrado da seguinte maneira:");

        System.out.println(medico);

        return medico;
    }

    public Page<DadosExibicaoMedico> ler (Pageable p) {

        Page<DadosExibicaoMedico> medicos = BANCO.findAllByAtivoTrue(p).map(DadosExibicaoMedico::new);

        System.out.println("\nFornecendo uma lista com os seguintes dados:");

        medicos.forEach(System.out::println);

        return medicos;
    }

    public Medico ler (Long id) {

        var medico = BANCO.getReferenceById(id);

        System.out.println("\nFornecendo o médico " + medico.getNome());

        return medico;
    }

    @Transactional public Medico atualizar (DadosAtualizacaoMedico json) {

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

        return atualizado;
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