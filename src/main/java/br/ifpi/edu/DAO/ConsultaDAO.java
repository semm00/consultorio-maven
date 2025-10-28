package br.ifpi.edu.DAO;

import br.ifpi.edu.Model.Consulta;
import br.ifpi.edu.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ConsultaDAO {
	public void salvar(Consulta consulta) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(consulta);
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

	public Consulta buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.find(Consulta.class, id);
		} finally {
			em.close();
		}
	}

	public void remover(Consulta consulta) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Consulta c = em.find(Consulta.class, consulta.getId());
			if (c != null) {
				em.remove(c);
			}
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

	public void removerPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Consulta consulta = em.find(Consulta.class, id);
			if (consulta != null) {
				em.remove(consulta);
			}
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

	public java.util.List<Consulta> listarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			String jpql = "SELECT c FROM Consulta c";
			jakarta.persistence.TypedQuery<Consulta> query = em.createQuery(jpql, Consulta.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
}
