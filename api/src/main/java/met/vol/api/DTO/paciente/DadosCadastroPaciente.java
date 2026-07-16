package met.vol.api.DTO.paciente;

import met.vol.api.DTO.DadosEndereco;
import met.vol.api.model.Paciente;

public record DadosCadastroPaciente (String nome, String email, String telefone, String cpf, DadosEndereco endereco) {

    public DadosCadastroPaciente(String nome, String email, String telefone, String cpf,

        String logradouro, String bairro, String cep, String cidade, String uf, short numero, String complemento) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf, numero, complemento);

        this(nome, email, telefone, cpf, esteEndereco);
    }

    public DadosCadastroPaciente (String nome, String email, String telefone, String cpf,

        String logradouro, String bairro, String cep, String cidade, String uf, String complemento) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf, complemento);

        this(nome, email, telefone, cpf, esteEndereco);
    }

    public DadosCadastroPaciente (String nome, String email, String telefone, String cpf,

        String logradouro, String bairro, String cep, String cidade, String uf, short numero) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf, numero);

        this(nome, email, telefone, cpf, esteEndereco);
    }

    public DadosCadastroPaciente (String nome, String email, String telefone, String cpf,

        String logradouro, String bairro, String cep, String cidade, String uf) {

        DadosEndereco esteEndereco = new DadosEndereco(logradouro, bairro, cep, cidade, uf);

        this(nome, email, telefone, cpf, esteEndereco);
    }

    public DadosCadastroPaciente (Paciente dados) {

        DadosEndereco esteEndereco = new DadosEndereco(dados.getEndereco());

        this(dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getCpf(), esteEndereco);
    }

    @Override public String toString () {

        return  "Nome: " + nome + '\n' +
                "Email: " + email + '\n' +
                "Telefone: " + telefone + '\n' +
                "Comprovante de pessoa física: " + cpf;
    }
}