package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;

public class UpdateProduitServlet extends HttpServlet {

    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("idProduit"));
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Double prix = Double.parseDouble(req.getParameter("prix"));
        
        Produit p = new Produit();
        p.setIdProduit(id);
        p.setNom(nom);
        p.setDescription(description);
        p.setPrix(prix);
        
        metier.updateProduit(p);
        req.setAttribute("listeProduits", metier.getAllProduits());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}