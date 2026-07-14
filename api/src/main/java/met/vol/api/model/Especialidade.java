package met.vol.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Especialidade {

    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    GINECOLOGIA("ginecologia"),
    DERMATOLOGIA("dermatologia");

    private final String ESPECIALIDADE;

    Especialidade (String especialidade) {
        ESPECIALIDADE = especialidade;
    }

    @JsonCreator public static Especialidade parseEspecialidade (String espe) {

        for (Especialidade categoria: Especialidade.values())

            if (categoria.ESPECIALIDADE.equalsIgnoreCase(espe))

                return categoria;

        throw new IllegalArgumentException("Não oferecemos esse serviço.");
    }

    @Override public String toString () {

        char inicial = ESPECIALIDADE.toUpperCase().charAt(0);

        String resto = ESPECIALIDADE.substring(1).toLowerCase();

        return inicial + resto;
    }
}