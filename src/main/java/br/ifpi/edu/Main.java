
package br.ifpi.edu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.ifpi.edu.DAO.ConsultaDAO;
import br.ifpi.edu.DAO.ExameDAO;
import br.ifpi.edu.DAO.MedicoDAO;
import br.ifpi.edu.DAO.PacienteDAO;
import br.ifpi.edu.DAO.ProcedimentoDAO;
import br.ifpi.edu.Model.Consulta;
import br.ifpi.edu.Model.Exame;
import br.ifpi.edu.Model.Medico;
import br.ifpi.edu.Model.Paciente;
import br.ifpi.edu.Model.Procedimento;
import br.ifpi.edu.factory.AgendamentoDados;
import br.ifpi.edu.factory.ConsultaFactory;
import br.ifpi.edu.factory.ExameFactory;
import br.ifpi.edu.factory.ProcedimentoFactory;

public class Main {
    private static final MedicoDAO medicoDAO = new MedicoDAO();
    private static final PacienteDAO pacienteDAO = new PacienteDAO();
    private static final ConsultaDAO consultaDAO = new ConsultaDAO();
    private static final ExameDAO exameDAO = new ExameDAO();
    private static final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private static final ConsultaFactory consultaFactory = new ConsultaFactory(consultaDAO);
    private static final ExameFactory exameFactory = new ExameFactory(exameDAO);
    private static final ProcedimentoFactory procedimentoFactory = new ProcedimentoFactory(procedimentoDAO);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Força a criação das tabelas JPA/Hibernate ao inicializar
        br.ifpi.edu.JPAUtil.getEntityManager().close();

        int opcao;
        do {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1. Médicos");
            System.out.println("2. Pacientes");
            System.out.println("3. Agendamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> menuMedicos();
                case 2 -> menuPacientes();
                case 3 -> menuAgendamentos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuMedicos() {
        int opcao;
        do {
            System.out.println("\n-- Médicos --");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarMedico();
                case 2 -> listarMedicos();
                case 0 -> {
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuPacientes() {
        int opcao;
        do {
            System.out.println("\n-- Pacientes --");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> listarPacientes();
                case 0 -> {
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuAgendamentos() {
        int opcao;
        do {
            System.out.println("\n-- Agendamentos --");
            System.out.println("1. Agendar atendimento");
            System.out.println("2. Listar consultas");
            System.out.println("3. Cancelar consulta");
            System.out.println("4. Listar exames");
            System.out.println("5. Cancelar exame");
            System.out.println("6. Listar procedimentos");
            System.out.println("7. Cancelar procedimento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> agendarAtendimento();
                case 2 -> listarConsultas();
                case 3 -> cancelarConsulta();
                case 4 -> listarExames();
                case 5 -> cancelarExame();
                case 6 -> listarProcedimentos();
                case 7 -> cancelarProcedimento();
                case 0 -> {
                }
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

    private static void agendarAtendimento() {
    System.out.println("Tipo de atendimento:");
        System.out.println("1. Consulta");
        System.out.println("2. Exame");
        System.out.println("3. Procedimento");
        System.out.print("Escolha uma opção: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        List<Medico> listaMedicos = medicoDAO.listarTodos();
        List<Paciente> listaPacientes = pacienteDAO.listarTodos();
        if (listaPacientes.isEmpty()) {
            System.out.println("Cadastre pelo menos um paciente antes de agendar.");
            return;
        }

        Medico medicoSelecionado = null;
        if (tipo == 1 || tipo == 3) {
            if (listaMedicos.isEmpty()) {
                System.out.println("Cadastre pelo menos um médico antes de agendar esse tipo de atendimento.");
                return;
            }
            exibirColecao("Médicos", listaMedicos);
            System.out.print("Escolha o índice do médico: ");
            int idxMedico = Integer.parseInt(scanner.nextLine());
            if (idxMedico < 0 || idxMedico >= listaMedicos.size()) {
                System.out.println("Índice inválido para médico.");
                return;
            }
            medicoSelecionado = listaMedicos.get(idxMedico);
        } else if (tipo == 2 && !listaMedicos.isEmpty()) {
            System.out.print("Deseja vincular um médico ao exame? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.startsWith("s")) {
                exibirColecao("Médicos", listaMedicos);
                System.out.print("Escolha o índice do médico: ");
                int idxMedico = Integer.parseInt(scanner.nextLine());
                if (idxMedico < 0 || idxMedico >= listaMedicos.size()) {
                    System.out.println("Índice inválido para médico.");
                    return;
                }
                medicoSelecionado = listaMedicos.get(idxMedico);
            }
        }

        exibirColecao("Pacientes", listaPacientes);
        System.out.print("Escolha o índice do paciente: ");
        int idxPaciente = Integer.parseInt(scanner.nextLine());
        if (idxPaciente < 0 || idxPaciente >= listaPacientes.size()) {
            System.out.println("Índice inválido para paciente.");
            return;
        }
        Paciente pacienteSelecionado = listaPacientes.get(idxPaciente);

        System.out.print("Data e hora do atendimento (yyyy-MM-dd HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        AgendamentoDados dadosBase = new AgendamentoDados(medicoSelecionado, pacienteSelecionado, dataHora, null);

        switch (tipo) {
            case 1 -> {
                Consulta consulta = consultaFactory.criarEAgendar(dadosBase);
                consulta.imprimirRecibo();
            }
            case 2 -> {
                System.out.print("Informe qual é o exame: ");
                String tipoExame = scanner.nextLine();
                Exame exame = exameFactory.criarEAgendar(dadosBase.comDescricao(tipoExame));
                System.out.println(exame.resumo());
            }
            case 3 -> {
                if (medicoSelecionado == null) {
                    System.out.println("Procedimentos devem estar vinculados a um médico.");
                    return;
                }
                System.out.print("Informe o nome do procedimento: ");
                String nomeProcedimento = scanner.nextLine();
                Procedimento procedimento = procedimentoFactory.criarEAgendar(dadosBase.comDescricao(nomeProcedimento));
                System.out.println(procedimento.resumo());
            }
            default -> System.out.println("Tipo de atendimento inválido.");
        }
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
            consultaDAO.removerPorId(consulta.getId());
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void listarExames() {
        List<Exame> lista = exameDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum exame agendado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i).resumo());
        }
    }

    private static void cancelarExame() {
        List<Exame> lista = exameDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum exame para cancelar.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i).resumo());
        }
        System.out.print("Escolha o índice do exame para cancelar: ");
        int idx = Integer.parseInt(scanner.nextLine());
        if (idx >= 0 && idx < lista.size()) {
            Exame exame = lista.get(idx);
            exame.cancelar();
            exameDAO.removerPorId(exame.getId());
            System.out.println("Exame cancelado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void listarProcedimentos() {
        List<Procedimento> lista = procedimentoDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum procedimento agendado.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i).resumo());
        }
    }

    private static void cancelarProcedimento() {
        List<Procedimento> lista = procedimentoDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum procedimento para cancelar.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i).resumo());
        }
        System.out.print("Escolha o índice do procedimento para cancelar: ");
        int idx = Integer.parseInt(scanner.nextLine());
        if (idx >= 0 && idx < lista.size()) {
            Procedimento procedimento = lista.get(idx);
            procedimento.cancelar();
            procedimentoDAO.removerPorId(procedimento.getId());
            System.out.println("Procedimento cancelado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static <T> void exibirColecao(String titulo, List<T> itens) {
        System.out.println("=== " + titulo + " ===");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("[" + i + "] " + itens.get(i));
        }
        System.out.println();
    }
}
