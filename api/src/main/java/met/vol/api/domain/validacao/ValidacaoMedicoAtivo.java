package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component public class ValidacaoMedicoAtivo implements ValidadorAgendamentoConsulta {

    private final MedicoRepository BANCO;

    public ValidacaoMedicoAtivo (MedicoRepository banco) { BANCO = banco; }

    @Override public void testar (DadosAgendamentoConsulta agendamento) {

        var id = agendamento.idMedico();

        if (id == null) return;// o médico não foi previamente escolhido

        var escolhido = BANCO.getReferenceById(id);

        if (!escolhido.getAtivo())

         throw new IllegalArgumentException("Lamentamos informar que " + escolhido.getNome() + " não está trabalhando aqui, no momento.");
    }
}