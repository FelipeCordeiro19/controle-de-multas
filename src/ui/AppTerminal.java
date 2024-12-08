package ui;

import modelo.Ocorrencia;
import modelo.RegraVelocidade;
import servico.BaseDeDados;

import java.time.LocalDate;
import java.util.Scanner;

public class AppTerminal {
    public static void main(String[] args) {
        BaseDeDados base = new BaseDeDados();
        RegraVelocidade regraVelocidade = new RegraVelocidade(60);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Adicionar Ocorrência");
            System.out.println("2. Verificar Multas por Placa");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Logradouro: ");
                    String logradouro = scanner.nextLine();
                    System.out.print("Data (yyyy-mm-dd): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine());
                    System.out.print("Velocidade: ");
                    double velocidade = scanner.nextDouble();

                    Ocorrencia ocorrencia = new Ocorrencia(placa, logradouro, data, velocidade);
                    base.adicionarOcorrencia(ocorrencia);
                    System.out.println("Ocorrência adicionada!");
                    break;

                case 2:
                    System.out.print("Digite a placa: ");
                    String placaBusca = scanner.nextLine();
                    for (Ocorrencia o : base.buscarPorPlaca(placaBusca)) {
                        System.out.println("Data: " + o.getData() + ", Multa: " +
                                regraVelocidade.calcularMulta(o));
                    }
                    break;

                case 3:
                    System.out.println("Encerrando...");
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
