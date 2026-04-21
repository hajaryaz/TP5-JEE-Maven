package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utilitaire JPA : fournit un EntityManagerFactory singleton.
 * On l'ouvre une seule fois au démarrage et on le ferme à l'arrêt.
 */
public class JpaUtil {

    private static final String PERSISTENCE_UNIT = "produitPU";
    private static EntityManagerFactory emf;

    // Créer l'EntityManagerFactory au chargement de la classe
    static {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    /** Retourne un nouvel EntityManager (à fermer après usage). */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /** Fermer la factory (à appeler au shutdown de l'application). */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
