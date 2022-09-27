package patricia;

import java.util.Scanner;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArvorePatricia arvore = new ArvorePatricia(8);
        // char vetor[] = new char[128];
        System.out.println("Digite o arquivo:");
        String arquivo;
        long contador = 0;
        arquivo = in.next();

        try {

            Path file = Paths.get(arquivo);
            contador = Files.lines(file).count();
            System.out.println("Total lines: " + contador);

        } catch (Exception e) {
            e.getStackTrace();
        }

        try {
            ExtraiPalavra palavras = new ExtraiPalavra("delimitador.txt", arquivo);
            String palavra = null;
            int i = 1;

            while ((palavra = palavras.proximaPalavra()) != null) {

                String resposta = "";
                if (palavra.length() < 16) {
                    for (int k = palavra.length(); k < 16; k++) {   //incrementa o tamanho da palavra com espaços vazios para completar 16 caracteres
                        palavra += " ";
                    }
                    for (int k = 0; k < 16; k++) {

                        //instancia um "binary" que recebe a conversão do caracter 'k' em um número binário
                        String binary = Integer.toBinaryString((int) palavra.charAt(k));

                        if (binary.length() < 8) {
                            for (int j = 0; j < (8 - binary.length()); j++) {
                                resposta += "0";
                            }
                        }
                        resposta += binary;
                    }
                }
                if (palavra == "") {

                } else {
                    System.out.println("Palavra: " + resposta);
                    arvore.insere(resposta);
                }
            }
            palavras.fecharArquivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String pesquisa = "dia";
        String resposta = "";
        if (pesquisa.length() < 16) {
            for (int k = pesquisa.length(); k < 16; k++) {   //incrementa o tamanho da palavra com espaços vazios para completar 16 caracteres
                pesquisa += " ";
            }
            for (int k = 0; k < 16; k++) {

                //instancia um "binary" que recebe a conversão do caracter 'k' em um número binário
                String binary = Integer.toBinaryString((int) pesquisa.charAt(k));

                if (binary.length() < 8) {
                    for (int j = 0; j < (8 - binary.length()); j++) {
                        resposta += "0";
                    }
                }
                resposta += binary;
            }
            //arvore.imprime();
            //System.out.println("Resposta: " + resposta);
            arvore.pesquisa(resposta);
        }
    }
}
