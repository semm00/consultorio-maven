package br.ifpi.edu.Model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente extends Pessoa {
    private String convenio;

    public Paciente() {
        super();
    }

    public Paciente(String nome, String cpf, LocalDate dataNascimento, String convenio) {
        super(nome, cpf, dataNascimento);
        this.convenio = convenio;
    }

    public String getConvenio() {
        return convenio;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("Convênio: " + convenio);
    }
}

