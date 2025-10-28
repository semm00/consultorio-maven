package br.ifpi.edu.factory;

import br.ifpi.edu.DAO.ProcedimentoDAO;
import br.ifpi.edu.Model.Procedimento;

public class ProcedimentoFactory extends AgendamentoFactory<Procedimento> {

    private final ProcedimentoDAO procedimentoDAO;

    public ProcedimentoFactory(ProcedimentoDAO procedimentoDAO) {
        this.procedimentoDAO = procedimentoDAO;
    }

    @Override
    protected Procedimento criarAgendamento(AgendamentoDados dados) {
        return new Procedimento(dados.getMedico(), dados.getPaciente(), dados.getDataHora(), dados.getDescricao());
    }

    @Override
    protected void salvar(Procedimento agendamento) {
        procedimentoDAO.salvar(agendamento);
    }
}
