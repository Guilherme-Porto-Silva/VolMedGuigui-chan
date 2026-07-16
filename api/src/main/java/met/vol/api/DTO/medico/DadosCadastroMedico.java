package met.vol.api.DTO.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import met.vol.api.DTO.DadosEndereco;
import met.vol.api.model.Especialidade;
import met.vol.api.model.Medico;

public record DadosCadastroMedico (
        
        @NotBlank String nome,

        @NotBlank @Email String email,

        @NotBlank @Pattern(regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$") String telefone,

        @NotNull @Pattern(regexp = "\\d{4,6}") String crm,// buga, se eu usar long

        @NotNull Especialidade especialidade,

        @NotNull @Valid DadosEndereco endereco
) {

    public DadosCadastroMedico (String nome, String email, String telefone, String crm, String especialidade, DadosEndereco esteEndereco) {

        Especialidade especialidadeDesteMedico = Especialidade.parseEspecialidade(especialidade);

        this(nome, email, telefone, crm, especialidadeDesteMedico, esteEndereco);
    }

    public DadosCadastroMedico (String nome, String email, String telefone, String crm, String especialidade,

        String logradouro, String bairro, String cep, String cidade, String uf, short numero, String complemento) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf, numero, complemento);

        this(nome, email, telefone, crm, especialidade, esteEndereco);
    }

    public DadosCadastroMedico (String nome, String email, String telefone, String crm, String especialidade,

        String logradouro, String bairro, String cep, String cidade, String uf, String complemento) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf, complemento);

        this(nome, email, telefone, crm, especialidade, esteEndereco);
    }

    public DadosCadastroMedico (String nome, String email, String telefone, String crm, String especialidade,

        String logradouro, String bairro, String cep, String cidade, String uf, short numero) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf, numero);

        this(nome, email, telefone, crm, especialidade, esteEndereco);
    }

    public DadosCadastroMedico (String nome, String email, String telefone, String crm, String especialidade,

        String logradouro, String bairro, String cep, String cidade, String uf) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf);

        this(nome, email, telefone, crm, especialidade, esteEndereco);
    }

    public DadosCadastroMedico (Medico medico) {

        DadosEndereco esteEndereco = new DadosEndereco(medico.getEndereco());

        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), esteEndereco);
    }

    @Override public String toString () {

        return  "Nome: " + nome + '\n' +
                "Email: " + email + '\n' +
                "Número de registro profissional no Conselho Regional de Medicina: " + crm + '\n' +
                "Especialidade: " + especialidade;
    }
}