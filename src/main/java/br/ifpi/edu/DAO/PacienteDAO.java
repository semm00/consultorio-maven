package br.ifpi.edu.DAO;

import br.ifpi.edu.JPAUtil;
import br.ifpi.edu.Model.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class PacienteDAO {

	private static PacienteDAO instance;

	private PacienteDAO() {
	}

	public static PacienteDAO getInstance() {
		if (instance == null) {
			instance = new PacienteDAO();
		}
		return instance;
	}
	public void salvar(Paciente paciente) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(paciente);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Paciente buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.find(Paciente.class, id);
		} finally {
			em.close();
		}
	}

	public Paciente buscarPorCpf(String cpf) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			String jpql = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";
			TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
			query.setParameter("cpf", cpf);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public java.util.List<Paciente> buscarPorNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			String jpql = "SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(:nome)";
			TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
			query.setParameter("nome", "%" + nome + "%");
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public java.util.List<Paciente> listarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			String jpql = "SELECT p FROM Paciente p";
			jakarta.persistence.TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
}
