package met.vol.api.domain.DTO.medico;

import met.vol.api.domain.DTO.DadosEndereco;
import met.vol.api.domain.model.Especialidade;
import met.vol.api.domain.model.Medico;

public record DadosDetalhamentoMedico (String nome, String email, String crm, String telefone, Especialidade especialidade, DadosEndereco endereco) {

    public DadosDetalhamentoMedico (Medico medico) {

        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), new DadosEndereco(medico.getEndereco()));
    }
}