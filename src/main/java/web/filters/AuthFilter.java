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

public class AuthFilter implements Filter {
    
    private static final String LOGIN_URL = "/login";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthFilter initialisé");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        
        // Pages accessibles sans authentification
        if (uri.endsWith("login") || uri.endsWith("login.jsp") || 
            uri.contains("/css/") || uri.contains("/js/")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Verifier si l'utilisateur est connecte
        HttpSession session = req.getSession(false);
        Object user = (session != null) ? session.getAttribute("user") : null;
        
        if (user == null) {
            // Non authentifie -> rediriger vers login
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/login");
        } else {
            // Authentifie -> continuer
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
        System.out.println("AuthFilter détruit");
    }
}