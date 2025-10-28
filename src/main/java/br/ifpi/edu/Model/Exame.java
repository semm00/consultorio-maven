package br.ifpi.edu.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exame")
public class Exame implements Agendavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    public Exame() {
    }

    public Exame(Medico medico, Paciente paciente, LocalDateTime dataHora, String tipo) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.tipo = tipo;
    }

    @Override
    public void agendar() {
        System.out.println("Exame agendado para " + formatarDataHora());
    }

    @Override
    public void cancelar() {
        System.out.println("Exame cancelado.");
    }

    public String resumo() {
        String medicoNome = medico != null ? medico.getNome() : "Sem médico vinculado";
        return String.format("Exame %s para %s em %s (Responsável: %s)", tipo, paciente.getNome(), formatarDataHora(), medicoNome);
    }

    private String formatarDataHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }

    public Long getId() {
        return id;
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

    public String getTipo() {
        return tipo;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return resumo();
    }
}
