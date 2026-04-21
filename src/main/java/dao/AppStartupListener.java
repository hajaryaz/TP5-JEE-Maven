package dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener de démarrage de l'application.
 * Insère des utilisateurs et produits de test si la base est vide.
 * Remplace l'ancien bloc static { } de UtilisateurImpl.
 */
@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== Démarrage de l'application : initialisation des données ===");

        UtilisateurDAO userDAO = new UtilisateurImpl();
        ProduitDAO produitDAO = new ProduitImpl();

        // Insérer les utilisateurs seulement s'ils n'existent pas déjà
        if (userDAO.findByLogin("admin_hajar") == null) {
            userDAO.addUser(new Utilisateur("admin_hajar", "admin123", "ADMIN"));
        }
        if (userDAO.findByLogin("user_hajar") == null) {
            userDAO.addUser(new Utilisateur("user_hajar", "user123", "USER"));
        }

        // Insérer des produits de test si la liste est vide
        if (produitDAO.getAllProduits().isEmpty()) {
            produitDAO.addProduit(new Produit("PC 1", "Sony Vaio 1", 7000.0));
            produitDAO.addProduit(new Produit("PC 2", "Sony Vaio 2", 6000.0));
        }

        System.out.println("=== Données initialisées avec succès ===");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JpaUtil.close();
        System.out.println("=== Application arrêtée, EntityManagerFactory fermé ===");
    }
}
