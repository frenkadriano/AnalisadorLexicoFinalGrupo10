package analisador;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AnalisadorLexico {

    private List<Token> tokens;
    private List<String> erros;

    public AnalisadorLexico() {
        tokens = new ArrayList<>();
        erros = new ArrayList<>();
    }

    public void analisarCodigoFonte(String codigoFonte) {
        tokens.clear();
        erros.clear();

        String[] linhas = codigoFonte.split("\n");
        for (int i = 0; i < linhas.length; i++) {
            analisarLinha(linhas[i], i + 1);
        }
    }

    private void analisarLinha(String linha, int numeroLinha) {
    StringBuilder tokenBuilder = new StringBuilder();
    boolean dentroString = false;
    char stringDelimiter = '\0';
    
    for (int i = 0; i < linha.length(); i++) {
        char c = linha.charAt(i);
        
        if (dentroString) {
            tokenBuilder.append(c);
            if (c == stringDelimiter) {
                tokens.add(new Token(tokenBuilder.toString(), "String", numeroLinha));
                tokenBuilder.setLength(0);
                dentroString = false;
            }
        } else {
            if (c == '\'' || c == '"') {
                if (tokenBuilder.length() > 0) {
                    processarToken(tokenBuilder.toString(), numeroLinha);
                    tokenBuilder.setLength(0);
                }
                dentroString = true;
                stringDelimiter = c;
                tokenBuilder.append(c);
            } else if (Character.isWhitespace(c) || Validacoes.isSimboloEspecial(Character.toString(c))) {
                if (tokenBuilder.length() > 0) {
                    processarToken(tokenBuilder.toString(), numeroLinha);
                    tokenBuilder.setLength(0);
                }
                if (!Character.isWhitespace(c)) {
                    tokens.add(new Token(Character.toString(c), "Simbolo especial", numeroLinha));
                }
            } else {
                tokenBuilder.append(c);
            }
        }
    }
    
    if (tokenBuilder.length() > 0) {
        processarToken(tokenBuilder.toString(), numeroLinha);
    }
    }

    private void processarToken(String token, int numeroLinha) {
        String classe = Validacoes.validar(token);
        if ("Indefinido".equals(classe)) {
            erros.add("Erro léxico na linha " + numeroLinha + ": " + token);
        } else {
            tokens.add(new Token(token, classe, numeroLinha));
        }
    }


    public List<Token> getTokens() {
        return tokens;
    }

    public List<String> getErros() {
        return erros;
    }
}
