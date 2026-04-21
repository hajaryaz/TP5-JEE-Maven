<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur interne</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            
            <div class="card mt-5 text-center">
                <div class="card-body">
                    <h1 class="display-1 text-danger">500</h1>
                    <h2 class="text-danger">Erreur interne du serveur</h2>
                    <p class="lead">Une erreur inattendue s'est produite. Veuillez réessayer plus tard.</p>
                    <hr/>
                    <a href="listProduits" class="btn btn-primary">Retour à l'accueil</a>
                    
                    <%-- Afficher les détails de l'erreur en mode développement --%>
                    <c:if test="${not empty pageContext.exception}">
                        <div class="alert alert-secondary mt-3 text-start">
                            <strong>Erreur :</strong> ${pageContext.exception.message}
                        </div>
                    </c:if>
                </div>
            </div>
            
        </div>
    </div>
</div>

</body>
</html>