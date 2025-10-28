package br.ifpi.edu.factory;

import br.ifpi.edu.DAO.ConsultaDAO;
import br.ifpi.edu.Model.Consulta;

public class ConsultaFactory extends AgendamentoFactory<Consulta> {

    private final ConsultaDAO consultaDAO;

    public ConsultaFactory(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    @Override
    protected Consulta criarAgendamento(AgendamentoDados dados) {
        return new Consulta(dados.getMedico(), dados.getPaciente(), dados.getDataHora());
    }

    @Override
    protected void salvar(Consulta agendamento) {
        consultaDAO.salvar(agendamento);
    }
}
