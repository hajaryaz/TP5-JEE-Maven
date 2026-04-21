<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion - Gestion des Produits</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            
            <div class="card mt-5">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Connexion</h3>
                </div>
                
                <div class="card-body">
                    <form action="login" method="post">
                        <div class="mb-3">
                            <label class="form-label">Login :</label>
                            <input type="text" class="form-control" name="login" required />
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Mot de passe :</label>
                            <input type="password" class="form-control" name="password" required />
                        </div>
                        
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Se connecter</button>
                        </div>
                    </form>
                    
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger mt-3">
                            ${error}
                        </div>
                    </c:if>
                </div>
            </div>
            
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>