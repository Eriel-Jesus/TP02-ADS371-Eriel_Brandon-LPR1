package ex;
/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Entrar com uma matriz de ordem MxM, onde a ordem também será escolhida pelo
 *              usuário, sendo que no máximo será de ordem 10 e quadrática. Após a digitação
 *              dos elementos, calcular e exibir a matriz inversa. Exibir as matrizes na tela,
 *              sob a forma matricial (linhas x colunas).
 */
import java.util.Scanner;

public class Exercicio10 {

    // Constante o mais próxima de 0, pois 0.0 poderia gerar problemas por questões do ponto flutuante
    private static final double TOLERANCIA_ZERO = 1e-10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ordem = lerOrdemMatriz(scanner);
        double[][] matrizOriginal = lerElementosMatriz(scanner, ordem);
        scanner.close();

        double[][] matrizInversa = calcularMatrizInversa(matrizOriginal);
        exibirMatriz(matrizOriginal);
        if (matrizInversa == null) {
            System.out.println("\nA matriz não possui inversa (determinante zero).");
        } else {
            System.out.println("\nMatriz Inversa:");
            exibirMatriz(matrizInversa);
        }
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
                System.out.printf("Elemento da %dª linha, %dª coluna: ", linha+1, coluna+1);
                matriz[linha][coluna] = scanner.nextDouble();
            }
        }
        return matriz;
    }


    public static double[][] calcularMatrizInversa(double[][] matrizOriginal) {
        int tamanho = matrizOriginal.length;

        // Cria uma cópia da matriz original para trabalhar, se não os dados da original são perdidos
        double[][] matrizTrabalho = new double[tamanho][tamanho];
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                matrizTrabalho[linha][coluna] = matrizOriginal[linha][coluna];
            }
        }

        // Inicializa a matriz inversa como uma matriz identidade
        double[][] matrizInversa = new double[tamanho][tamanho];
        for (int diagonal = 0; diagonal < tamanho; diagonal++) {
            matrizInversa[diagonal][diagonal] = 1.0;
        }


        for (int colunaPivo = 0; colunaPivo < tamanho; colunaPivo++) {


            int indiceLinhaPivo = colunaPivo;
            double maiorValorAbsoluto = Math.abs(matrizTrabalho[colunaPivo][colunaPivo]);

            for (int linha = colunaPivo + 1; linha < tamanho; linha++) {
                double valorAtual = Math.abs(matrizTrabalho[linha][colunaPivo]);
                if (valorAtual > maiorValorAbsoluto) {
                    maiorValorAbsoluto = valorAtual;
                    indiceLinhaPivo = linha;
                }
            }


            if (maiorValorAbsoluto < TOLERANCIA_ZERO) {
                return null;
            }

            // Se a linha do pivô não for a linha atual, troca as duas linhas (tanto na matriz de trabalho quanto na inversa)
            if (indiceLinhaPivo != colunaPivo) {
                double[] linhaTemporaria = matrizTrabalho[colunaPivo];
                matrizTrabalho[colunaPivo] = matrizTrabalho[indiceLinhaPivo];
                matrizTrabalho[indiceLinhaPivo] = linhaTemporaria;

                double[] linhaTemporariaInversa = matrizInversa[colunaPivo];
                matrizInversa[colunaPivo] = matrizInversa[indiceLinhaPivo];
                matrizInversa[indiceLinhaPivo] = linhaTemporariaInversa;
            }


            // Divide toda a linha pelo valor do pivô para que o elemento da diagonal seja 1
            double valorPivo = matrizTrabalho[colunaPivo][colunaPivo];
            for (int coluna = 0; coluna < tamanho; coluna++) {
                matrizTrabalho[colunaPivo][coluna] /= valorPivo;
                matrizInversa[colunaPivo][coluna] /= valorPivo;
            }

            // Zera todos os outros elementos da coluna atual, exceto o pivô
            for (int linha = 0; linha < tamanho; linha++) {
                if (linha != colunaPivo) {
                    double fatorEliminacao = matrizTrabalho[linha][colunaPivo];
                    for (int coluna = 0; coluna < tamanho; coluna++) {
                        matrizTrabalho[linha][coluna] -= fatorEliminacao * matrizTrabalho[colunaPivo][coluna];
                        matrizInversa[linha][coluna] -= fatorEliminacao * matrizInversa[colunaPivo][coluna];
                    }
                }
            }
        }

        return matrizInversa;
    }
    
    private static void exibirMatriz(double[][] matriz) {
        int tamanho = matriz.length;
        if (tamanho == 0) return;

        // Formata os elementos da matriz em string 
        // Calcula a largura de cada elemento com base no elemento que ocupa mais espaço (por seus dígitos + sinal)
        String[][] colunaFormatada = new String[tamanho][tamanho];
        int[] larguraColuna = new int[tamanho];

        for (int col = 0; col < tamanho; col++) {
            for (int lin = 0; lin < tamanho; lin++) {
                String texto = String.format("%.4f", matriz[lin][col]);
                // Acrescenta +1 espaço em números positivos, para igualar com o espaço do "-" de negativos
                if (matriz[lin][col] >= 0) {
                    texto = " " + texto;
                }
                colunaFormatada[lin][col] = texto;
                larguraColuna[col] = Math.max(larguraColuna[col], texto.length());
            }
        }

        for (int lin = 0; lin < tamanho; lin++) {
            System.out.print("[ ");
            for (int col = 0; col < tamanho; col++) {
                String valor = colunaFormatada[lin][col];
                int espacosTotal = larguraColuna[col] - valor.length();
                int espacosEsquerda = espacosTotal / 2;
                int espacosDireita = espacosTotal - espacosEsquerda;

                String paddingEsquerda = "";
                for (int i = 0; i < espacosEsquerda; i++) paddingEsquerda += " ";
                String paddingDireita = "";
                for (int i = 0; i < espacosDireita; i++) paddingDireita += " ";

                System.out.print(paddingEsquerda + valor + paddingDireita);
                
                boolean ultimaColuna = (col == tamanho - 1);
                if (!ultimaColuna) {
                    System.out.print("  ");
                }
            }
            System.out.println(" ]");
        }
    }
}

    
  