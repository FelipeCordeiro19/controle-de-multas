package modelo;

import java.time.DayOfWeek;

public class RegraRodizio extends RegraMulta {

    @Override
    public double calcularMulta(Ocorrencia ocorrencia) {
        if (estaEmRodizio(ocorrencia)) {
            return 130.16; // Multa padrão para infração de rodízio
        }
        return 0; // Sem multa
    }

    @Override
    public String verificaNivelMulta(Ocorrencia ocorrencia) {
        return estaEmRodizio(ocorrencia) ? "Rodízio violado" : "Sem infração";
    }

    private boolean estaEmRodizio(Ocorrencia ocorrencia) {
        DayOfWeek dia = ocorrencia.getData().getDayOfWeek();
        char ultimoDigito = ocorrencia.getPlaca().charAt(ocorrencia.getPlaca().length() - 1);

        // Exemplo de lógica simplificada para rodízio
        return (dia == DayOfWeek.MONDAY && (ultimoDigito == '1' || ultimoDigito == '2')) ||
                (dia == DayOfWeek.TUESDAY && (ultimoDigito == '3' || ultimoDigito == '4')) ||
                (dia == DayOfWeek.WEDNESDAY && (ultimoDigito == '5' || ultimoDigito == '6')) ||
                (dia == DayOfWeek.THURSDAY && (ultimoDigito == '7' || ultimoDigito == '8')) ||
                (dia == DayOfWeek.FRIDAY && (ultimoDigito == '9' || ultimoDigito == '0'));
    }
}
