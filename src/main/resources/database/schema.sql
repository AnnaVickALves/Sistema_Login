-- Script de criação da tabela de usuários
-- Usamos TEXT com CHECK para simular o tipo ENUM, que não é nativo do SQLite.
CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL, -- Coluna para o salt único por usuário
    role TEXT CHECK(role IN ('ADMIN', 'USER')) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserção do usuário administrador padrão se a tabela estiver vazia
-- A senha é 'admin'. O hash e o salt serão gerados pela aplicação na primeira execução.
-- Este INSERT é apenas um placeholder conceitual. A lógica real está no DatabaseManager.java.
