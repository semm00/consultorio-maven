package br.ifpi.edu.Model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {
    private int matricula;
    private double salario;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String cpf, LocalDate dataNascimento, int matricula, double salario) {
        super(nome, cpf, dataNascimento);
        this.matricula = matricula;
        this.salario = salario;
    }

    public int getMatricula() {
        return matricula;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário: " + salario);
    }
}

