package br.ifpi.edu.DAO;

import java.util.List;

import br.ifpi.edu.JPAUtil;
import br.ifpi.edu.Model.Exame;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ExameDAO {

    public void salvar(Exame exame) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(exame);
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

    public List<Exame> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Exame> query = em.createQuery("SELECT e FROM Exame e", Exame.class);
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
            Exame exame = em.find(Exame.class, id);
            if (exame != null) {
                em.remove(exame);
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
