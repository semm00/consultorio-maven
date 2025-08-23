package br.ifpi.edu.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import br.ifpi.edu.Model.Pessoa;
import br.ifpi.edu.JPAUtil;

public class PessoaDAO {

    public void salvar(Pessoa pessoa) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(pessoa);
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

    public Pessoa buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }

    public Pessoa buscarPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf";
            TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
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
            Pessoa pessoa = em.find(Pessoa.class, id);
            if (pessoa != null) {
                em.remove(pessoa);
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

    public java.util.List<Pessoa> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pessoa p";
            TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
