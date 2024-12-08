package modelo;

import java.time.LocalDate;

public class Ocorrencia {
    private String placa;
    private String logradouro;
    private LocalDate data;
    private double velocidade;

    // Construtor
    public Ocorrencia(String placa, String logradouro, LocalDate data, double velocidade) {
        this.placa = placa;
        this.logradouro = logradouro;
        this.data = data;
        this.velocidade = velocidade;
    }

    // Getters e Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }
}

