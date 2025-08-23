
package br.ifpi.edu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ifpi.edu.DAO.ConsultaDAO;
import br.ifpi.edu.DAO.MedicoDAO;
import br.ifpi.edu.DAO.PacienteDAO;
import br.ifpi.edu.Model.Consulta;
import br.ifpi.edu.Model.Medico;
import br.ifpi.edu.Model.Paciente;

public class Main {
    private static final List<Medico> medicos = new ArrayList<>(); // Para exibir na tela, mas o cadastro será persistido
    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final List<Consulta> consultas = new ArrayList<>();
    private static final MedicoDAO medicoDAO = new MedicoDAO();
    private static final PacienteDAO pacienteDAO = new PacienteDAO();
    private static final ConsultaDAO consultaDAO = new ConsultaDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Força a criação das tabelas JPA/Hibernate ao inicializar
        br.ifpi.edu.JPAUtil.getEntityManager().close();

        int opcao;
        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Cadastrar Médico");
            System.out.println("2. Cadastrar Paciente");
            System.out.println("3. Listar Médicos");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Agendar Consulta");
            System.out.println("6. Listar Consultas");
            System.out.println("7. Cancelar Consulta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarMedico();
                case 2 -> cadastrarPaciente();
                case 3 -> listarMedicos();
                case 4 -> listarPacientes();
                case 5 -> agendarConsulta();
                case 6 -> listarConsultas();
                case 7 -> cancelarConsulta();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarMedico() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        System.out.print("Matrícula: ");
        int matricula = Integer.parseInt(scanner.nextLine());
        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("CRM: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        Medico medico = new Medico(nome, cpf, dataNascimento, matricula, salario, crm, especialidade);
        medicoDAO.salvar(medico);
        medicos.add(medico); // opcional: para exibir na tela sem buscar do banco
        System.out.println("Médico cadastrado com sucesso!");
    }

    private static void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        System.out.print("Convênio: ");
        String convenio = scanner.nextLine();
        Paciente paciente = new Paciente(nome, cpf, dataNascimento, convenio);
        pacienteDAO.salvar(paciente);
        pacientes.add(paciente); // opcional: para exibir na tela sem buscar do banco
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void listarMedicos() {
        List<Medico> lista = medicoDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "]");
            lista.get(i).exibirInfo();
            System.out.println();
        }
    }

    private static void listarPacientes() {
        List<Paciente> lista = pacienteDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "]");
            lista.get(i).exibirInfo();
            System.out.println();
        }
    }

    private static void agendarConsulta() {
        List<Medico> listaMedicos = medicoDAO.listarTodos();
        List<Paciente> listaPacientes = pacienteDAO.listarTodos();
        if (listaMedicos.isEmpty() || listaPacientes.isEmpty()) {
            System.out.println("Cadastre pelo menos um médico e um paciente antes de agendar.");
            return;
        }
        for (int i = 0; i < listaMedicos.size(); i++) {
            System.out.println("[" + i + "]");
            listaMedicos.get(i).exibirInfo();
            System.out.println();
        }
        System.out.print("Escolha o índice do médico: ");
        int idxMedico = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < listaPacientes.size(); i++) {
            System.out.println("[" + i + "]");
            listaPacientes.get(i).exibirInfo();
            System.out.println();
        }
        System.out.print("Escolha o índice do paciente: ");
        int idxPaciente = Integer.parseInt(scanner.nextLine());
        System.out.print("Data e hora da consulta (yyyy-MM-dd HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Consulta consulta = new Consulta(listaMedicos.get(idxMedico), listaPacientes.get(idxPaciente), dataHora);
        consultaDAO.salvar(consulta);
        consulta.agendar();
        consultas.add(consulta);
        consulta.imprimirRecibo();
    }

    private static void listarConsultas() {
        List<Consulta> lista = consultaDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.print("[" + i + "] ");
            lista.get(i).imprimirRecibo();
        }
    }

    private static void cancelarConsulta() {
        List<Consulta> lista = consultaDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma consulta para cancelar.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.print("[" + i + "] ");
            lista.get(i).imprimirRecibo();
        }
        System.out.print("Escolha o índice da consulta para cancelar: ");
        int idx = Integer.parseInt(scanner.nextLine());
        if (idx >= 0 && idx < lista.size()) {
            Consulta consulta = lista.get(idx);
            consulta.cancelar();
            consultaDAO.remover(consulta);
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
}
