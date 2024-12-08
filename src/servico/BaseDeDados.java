package servico;

import modelo.Ocorrencia;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDados {
    private List<Ocorrencia> ocorrencias;

    public BaseDeDados() {
        this.ocorrencias = new ArrayList<>();
    }

    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        ocorrencias.add(ocorrencia);
    }

    public List<Ocorrencia> buscarPorPlaca(String placa) {
        List<Ocorrencia> resultados = new ArrayList<>();
        for (Ocorrencia o : ocorrencias) {
            if (o.getPlaca().equalsIgnoreCase(placa)) {
                resultados.add(o);
            }
        }
        return resultados;
    }
}
