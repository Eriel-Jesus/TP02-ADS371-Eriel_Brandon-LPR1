package ex;

import java.util.Scanner;

/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Entrar com uma matriz de ordem MxN, onde a ordem também será escolhida pelo
 *              usuário, sendo que no máximo 10x10. A matriz não precisa ser quadrática.
 *              Após a digitação dos elementos, calcular e exibir a matriz transposta.
 */

public class Exercicio09 {

	static int lerIntervalo(Scanner scanner, String mensagem, int min, int max) {
		int valor;
		do {
			System.out.printf(mensagem, min, max);
			valor = scanner.nextInt();
			if (valor < min || valor > max) {
				System.out.printf("\nErro: O valor deve estar entre %d e %d.%n", min, max);
			}
		} while (valor < min || valor > max);
		return valor;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = lerIntervalo(scanner, "Digite a primeira ordem da matriz (%d a %d): ", 1, 10);
		int n = lerIntervalo(scanner, "Digite a segunda ordem da matriz (%d a %d): ", 1, 10);

		double[][] matriz = new double[m][n];

		System.out.println("Digite os elementos da matriz:");
		for (int linha = 0; linha < m; linha++) {
			for (int coluna = 0; coluna < n; coluna++) {
				System.out.printf("Elemento da linha %d, coluna %d: ", linha + 1, coluna + 1);
				matriz[linha][coluna] = scanner.nextDouble();
			}
		}
		scanner.close();

		System.out.println("\nMatriz Original");
		for (int linha = 0; linha < m; linha++) {
			System.out.print("[ ");
			for (int coluna = 0; coluna < n; coluna++) {
				System.out.printf("%-5.1f", matriz[linha][coluna]);
			}
			System.out.println("]");
		}
		System.out.println();

		// Transposta -> matriz[m][n], matriz[n][m]
		System.out.println("\nMatriz Transposta:");
		for (int linha = 0; linha < n; linha++) {
			System.out.print("[ ");
			for (int coluna = 0; coluna < m; coluna++) {
				System.out.printf("%-5.1f", matriz[coluna][linha]);
			}
			System.out.println("]");
		}

	}

}
