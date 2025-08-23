package br.ifpi.edu.Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "consulta")
public class Consulta implements Agendavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime dataHora;

    public Consulta() {
    }

    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
    }

    @Override
    public void agendar() {
        System.out.println("Consulta agendada para " + formatarDataHora());
    }

    @Override
    public void cancelar() {
        System.out.println("Consulta cancelada.");
    }

    public void imprimirRecibo() {
        System.out.println("Recibo: Consulta com Dr(a). " + medico.getNome() + 
                           " para paciente " + paciente.getNome() +
                           " em " + formatarDataHora());
    }

    private String formatarDataHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }

    public Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}

