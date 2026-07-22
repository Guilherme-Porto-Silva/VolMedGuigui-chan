package met.vol.api.controller;

import met.vol.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import met.vol.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import met.vol.api.domain.model.Consulta;
import met.vol.api.domain.model.Especialidade;
import met.vol.api.domain.service.ConsultaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest @AutoConfigureMockMvc @AutoConfigureJsonTesters

class ConsultaControllerTest {

    @Autowired private MockMvc duble;

    @Autowired private JacksonTester<DadosAgendamentoConsulta> jsonRecebido;

    @Autowired private JacksonTester<DadosDetalhamentoConsulta> jsonDevolvido;

    @MockitoBean private ConsultaService servico;

    @Test
    @DisplayName("Enviarei informações inválidas. A API devia devolver o código 400 do protocólo de transferência de hiper texto seguro.")
    @WithMockUser// burlar autentificação durante testes
    void agendarBeanValidation () throws Exception {

        var respostaAPI = duble.perform(post("/consultas")).andReturn().getResponse();

        var codigoHTTP = respostaAPI.getStatus();

        Assertions.assertThat(codigoHTTP).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Enviarei informações válidas. A API devia devolver o código 200 do protocólo de transferência de hiper texto seguro.")
    @WithMockUser// burlar autentificação durante testes
    void agendarCorretamente () throws Exception {

        LocalDateTime agora = LocalDateTime.now().plusHours(2);

        DadosAgendamentoConsulta dadosRecebidos = new DadosAgendamentoConsulta(2l, 3l, agora, Especialidade.CARDIOLOGIA);

        var dadosRecebidosJSON = jsonRecebido.write(dadosRecebidos).getJson();

        var respostaAPI = duble.perform(post("/consultas").contentType(MediaType.APPLICATION_JSON).content(dadosRecebidosJSON)).andReturn().getResponse();

        var codigoHTTP = respostaAPI.getStatus();

        Assertions.assertThat(codigoHTTP).isEqualTo(HttpStatus.OK.value());



//        var detalhamentoEsperado = new DadosDetalhamentoConsulta(null, 2l, 3l, agora);
//
//        var jsonEsperado = jsonDevolvido.write(detalhamentoEsperado).getJson();
//
//        Consulta retornoServico = new Consulta(dadosRecebidos);
//
//        when(servico.agendar(any())).thenReturn(retornoServico);
//
//        Assertions.assertThat(respostaAPI.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    void cancelar () {
    }
}