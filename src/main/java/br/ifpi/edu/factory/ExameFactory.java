package br.ifpi.edu.factory;

import br.ifpi.edu.DAO.ExameDAO;
import br.ifpi.edu.Model.Exame;

public class ExameFactory extends AgendamentoFactory<Exame> {

    private final ExameDAO exameDAO;

    public ExameFactory(ExameDAO exameDAO) {
        this.exameDAO = exameDAO;
    }

    @Override
    protected Exame criarAgendamento(AgendamentoDados dados) {
        return new Exame(dados.getMedico(), dados.getPaciente(), dados.getDataHora(), dados.getDescricao());
    }

    @Override
    protected void salvar(Exame agendamento) {
        exameDAO.salvar(agendamento);
    }
}
