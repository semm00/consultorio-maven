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
@Table(name = "procedimento")
public class Procedimento implements Agendavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    public Procedimento() {
    }

    public Procedimento(Medico medico, Paciente paciente, LocalDateTime dataHora, String descricao) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    @Override
    public void agendar() {
        System.out.println("Procedimento agendado para " + formatarDataHora());
    }

    @Override
    public void cancelar() {
        System.out.println("Procedimento cancelado.");
    }

    public String resumo() {
        return String.format("Procedimento %s com Dr(a). %s para %s em %s", descricao, medico.getNome(), paciente.getNome(), formatarDataHora());
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

    public String getDescricao() {
        return descricao;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return resumo();
    }
}
