package br.ifpi.edu.Model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico extends Funcionario {
    private String crm;
    private String especialidade;

    public Medico() {
        super();
    }

    public Medico(String nome, String cpf, LocalDate dataNascimento, int matricula, double salario, String crm, String especialidade) {
        super(nome, cpf, dataNascimento, matricula, salario);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("CRM: " + crm + ", Especialidade: " + especialidade);
    }

    public void emitirLaudo() {
        System.out.println("Laudo emitido pelo médico " + getNome());
    }
}

