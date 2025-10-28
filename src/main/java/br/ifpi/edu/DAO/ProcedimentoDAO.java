package br.ifpi.edu.DAO;

import java.util.List;

import br.ifpi.edu.JPAUtil;
import br.ifpi.edu.Model.Procedimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProcedimentoDAO {

    public void salvar(Procedimento procedimento) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(procedimento);
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

    public List<Procedimento> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Procedimento> query = em.createQuery("SELECT p FROM Procedimento p", Procedimento.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void removerPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Procedimento procedimento = em.find(Procedimento.class, id);
            if (procedimento != null) {
                em.remove(procedimento);
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
}
