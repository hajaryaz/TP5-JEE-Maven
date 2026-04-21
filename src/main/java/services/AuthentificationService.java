package services;

import dao.Utilisateur;
import dao.UtilisateurDAO;
import dao.UtilisateurImpl;

/**
 * Service d'authentification Singleton.
 * Utilise le DAO JPA pour vérifier les credentials en BDD.
 */
public class AuthentificationService {

    private static AuthentificationService instance;
    private UtilisateurDAO utilisateurDAO;

    private AuthentificationService() {
        utilisateurDAO = new UtilisateurImpl();
    }

    public static synchronized AuthentificationService getInstance() {
        if (instance == null) {
            instance = new AuthentificationService();
        }
        return instance;
    }

    public Utilisateur login(String login, String password) {
        return utilisateurDAO.findByLoginAndPassword(login, password);
    }

    public boolean isAdmin(Utilisateur user) {
        return user != null && "ADMIN".equals(user.getRole());
    }

    public boolean isUser(Utilisateur user) {
        return user != null && "USER".equals(user.getRole());
    }
}
