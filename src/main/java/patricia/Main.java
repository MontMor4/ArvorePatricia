package patricia;

import java.util.Scanner;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArvorePatricia arvore = new ArvorePatricia(128); //instancia uma nova arvore patricia
        System.out.println("Digite o arquivo:");
        String arquivo;
        arquivo = in.next(); //recebe o arquivo que será lido

        try {
            ExtraiPalavra palavras = new ExtraiPalavra(arquivo); //extrai as palavras do arquivo
            String palavra = null;

            while ((palavra = palavras.proximaPalavra()) != null) { //enquanto não chegar ao final do arquivo...

                if (!palavra.equals("")) { //se a palavra for diferente de vazio
                    String resposta = ""; //inicializa a 'resposta' como vazio

                    if (palavra.length() < 16) {
                        for (int k = palavra.length(); k < 17; k++) {
                            palavra += "0"; //completa a palavra com '0' até completar 16 caracteres para padronizar os tamanhos
                        }
                    }

                    for (int k = 0; k < 16; k++) {

                        //instancia um "binary" que recebe a conversão do 'k-ésimo' caracter da palavra para um número binário
                        //essa conversão é feita 16 vezes no laço 'for', sendo uma para cada caractere da palavra (16 no total)
                        String binary = Integer.toBinaryString((int) palavra.charAt(k));

                        if (binary.length() < 8) { //se o tamanho do binário convertido for menor que 8 bits...
                            for (int j = 0; j < (8 - binary.length()); j++) {
                                resposta += "0"; //completa a resposta com '0' a esquerda para completar 8 bits
                            }
                        }
                        resposta += binary; //a cada iteração do loop, a 'resposta' é incrementada com o caracter convertido
                    }

                    //lê a linha e coluna atual, e insere na arvore a palavra convertida
                    int linhas = palavras.getLinha();
                    int colunas = palavras.getColuna();
                    arvore.insere(resposta, linhas, colunas);
                }
            }
            palavras.fecharArquivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //realizando a pesquisa na árvore
        String pesquisa = "S";
        System.out.println("Digite a palavra que gostaria de buscar: (Caso não deseje mais procurar por palavras, digite N)");
        pesquisa = in.next();

        while (!pesquisa.equals("N")) { //enquanto a pesquisa for diferente de 'N'
            String resposta = ""; //inicializa a resposta como vazio

            if (pesquisa.length() < 16) {
                for (int k = pesquisa.length(); k < 17; k++) {
                    pesquisa += "0"; //completa a palavra com '0' até completar 16 caracteres para padronizar os tamanhos
                }
            }
            for (int k = 0; k < 16; k++) {

                //instancia um "binary" que recebe a conversão do 'k-ésimo' caracter da palavra para um número binário
                //essa conversão é feita 16 vezes no laço 'for', sendo uma para cada caractere da palavra (16 no total)
                String binary = Integer.toBinaryString((int) pesquisa.charAt(k));

                if (binary.length() < 8) { //se o tamanho do binário convertido for menor que 8 bits...
                    for (int j = 0; j < (8 - binary.length()); j++) {
                        resposta += "0"; //completa a resposta com '0' a esquerda para completar 8 bits
                    }
                }
                resposta += binary; //a cada iteração do loop, a 'resposta' é incrementada com o caracter convertido
            }
            //realiza a pesquisa da palavra
            arvore.pesquisa(resposta);

            System.out.println("\nCaso queira interromper digite N");
            System.out.println("Digite a palvra que gostaria de buscar: ");
            pesquisa = in.next();
        }
    }
}
