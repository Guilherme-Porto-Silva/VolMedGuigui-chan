package met.vol.api.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tecnico {

    private final static Pattern INICIAL = Pattern.compile(" [A-z]{1}");

    public static String titulacao (String texto) {

        char inicial = texto.toUpperCase().charAt(0);

        String resto = texto.substring(1).toLowerCase();

        Matcher ehInicial = INICIAL.matcher(resto);

        while (ehInicial.find()) {

            var posicaoDoChar = ehInicial.regionEnd();

            resto = resto.replace(resto.charAt(posicaoDoChar), resto.toUpperCase().charAt(posicaoDoChar));
        }

        return inicial + resto;
    }
}