package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;

/*
 * Servlet qui gère l'affichage de la liste des produits et supporte la recherche par ID.
 * Fonctionnalités :
 * 1. Si le paramètre idProduit est fourni et valide, afficher uniquement ce produit
 * 2. Sinon, afficher tous les produits
 * Partie "Controller" dans MVC :
 * JSP (formulaire de recherche ou page d'accueil) -> Servlet -> Couche métier -> DAO -> JSP
 */
public class ListProduitServlet extends HttpServlet {
    // Singleton de la couche métier
    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Lecture du paramètre "idProduit" envoyé depuis le formulaire de recherche
        String idParam = req.getParameter("idProduit");
        // Liste de produits à afficher
        List<Produit> liste = new ArrayList<>();
        if (idParam != null && !idParam.isEmpty()) {
            try {
                // Conversion du paramètre en Long
                Long id = Long.parseLong(idParam);
                // Recherche du produit correspondant via la couche métier
                Produit p = metier.getProduitById(id);
                if (p != null) {
                    liste.add(p); // ajouter le produit trouvé à la liste
                }
                // Si le produit n'existe pas, la liste restera vide (option : message d'erreur)
            } catch (NumberFormatException e) {
                // Cas où l'utilisateur entre un ID invalide (non numérique)
                // On récupère tous les produits pour ne pas bloquer la page
                liste = metier.getAllProduits();
            }
        } else {
            // Aucun paramètre fourni : on affiche tous les produits
            liste = metier.getAllProduits();
        }
        // Stocker la liste dans la requête pour l'affichage JSP
        req.setAttribute("listeProduits", liste);
        // Forward vers JSP pour affichage (on garde les attributs de la requête)
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}