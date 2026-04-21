package services;

import java.util.List;
import dao.Produit;
import dao.ProduitDAO;
import dao.ProduitImpl;

/**
 * Couche service (métier) pour la gestion des produits.
 * Pattern Singleton : une seule instance partagée entre toutes les Servlets.
 * Délègue les opérations au DAO JPA/Hibernate.
 */
public class ProduitMetierImpl implements ProduitMetier {

    private static ProduitMetierImpl instance;
    private ProduitDAO dao;

    private ProduitMetierImpl() {
        dao = new ProduitImpl();
    }

    public static synchronized ProduitMetierImpl getInstance() {
        if (instance == null) {
            instance = new ProduitMetierImpl();
        }
        return instance;
    }

    @Override
    public void addProduit(Produit p) {
        dao.addProduit(p);
    }

    @Override
    public void deleteProduit(Long id) {
        dao.deleteProduit(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return dao.getAllProduits();
    }

    @Override
    public Produit getProduitById(Long id) {
        return dao.getProduitById(id);
    }

    @Override
    public void updateProduit(Produit p) {
        dao.updateProduit(p);
    }
}
