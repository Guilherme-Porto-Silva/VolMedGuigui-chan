package met.vol.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import met.vol.api.DTO.DadosCadastroPaciente;
import met.vol.api.DTO.DadosCadastroPaciente;

@AllArgsConstructor @Entity @Getter @Setter @EqualsAndHashCode(of = "id") public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private Endereco endereco;

    private Boolean ativo = true;

    public Paciente (DadosCadastroPaciente dados) {

        nome = dados.nome();

        email = dados.email();

        telefone = dados.telefone();

        cpf = dados.cpf();

        endereco = new Endereco(dados.endereco());
    }

    public Paciente () {}

    @Override public String toString () {

        DadosCadastroPaciente dto = new DadosCadastroPaciente(this);

        return dto.toString();
    }
}