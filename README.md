# TP3-JEE — Gestion des Produits
## Stack : Maven + JPA/Hibernate 3 + MySQL + MVC2 (Servlets/JSP)

---

## Structure du projet

```
TP3-JEE-Maven/
├── pom.xml
├── init_db.sql
└── src/main/
    ├── java/
    │   ├── dao/
    │   │   ├── JpaUtil.java              ← EntityManagerFactory singleton
    │   │   ├── Produit.java              ← Entité JPA (@Entity)
    │   │   ├── Utilisateur.java          ← Entité JPA (@Entity)
    │   │   ├── ProduitDAO.java           ← Interface
    │   │   ├── ProduitImpl.java          ← Implémentation JPA
    │   │   ├── UtilisateurDAO.java       ← Interface
    │   │   ├── UtilisateurImpl.java      ← Implémentation JPA
    │   │   └── AppStartupListener.java   ← Init données au démarrage
    │   ├── services/
    │   │   ├── ProduitMetier.java
    │   │   ├── ProduitMetierImpl.java
    │   │   └── AuthentificationService.java
    │   └── web/
    │       ├── filters/
    │       │   ├── AuthFilter.java
    │       │   └── RoleFilter.java
    │       ├── ListProduitServlet.java
    │       ├── AddProduitServlet.java
    │       ├── EditProduitServlet.java
    │       ├── UpdateProduitServlet.java
    │       ├── DeleteProduitServlet.java
    │       ├── LoginServlet.java
    │       └── LogoutServlet.java
    ├── resources/
    │   └── META-INF/
    │       └── persistence.xml           ← Config JPA/Hibernate + MySQL
    └── webapp/
        ├── index.jsp
        ├── login.jsp
        ├── accessDenied.jsp
        ├── error404.jsp
        ├── error500.jsp
        ├── META-INF/MANIFEST.MF
        └── WEB-INF/
            └── web.xml
```

---

## Étapes pour faire tourner le projet

### 1. Créer la base MySQL

Ouvrir MySQL Workbench ou le terminal MySQL :

```sql
CREATE DATABASE gestion_produits CHARACTER SET utf8mb4;
```

> Hibernate créera les tables `produit` et `utilisateur` automatiquement au premier démarrage.

### 2. Configurer la connexion MySQL

Ouvrir `src/main/resources/META-INF/persistence.xml` et adapter :

```xml
<property name="hibernate.connection.url"
          value="jdbc:mysql://localhost:3306/gestion_produits?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true"/>
<property name="hibernate.connection.username" value="root"/>
<property name="hibernate.connection.password" value=""/>   <!-- votre mot de passe ici -->
```

### 3. Importer dans Eclipse

- `File → Import → Existing Maven Projects`
- Sélectionner le dossier `TP3-JEE-Maven`
- Eclipse télécharge les dépendances automatiquement

### 4. Configurer Tomcat dans Eclipse

- `Window → Preferences → Server → Runtime Environments → Add → Apache Tomcat 9`
- Clic droit sur le projet → `Run As → Run on Server`

### 5. Accéder à l'application

```
http://localhost:8080/TP3-JEE/login
```

**Comptes disponibles :**

| Login        | Mot de passe | Rôle  |
|--------------|-------------|-------|
| admin_hajar  | admin123    | ADMIN |
| user_hajar   | user123     | USER  |

> Les comptes et 2 produits de test sont insérés automatiquement au démarrage si la base est vide.

---

## Ce qui a changé par rapport à la version originale

| Avant (liste en mémoire) | Après (JPA/Hibernate + MySQL) |
|--------------------------|-------------------------------|
| `ProduitImpl` = ArrayList | `ProduitImpl` = EntityManager JPA |
| `UtilisateurImpl` = static List | `UtilisateurImpl` = requêtes JPQL |
| Données perdues au redémarrage | Données persistées en MySQL |
| Pas de BDD | Tables créées auto par Hibernate |
| Pas de `pom.xml` | Maven avec toutes les dépendances |

**Ce qui N'A PAS changé :**
- Architecture MVC2 (Servlets + JSP)
- Toutes les Servlets (web/)
- Tous les Filtres (AuthFilter, RoleFilter)
- Tous les JSP (index.jsp, login.jsp...)
- La logique métier (services/)
- Les interfaces DAO
- web.xml
