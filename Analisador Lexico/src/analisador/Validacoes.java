package analisador;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validacoes {

    private static final String DIGITOS = "0123456789";
    private static final List<String> OPERADORES = Arrays.asList(
        "+", "-", "*", "=", "<>", "<", ">", "<=", ">=", ":=", "div", "or", "and", "not"
    );
    private static final List<String> SIMBOLOS_ESPECIAIS = Arrays.asList(
        "(", ")", "[", "]", ".", ",", ";", ":", ".."
    );
    public static final Set<String> PALAVRAS_RESERVADAS = new HashSet<>(Arrays.asList(
        "var", "array", "function", "procedure", "program", "true", "false", "char", "integer", "boolean",
        "if", "then", "else", "of", "while", "do", "begin", "end", "read", "write"
    ));
    private static final String LETRAS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static boolean isOperador(String str) {
        return OPERADORES.contains(str);
    }

    static boolean isSimboloEspecial(String str) {
        return SIMBOLOS_ESPECIAIS.contains(str);
    }

    private static boolean isAtribuicao(String str) {
        return ":=".equals(str);
    }

    private static boolean isString(String str) {
    str = str.trim(); 
    
    if ((str.startsWith("'") && str.endsWith("'")) || (str.startsWith("\"") && str.endsWith("\""))) {
        return true;
    }
    
    return false;
}

    public static boolean isDigito(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!DIGITOS.contains(String.valueOf(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIdentificador(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return false;
        }

        if (!LETRAS.contains(String.valueOf(identificador.charAt(0)))) {
            return false;
        }

        for (int i = 1; i < identificador.length(); i++) {
            char ch = identificador.charAt(i);
            if (!LETRAS.contains(String.valueOf(ch)) && !DIGITOS.contains(String.valueOf(ch))) {
                return false;
            }
        }

        return true;
    }

    public static String validar(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "Indefinido";
        }

        str = str.trim();

        if (isString(str)) {
            return "String";
        } else if (PALAVRAS_RESERVADAS.contains(str)) {
            return "Palavra reservada";
        } else if (isOperador(str)) {
            return "Operador";
        } else if (isSimboloEspecial(str)) {
            return "Simbolo especial";
        } else if (isAtribuicao(str)) {
            return "Atribuicao";
        } else if (isDigito(str)) {
            return "Digito";
        } else if (isIdentificador(str)) {
            return "Identificador";
        } else {
            return "Indefinido";
        }
    }
}
