package met.vol.api.infra.documentacao;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class SpringDocConfig {

    private final String DESCICAO_API = "API Rest da aplicação VollMed, contendo as funcionalidades de C.R.U.D. de médicos e de pacientes, além de agendamento e cancelamento de consultas.";

    private final String EMAIL = "backend@voll.med";

    private final String NOME_API = "Time Backend";

    private final String ENDERECO_LICENCA = "http://voll.med/api/licenca";

    private final String NOME_LICENCA = "Apache 2.0";

    private final String TITULO_API = "VollMed API";

    @Bean public OpenAPI customOpenAPI () {

        OpenAPI retorno = new OpenAPI();

        Components componente = new Components();

        SecurityScheme esquemaSeguranca = new SecurityScheme();

        Info informacoes = new Info();

        Contact contato = new Contact();

        License licenca = new License();

        licenca.name(NOME_LICENCA).url(ENDERECO_LICENCA);

        contato.name(NOME_API).email(EMAIL);

        informacoes.title(TITULO_API).description(DESCICAO_API).contact(contato).license(licenca);

        esquemaSeguranca.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");

        componente.addSecuritySchemes("bearer-key", esquemaSeguranca);//@SecurityRequirement(name = "bearer-key")

        retorno.info(informacoes).components(componente);

        return retorno;
    }
}