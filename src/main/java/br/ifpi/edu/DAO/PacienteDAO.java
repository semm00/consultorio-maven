package br.ifpi.edu.DAO;

import br.ifpi.edu.JPAUtil;
import br.ifpi.edu.Model.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PacienteDAO {
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
