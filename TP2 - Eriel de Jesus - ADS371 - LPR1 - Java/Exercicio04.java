/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Armazenar seis valores em uma matriz de ordem 2x3.
 *              Apresentar os valores na tela.
 */
package ex;

public class Exercicio04 {

    public static void main(String[] args) {
        double[][] matriz = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0}
        };

        for (int linha = 0; linha < 2; linha++) {
            System.out.print("[ ");
            for (int coluna = 0; coluna < 3; coluna++) {
                System.out.printf("%-6.1f", matriz[linha][coluna]);
            }
            System.out.println("]");
        }
    }
}
