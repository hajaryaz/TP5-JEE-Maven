package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import services.ProduitMetier;
import services.ProduitMetierImpl;

public class DeleteProduitServlet extends HttpServlet {

    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        metier.deleteProduit(id);
        resp.sendRedirect("listProduits");
    }
}