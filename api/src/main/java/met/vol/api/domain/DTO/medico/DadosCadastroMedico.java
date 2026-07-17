package met.vol.api.domain.DTO.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import met.vol.api.domain.DTO.DadosEndereco;
import met.vol.api.domain.model.Especialidade;
import met.vol.api.domain.model.Medico;

public record DadosCadastroMedico (
        
        @NotBlank(message = "Como um médico será cadastrado no sistema sem que seu nome seja informado?") String nome,

        @NotBlank(message = "O email do médico precisa ser informado para cadastrá-lo no sistema.")

        @Email(message = "O email do médico precisa seguir o formato 'usuario@dominio.serviço' para cadastrá-lo no sistema.") String email,

        @NotBlank(message = "O telefone do médico precisa ser informado para cadastrá-lo no sistema.")

        @Pattern(regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$", message = "O telefone do médico precisa seguir o formato '^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$' para cadastrá-lo no sistema.")

        String telefone,

        @NotNull(message = "Médicos sem comprovante de registro médico não podem se cadastrar nesta API. Questão de segurança pública.")

        @Pattern(regexp = "\\d{4,6}", message = "O comprovante de registro médico do médico precisa seguir o formato '\\d{4,6}' para cadastrá-lo no sistema.")

        String crm,// buga, se eu usar long

        @NotNull(message = "Como um médico será cadastrado no sistema sem aquilo que ele faz ser informado?") Especialidade especialidade,

        @NotNull(message = "Informe o endereço do médico.") @Valid DadosEndereco endereco
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