package br.ifpi.edu.factory;

import java.time.LocalDateTime;

import br.ifpi.edu.Model.Medico;
import br.ifpi.edu.Model.Paciente;

public class AgendamentoDados {
    private final Medico medico;
    private final Paciente paciente;
    private final LocalDateTime dataHora;
    private final String descricao;

    public AgendamentoDados(Medico medico, Paciente paciente, LocalDateTime dataHora, String descricao) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public AgendamentoDados comDescricao(String novaDescricao) {
        return new AgendamentoDados(medico, paciente, dataHora, novaDescricao);
    }
}
