package met.vol.api.domain.validacao;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    public void testar (DadosAgendamentoConsulta agendamento);
}