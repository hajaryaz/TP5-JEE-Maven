<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Produits</title>
    <!-- Bootstrap CSS (le nécessaire uniquement) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <div class="card">
        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
            <h2 class="mb-0">Gestion des Produits (MVC1)</h2>
            <div>
                Bienvenue, <strong>${sessionScope.user.login}</strong> (${sessionScope.user.role})
                <a href="logout" class="btn btn-sm btn-light ms-2">Déconnexion</a>
            </div>
        </div>
        
        <div class="card-body">
            <!-- Formulaire Ajouter / Modifier -->
            <form action="${produitEdit != null ? 'updateProduit' : 'addProduit'}" method="post" class="row g-3 mb-4">
                <input type="hidden" name="idProduit" value="${produitEdit.idProduit}" />
                
                <div class="col-md-4">
                    <label class="form-label">Nom</label>
                    <input type="text" class="form-control" name="nom" value="${produitEdit.nom}" required />
                </div>
                
                <div class="col-md-4">
                    <label class="form-label">Description</label>
                    <input type="text" class="form-control" name="description" value="${produitEdit.description}" required />
                </div>
                
                <div class="col-md-3">
                    <label class="form-label">Prix</label>
                    <input type="text" class="form-control" name="prix" value="${produitEdit.prix}" required />
                </div>
                
                <div class="col-md-1 d-flex align-items-end">
                    <input type="submit" class="btn ${produitEdit != null ? 'btn-warning' : 'btn-success'} w-100" 
                           value="${produitEdit != null ? 'Modifier' : 'Ajouter'}" />
                </div>
            </form>
            
            <hr/>
            
            <!-- Formulaire Recherche -->
            <form action="listProduits" method="get" class="row g-3 mb-4">
                <div class="col-md-3">
                    <label class="form-label">Rechercher par ID</label>
                    <input type="text" class="form-control" name="idProduit" placeholder="ID produit" />
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <input type="submit" class="btn btn-info" value="Rechercher" />
                </div>
            </form>
            
            <!-- Tableau des produits -->
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Prix</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${listeProduits}">
                        <tr>
                            <td>${p.idProduit}</td>
                            <td>${p.nom}</td>
                            <td>${p.description}</td>
                            <td>${p.prix}</td>
                            <td>
                                <a href="editProduit?id=${p.idProduit}" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="deleteProduit?id=${p.idProduit}" class="btn btn-sm btn-danger" 
                                   onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS (optionnel, nécessaire pour certains composants) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>