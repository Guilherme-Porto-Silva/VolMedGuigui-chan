package met.vol.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import met.vol.api.domain.DTO.medico.DadosCadastroMedico;
import met.vol.api.domain.service.Tecnico;

@Entity @Getter @Setter @EqualsAndHashCode(of = "id") public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @Enumerated (EnumType.STRING) private Especialidade especialidade;

    private Endereco endereco;

    private Boolean ativo = true;

    public Medico (String nome, String email, String crm, Especialidade especialidade) {

        this.nome = nome;

        this.email = email;

        this.crm = crm;

        this.especialidade = especialidade;
    }

    public Medico (DadosCadastroMedico dados) {

        nome = Tecnico.titulacao(dados.nome());

        email = dados.email();

        telefone = dados.telefone();

        crm = dados.crm();

        especialidade = dados.especialidade();

        endereco = new Endereco(dados.endereco());
    }

    public Medico () {}

    @Override public String toString () {

        DadosCadastroMedico dto = new DadosCadastroMedico(this);

        return dto.toString();
    }
}