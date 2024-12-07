package fi.tuni.compse140.project.service;

public interface AuthService {
    boolean verify(String username, String password);
}
