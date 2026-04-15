package ex;
/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Entrar com uma matriz de ordem MxM, onde a ordem também será escolhida pelo usuário,
				sendo que no máximo será de ordem 10 e quadrática. Após a digitação dos elementos,
				calcular e exibir determinante da matriz.
 */
import java.util.Scanner;

public class Exercicio11 {

    private static final double TOLERANCIA_ZERO = 1e-10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ordem = lerOrdemMatriz(scanner);
        double[][] matriz = lerElementosMatriz(scanner, ordem);
        scanner.close();

        System.out.println("\nMatriz digitada:");
        exibirMatriz(matriz);

        double determinante = calcularDeterminante(matriz);

        System.out.printf("\nDeterminante = %.2f\n", determinante);
    }

    private static int lerOrdemMatriz(Scanner scanner) {
        int ordem;
        do {
            System.out.print("Digite a ordem da matriz quadrada (1 a 10): ");
            ordem = scanner.nextInt();
            if (ordem < 1 || ordem > 10) {
                System.out.println("Erro! A ordem deve estar entre 1 e 10.");
            }
        } while (ordem < 1 || ordem > 10);
        return ordem;
    }

    private static double[][] lerElementosMatriz(Scanner scanner, int ordem) {
        double[][] matriz = new double[ordem][ordem];
        System.out.println("Digite os elementos da matriz:");
        for (int linha = 0; linha < ordem; linha++) {
            for (int coluna = 0; coluna < ordem; coluna++) {
                System.out.printf("Elemento da %dª linha %dª coluna: ", linha+1, coluna+1);
                matriz[linha][coluna] = scanner.nextDouble();
            }
        }
        return matriz;
    }

    public static double calcularDeterminante(double[][] matrizOriginal) {
        int tamanho = matrizOriginal.length;

        // Caso matriz 1x1
        if (tamanho == 1) {
            return matrizOriginal[0][0];
        }

        double[][] matrizTrabalho = new double[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizTrabalho[i][j] = matrizOriginal[i][j];
            }
        }

        int numeroTrocasLinha = 0;
        double determinante = 1.0;

        for (int colunaPivo = 0; colunaPivo < tamanho; colunaPivo++) {

            // Encontra o maior pivô na coluna atual
            int indiceLinhaPivo = colunaPivo;
            double maiorValor = Math.abs(matrizTrabalho[colunaPivo][colunaPivo]);

            for (int linha = colunaPivo + 1; linha < tamanho; linha++) {
                double valorAtual = Math.abs(matrizTrabalho[linha][colunaPivo]);
                if (valorAtual > maiorValor) {
                    maiorValor = valorAtual;
                    indiceLinhaPivo = linha;
                }
            }

            // Se o pivô for praticamente zero, determinante é zero
            if (maiorValor < TOLERANCIA_ZERO) {
                return 0.0;
            }

            // Se necessário, troca as linhas (na matriz de trabalho)
            if (indiceLinhaPivo != colunaPivo) {
                double[] linhaTemp = matrizTrabalho[colunaPivo];
                matrizTrabalho[colunaPivo] = matrizTrabalho[indiceLinhaPivo];
                matrizTrabalho[indiceLinhaPivo] = linhaTemp;
                numeroTrocasLinha++;
            }

            // Eliminação das linhas abaixo do pivô
            for (int linha = colunaPivo + 1; linha < tamanho; linha++) {
                double fator = matrizTrabalho[linha][colunaPivo] / matrizTrabalho[colunaPivo][colunaPivo];
                for (int coluna = colunaPivo; coluna < tamanho; coluna++) {
                    matrizTrabalho[linha][coluna] -= fator * matrizTrabalho[colunaPivo][coluna];
                }
            }
        }

        // O determinante é o produto da diagonal principal
        for (int i = 0; i < tamanho; i++) {
            determinante *= matrizTrabalho[i][i];
        }

        // Ajuste de sinal de acordo com o número de trocas de linha
        if (numeroTrocasLinha % 2 == 1) {
            determinante = -determinante;
        }

        return determinante;
    }

  // Função reaproveitada do exercício anterior
    private static void exibirMatriz(double[][] matriz) {
        int tamanho = matriz.length;
        if (tamanho == 0) return;

        String[][] elementoFormatado = new String[tamanho][tamanho];
        int[] larguraColuna = new int[tamanho];

        for (int col = 0; col < tamanho; col++) {
            for (int lin = 0; lin < tamanho; lin++) {
                String texto = String.format("%.2f", matriz[lin][col]);
                if (matriz[lin][col] >= 0) {
                    texto = " " + texto;
                }
                elementoFormatado[lin][col] = texto;
                larguraColuna[col] = Math.max(larguraColuna[col], texto.length());
            }
        }
        
        for (int lin = 0; lin < tamanho; lin++) {
            System.out.print("[ ");
            for (int col = 0; col < tamanho; col++) {
                String valor = elementoFormatado[lin][col];
                int espacosTotal = larguraColuna[col] - valor.length();
                int espacosEsquerda = espacosTotal / 2;
                int espacosDireita = espacosTotal - espacosEsquerda;

                String paddingEsquerda = "";
                for (int i = 0; i < espacosEsquerda; i++) paddingEsquerda += " ";
                String paddingDireita = "";
                for (int i = 0; i < espacosDireita; i++) paddingDireita += " ";

                System.out.print(paddingEsquerda + valor + paddingDireita);

                boolean ehUltimaColuna = (col == tamanho - 1);
                if (!ehUltimaColuna) {
                    System.out.print("  ");
                }
            }
            System.out.println(" ]");
        }
    }
}