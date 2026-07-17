package met.vol.api.domain.DTO.medico;

import met.vol.api.domain.model.Especialidade;
import met.vol.api.domain.model.Medico;

public record DadosExibicaoMedico (Long id, String nome, String especialidade, String crm) {

    public DadosExibicaoMedico (Long id, String nome, Especialidade especialidade, String crm) {

        this(id, nome, especialidade.toString(), "CRM: " + crm);
    }

    public DadosExibicaoMedico (Medico medico) {

        this(medico.getId(), medico.getNome(), medico.getEspecialidade(), medico.getCrm());
    }
}