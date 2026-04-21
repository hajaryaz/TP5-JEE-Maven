package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Utilisateur;
import services.AuthentificationService;

public class LoginServlet extends HttpServlet {
    
    private AuthentificationService authService = AuthentificationService.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Afficher la page de login
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String error = null;
        
        // Validation des champs
        if (login == null || login.trim().isEmpty()) {
            error = "Login est obligatoire";
        } else if (password == null || password.trim().isEmpty()) {
            error = "Mot de passe est obligatoire";
        } else {
            // Tentative d'authentification
            Utilisateur user = authService.login(login, password);
            
            if (user != null) {
                // Connexion réussie
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                
                // Redirection selon le rôle
                if (authService.isAdmin(user)) {
                    resp.sendRedirect("listProduits");
                } else {
                    resp.sendRedirect("listProduits");
                }
                return;
            } else {
                error = "Login ou mot de passe incorrect";
            }
        }
        
        // En cas d'erreur, retour à la page login
        req.setAttribute("error", error);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}