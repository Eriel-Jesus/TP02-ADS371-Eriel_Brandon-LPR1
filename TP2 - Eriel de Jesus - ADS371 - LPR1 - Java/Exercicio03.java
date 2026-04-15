/*
 * Disciplina : CBTLPR1 (Java) - ADS 371 - Professor: Wellington Tuler Moraes
 * Trabalho   : Trabalho Prático 02
 * Alunos     : Brandon Oliveira Simões e Eriel de Jesus Souza
 *
 * Enunciado  : Entrar via teclado com "N" valores quaisquer. O valor "N" deverá ser positivo
 *              e menor que vinte. Caso não satisfaça, enviar mensagem de erro e solicitar
 *              novamente. Após a digitação, exibir: o maior valor, o menor valor, a soma,
 *              a média aritmética, a porcentagem de valores positivos e a porcentagem de
 *              valores negativos. Perguntar ao usuário se deseja nova execução (S/N).
 */
package ex;

import java.util.Scanner;

public class Exercicio03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char novaExecucao;

        do {
            int quantidade = lerQuantidade(scanner);
            double[] valores = lerValores(scanner, quantidade);
            exibirResultados(valores);

            do {
                System.out.print("Deseja nova execução? (S/N): ");
                novaExecucao = scanner.next().toUpperCase().charAt(0);
                if (novaExecucao != 'S' && novaExecucao != 'N') {
                    System.out.println("Erro! Digite apenas S ou N.");
                }
            } while (novaExecucao != 'S' && novaExecucao != 'N');

        } while (novaExecucao == 'S');

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    private static int lerQuantidade(Scanner scanner) {
        int quantidade;
        do {
            System.out.print("Digite a quantidade de valores (1 a 19): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }
            quantidade = scanner.nextInt();
            if (quantidade < 1 || quantidade >= 20) {
                System.out.println("Erro! A quantidade deve ser positiva e menor que 20.");
            }
        } while (quantidade < 1 || quantidade >= 20);
        return quantidade;
    }

    private static double[] lerValores(Scanner scanner, int quantidade) {
        double[] valores = new double[quantidade];
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Digite o " + (i + 1) + "º valor: ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }
            valores[i] = scanner.nextDouble();
        }
        return valores;
    }

    private static void exibirResultados(double[] valores) {
        double maiorValor = valores[0];
        double menorValor = valores[0];
        double soma = 0.0;
        double quantidadePositivos = 0;
        double quantidadeNegativos = 0;

        for (int i = 0; i < valores.length; i++) {
            if (valores[i] > maiorValor) maiorValor = valores[i];
            if (valores[i] < menorValor) menorValor = valores[i];
            soma += valores[i];
            if (valores[i] > 0) quantidadePositivos++;
            if (valores[i] < 0) quantidadeNegativos++;
        }

        double media = soma / valores.length;
        double percentualPositivos = (quantidadePositivos / valores.length) * 100;
        double percentualNegativos = (quantidadeNegativos / valores.length) * 100;

        System.out.println("\nMaior valor: " + maiorValor);
        System.out.println("Menor valor: " + menorValor);
        System.out.println("Soma dos valores: " + soma);
        System.out.printf("Média aritmética: %.2f%n", media);
        System.out.printf("Percentual de positivos: %.2f%%%n", percentualPositivos);
        System.out.printf("Percentual de negativos: %.2f%%%n", percentualNegativos);
        System.out.println("--------------------------------------------------");
    }
}
