package met.vol.api.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import met.vol.api.domain.model.Endereco;

public record DadosEndereco(

        @NotBlank String logradouro,

        @NotBlank String bairro,

        @NotBlank @Pattern(regexp = "\\d{8}") String cep,

        @NotBlank String cidade,

        @NotBlank String uf,

        short numero,

        String complemento
) {
    public DadosEndereco (String logradouro, String bairro, String cep, String cidade, String uf, short numero) {

        this(logradouro, bairro, cep, cidade, uf, numero, null);
    }

    public DadosEndereco (String logradouro, String bairro, String cep, String cidade, String uf, String complemento) {

        this(logradouro, bairro, cep, cidade, uf, (short) -1, complemento);
    }

    public DadosEndereco (String logradouro, String bairro, String cep, String cidade, String uf) {

        this(logradouro, bairro, cep, cidade, uf, (short) -1, null);
    }

    public DadosEndereco (Endereco endereco) {

        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getNumero(), endereco.getComplemento());
    }
}