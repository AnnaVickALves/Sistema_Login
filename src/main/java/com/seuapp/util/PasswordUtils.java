package com.seuapp.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Classe utilitária para operações de segurança de senha,
 * como geração de hash e salt.
 */
public class PasswordUtils {

    private static final int SALT_LENGTH = 16; // 16 bytes = 128 bits

    /**
     * Gera um salt criptograficamente seguro.
     *
     * @return O salt gerado, codificado em Base64.
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Gera o hash de uma senha usando SHA-256 e um salt.
     *
     * @param password A senha em texto plano.
     * @param salt O salt a ser usado no hash.
     * @return O hash da senha, codificado em Base64.
     */
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Combina o salt com a senha antes de aplicar o hash
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            // Esta exceção não deve ocorrer se o algoritmo SHA-256 estiver disponível
            throw new RuntimeException("Algoritmo SHA-256 não encontrado", e);
        }
    }

    /**
     * Verifica se uma senha fornecida corresponde a um hash armazenado.
     *
     * @param providedPassword A senha fornecida pelo usuário.
     * @param salt O salt armazenado no banco de dados.
     * @param storedHash O hash da senha armazenado no banco de dados.
     * @return true se a senha for válida, false caso contrário.
     */
    public static boolean verifyPassword(String providedPassword, String salt, String storedHash) {
        String newHash = hashPassword(providedPassword, salt);
        return newHash.equals(storedHash);
    }
}