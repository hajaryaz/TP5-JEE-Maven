package dao;

import java.util.List;

public interface UtilisateurDAO {
    Utilisateur findByLogin(String login);
    Utilisateur findByLoginAndPassword(String login, String password);
    List<Utilisateur> getAllUsers();
    void addUser(Utilisateur user);
}
