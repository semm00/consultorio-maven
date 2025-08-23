package br.ifpi.edu.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;


import br.ifpi.edu.Model.Medico;

import br.ifpi.edu.JPAUtil;

public class MedicoDAO {

    public void salvar(Medico medico) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(medico);
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

    public Medico buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Medico.class, id);
        } finally {
            em.close();
        }
    }

    public Medico buscarPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT m FROM Medico m WHERE m.cpf = :cpf";
            TypedQuery<Medico> query = em.createQuery(jpql, Medico.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void remover(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Medico medico = em.find(Medico.class, id);
            if (medico != null) {
                em.remove(medico);
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

    public java.util.List<Medico> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT m FROM Medico m";
            TypedQuery<Medico> query = em.createQuery(jpql, Medico.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
