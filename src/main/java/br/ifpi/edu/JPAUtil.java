package br.ifpi.edu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // A fábrica de EntityManagers. Ela é a "ConnectionFactory" do JPA.
    // O nome "minhaUnidadeDePersistencia" deve ser o mesmo do <persistence-unit> no persistence.xml
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("minhaUnidadeDePersistencia");

    // Método para obter uma instância de EntityManager
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
