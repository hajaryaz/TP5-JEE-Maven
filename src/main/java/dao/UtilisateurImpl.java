package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implémentation de UtilisateurDAO avec JPA/Hibernate.
 * Les utilisateurs sont stockés en base MySQL.
 */
public class UtilisateurImpl implements UtilisateurDAO {

    @Override
    public Utilisateur findByLogin(String login) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Utilisateur> q = em.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.login = :login", Utilisateur.class);
            q.setParameter("login", login);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Utilisateur findByLoginAndPassword(String login, String password) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Utilisateur> q = em.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password",
                Utilisateur.class);
            q.setParameter("login", login);
            q.setParameter("password", password);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Utilisateur> getAllUsers() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void addUser(Utilisateur user) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erreur lors de l'ajout de l'utilisateur", e);
        } finally {
            em.close();
        }
    }
}
