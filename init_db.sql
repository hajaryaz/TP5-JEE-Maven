-- =============================================
-- Script SQL pour TP3-JEE avec Hibernate/JPA
-- Exécuter dans MySQL avant de lancer l'appli
-- =============================================

CREATE DATABASE IF NOT EXISTS gestion_produits
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gestion_produits;

-- Les tables sont créées automatiquement par Hibernate (hbm2ddl.auto=update)
-- Ce script est optionnel, mais utile pour créer la base vide proprement.

-- Si vous voulez créer les tables manuellement (optionnel) :
/*
CREATE TABLE IF NOT EXISTS produit (
    id_produit BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    prix DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS utilisateur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);
*/
