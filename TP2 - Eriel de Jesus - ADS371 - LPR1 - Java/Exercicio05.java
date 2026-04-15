/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Armazenar seis valores em uma matriz de ordem 3x2.
 *              Apresentar os valores na tela.
 */

public class Exercicio05 {
    public static void main(String[] args) {
        int[][] matriz = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
