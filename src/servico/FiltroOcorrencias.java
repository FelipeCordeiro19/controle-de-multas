package servico;

import modelo.Ocorrencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FiltroOcorrencias {

    public List<Ocorrencia> filtrarPorData(List<Ocorrencia> ocorrencias, LocalDate inicio, LocalDate fim) {
        List<Ocorrencia> resultados = new ArrayList<>();
        for (Ocorrencia o : ocorrencias) {
            if ((o.getData().isEqual(inicio) || o.getData().isAfter(inicio)) &&
                    (o.getData().isEqual(fim) || o.getData().isBefore(fim))) {
                resultados.add(o);
            }
        }
        return resultados;
    }
}
