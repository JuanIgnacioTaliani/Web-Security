CREATE DATABASE db_web_security;
USE db_web_security;

CREATE TABLE roles (
	id_rol INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(10) NOT NULL,
    PRIMARY KEY (id_rol)
);

CREATE TABLE usuarios (
	id_usuario INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(80) NOT NULL,
    nombre_usuario VARCHAR(45) NOT NULL,
    contrasena VARCHAR(5) NOT NULL,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE roles_por_usuario (
	id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    PRIMARY KEY (id_usuario, id_rol),
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
    FOREIGN KEY (id_rol) REFERENCES roles (id_rol)
);