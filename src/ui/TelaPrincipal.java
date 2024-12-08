package ui;

import modelo.Ocorrencia;
import modelo.RegraVelocidade;
import servico.BaseDeDados;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaPrincipal extends JFrame {
    private BaseDeDados baseDeDados;
    private RegraVelocidade regraVelocidade;

    public TelaPrincipal() {
        // Inicializa a base de dados e as regras
        baseDeDados = new BaseDeDados();
        regraVelocidade = new RegraVelocidade(60);

        // Configurações da janela principal
        setTitle("Controle de Multas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Adiciona os componentes
        JPanel painelBotoes = criarPainelBotoes();
        JScrollPane painelResultados = criarPainelResultados();

        add(painelBotoes, BorderLayout.NORTH);
        add(painelResultados, BorderLayout.CENTER);
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());

        JButton btnAdicionarOcorrencia = new JButton("Adicionar Ocorrência");
        JButton btnBuscarPorPlaca = new JButton("Buscar por Placa");

        btnAdicionarOcorrencia.addActionListener(e -> exibirFormularioOcorrencia());
        btnBuscarPorPlaca.addActionListener(e -> exibirBuscaPorPlaca());

        painel.add(btnAdicionarOcorrencia);
        painel.add(btnBuscarPorPlaca);

        return painel;
    }

    private JScrollPane criarPainelResultados() {
        JTextArea areaResultados = new JTextArea();
        areaResultados.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaResultados);
        scrollPane.setPreferredSize(new Dimension(500, 250));

        return scrollPane;
    }

    private void exibirFormularioOcorrencia() {
        // Janela para adicionar uma nova ocorrência
        JTextField campoPlaca = new JTextField(10);
        JTextField campoLogradouro = new JTextField(15);
        JTextField campoData = new JTextField(10);
        JTextField campoVelocidade = new JTextField(5);

        JPanel painel = new JPanel(new GridLayout(4, 2));
        painel.add(new JLabel("Placa:"));
        painel.add(campoPlaca);
        painel.add(new JLabel("Logradouro:"));
        painel.add(campoLogradouro);
        painel.add(new JLabel("Data (yyyy-mm-dd):"));
        painel.add(campoData);
        painel.add(new JLabel("Velocidade:"));
        painel.add(campoVelocidade);

        int resultado = JOptionPane.showConfirmDialog(this, painel, "Adicionar Ocorrência", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String placa = campoPlaca.getText();
                String logradouro = campoLogradouro.getText();
                LocalDate data = LocalDate.parse(campoData.getText());
                double velocidade = Double.parseDouble(campoVelocidade.getText());

                Ocorrencia ocorrencia = new Ocorrencia(placa, logradouro, data, velocidade);
                baseDeDados.adicionarOcorrencia(ocorrencia);

                JOptionPane.showMessageDialog(this, "Ocorrência adicionada com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar ocorrência: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exibirBuscaPorPlaca() {
        // Janela para buscar por placa
        String placa = JOptionPane.showInputDialog(this, "Digite a placa do veículo:");

        if (placa != null && !placa.isBlank()) {
            java.util.List<Ocorrencia> resultados = baseDeDados.buscarPorPlaca(placa);

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma ocorrência encontrada para a placa: " + placa);
            } else {
                StringBuilder mensagem = new StringBuilder();
                for (Ocorrencia ocorrencia : resultados) {
                    double multa = regraVelocidade.calcularMulta(ocorrencia);
                    mensagem.append("Data: ").append(ocorrencia.getData())
                            .append(", Logradouro: ").append(ocorrencia.getLogradouro())
                            .append(", Velocidade: ").append(ocorrencia.getVelocidade())
                            .append(", Multa: R$ ").append(multa)
                            .append("\n");
                }
                JOptionPane.showMessageDialog(this, mensagem.toString(), "Resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Inicializa a tela principal
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
