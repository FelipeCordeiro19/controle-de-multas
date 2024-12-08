package modelo;

public class RegraVelocidade extends RegraMulta {
    private double limiteVelocidade;

    // Construtor
    public RegraVelocidade(double limiteVelocidade) {
        this.limiteVelocidade = limiteVelocidade;
    }

    @Override
    public double calcularMulta(Ocorrencia ocorrencia) {
        double excesso = ocorrencia.getVelocidade() - limiteVelocidade;
        if (excesso <= 0) return 0; // Sem multa
        if (excesso <= 20) return 130.16; // Multa leve
        if (excesso <= 50) return 195.23; // Multa média
        return 293.47; // Multa grave
    }

    @Override
    public String verificaNivelMulta(Ocorrencia ocorrencia) {
        double excesso = ocorrencia.getVelocidade() - limiteVelocidade;
        if (excesso <= 0) return "Sem infração";
        if (excesso <= 20) return "Leve";
        if (excesso <= 50) return "Média";
        return "Grave";
    }
}
