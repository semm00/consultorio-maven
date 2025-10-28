package br.ifpi.edu.factory;

import br.ifpi.edu.Model.Agendavel;

public abstract class AgendamentoFactory<T extends Agendavel> {

    public T criarEAgendar(AgendamentoDados dados) {
        T agendamento = criarAgendamento(dados);
        salvar(agendamento);
        agendamento.agendar();
        return agendamento;
    }

    protected abstract T criarAgendamento(AgendamentoDados dados);

    protected abstract void salvar(T agendamento);
}
