-- Script de Creación de Base de Datos y Tablas
-- Base de datos: gestion_funcionarios

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS gestion_funcionarios;
USE gestion_funcionarios;

-- Crear tabla tipos_documento
CREATE TABLE IF NOT EXISTS tipos_documento (
    id_tipo_documento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

-- Crear tabla dependencias
CREATE TABLE IF NOT EXISTS dependencias (
    id_dependencia INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla cargos
CREATE TABLE IF NOT EXISTS cargos (
    id_cargo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla funcionarios
CREATE TABLE IF NOT EXISTS funcionarios (
    id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(120) NOT NULL,
    telefono VARCHAR(20),
    salario DECIMAL(10,2) NOT NULL,
    fecha_ingreso DATE NOT NULL,
    id_tipo_documento INT NOT NULL,
    id_dependencia INT NOT NULL,
    id_cargo INT NOT NULL,
    
    -- Llaves foráneas
    CONSTRAINT fk_func_tipo_doc FOREIGN KEY (id_tipo_documento) REFERENCES tipos_documento(id_tipo_documento),
    CONSTRAINT fk_func_dependencia FOREIGN KEY (id_dependencia) REFERENCES dependencias(id_dependencia),
    CONSTRAINT fk_func_cargo FOREIGN KEY (id_cargo) REFERENCES cargos(id_cargo)
);
