package met.vol.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import met.vol.api.DTO.DadosEndereco;

@Getter @Setter @Embeddable @AllArgsConstructor public class Endereco {

    private String logradouro;

    private String bairro;

    private String cep;

    private String cidade;

    private String uf;

    private short numero;

    private String complemento;

    public Endereco () {}

    public Endereco (DadosEndereco dados) {

        logradouro = dados.logradouro();

        bairro = dados.bairro();

        cep = dados.cep();

        cidade = dados.cidade();

        uf = dados.uf();

        numero = dados.numero();

        complemento = dados.complemento();
    }
}