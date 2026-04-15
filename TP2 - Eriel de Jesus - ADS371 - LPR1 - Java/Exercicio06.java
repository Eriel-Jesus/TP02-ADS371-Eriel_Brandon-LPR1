/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Armazenar seis nomes em uma matriz de ordem 2x3.
 *              Apresentar os nomes na tela.
 */

public class Exercicio06 {
    public static void main(String[] args) {
        String[][] matriz = {
            {"Ana", "Bruno", "Carlos"},
            {"Diana", "Eduardo", "Fernanda"}
        };

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
