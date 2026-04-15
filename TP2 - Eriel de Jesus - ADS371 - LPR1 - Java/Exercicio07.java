package ex;
/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Entrar via teclado com doze valores e armazená-los em uma matriz de ordem 3x4.
 *              Após a digitação dos valores solicitar uma constante multiplicativa, que deverá
 *              multiplicar cada valor da matriz e armazenar o resultado na própria matriz,
 *              nas posições correspondentes.
 */


import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] matriz = new double[3][4];

        System.out.println("Digite os 12 valores da matriz 3x4:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("Elemento da %dª linha, %dª coluna: ", i+1, j+1);
                matriz[i][j] = sc.nextDouble();
            }
        }
        
        System.out.print("Digite a constante multiplicativa: ");
        double constante = sc.nextDouble();
        
        System.out.println("\nMatriz original:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%8.2f", matriz[i][j]);
            }
            System.out.println();
        }
  

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] *= constante;
            }
        }      

        System.out.println("\nMatriz resultante:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%8.2f", matriz[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}
