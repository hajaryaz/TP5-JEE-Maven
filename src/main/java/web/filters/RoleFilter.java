package web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Utilisateur;

public class RoleFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("RoleFilter initialisé");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        Utilisateur user = (session != null) ? (Utilisateur) session.getAttribute("user") : null;
        
        String uri = req.getRequestURI();
        
        // Actions réservées aux ADMIN
        boolean isAdminAction = uri.contains("deleteProduit") || 
                                 uri.contains("updateProduit") ||
                                 uri.contains("editProduit");
        
        if (isAdminAction && (user == null || !"ADMIN".equals(user.getRole()))) {
            // Pas admin -> accès refusé
            resp.sendRedirect(req.getContextPath() + "/accessDenied.jsp");
            return;
        }
        
        // Continuer
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        System.out.println("RoleFilter détruit");
    }
}