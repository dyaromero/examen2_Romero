-- =====================================
-- BASE DE DATOS: examen2_romero
-- =====================================

-- (Opcional si no la has creado)
-- CREATE DATABASE examen2_romero;
-- \c examen2_romero;

-- =====================================
-- LIMPIAR TABLAS (para evitar errores)
-- =====================================
DROP TABLE IF EXISTS apartamento CASCADE;
DROP TABLE IF EXISTS casa CASCADE;
DROP TABLE IF EXISTS inmueble CASCADE;
DROP TABLE IF EXISTS propietario CASCADE;

-- =====================================
-- CREACIÓN DE TABLAS
-- =====================================

CREATE TABLE propietario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE inmueble (
    numero INT PRIMARY KEY,
    fecha_compra DATE,
    estado VARCHAR(50),
    propietario_id INT,
    FOREIGN KEY (propietario_id) REFERENCES propietario(id)
);

CREATE TABLE casa (
    numero INT PRIMARY KEY,
    cantidad_pisos INT,
    FOREIGN KEY (numero) REFERENCES inmueble(numero)
);

CREATE TABLE apartamento (
    numero INT PRIMARY KEY,
    numero_piso INT,
    FOREIGN KEY (numero) REFERENCES inmueble(numero)
);

-- =====================================
-- INSERTAR DATOS
-- =====================================

-- Propietarios
INSERT INTO propietario (nombre) VALUES
('Daniel Rodriguez'),
('Melissa Romero');


-- Inmuebles
INSERT INTO inmueble (numero, fecha_compra, estado, propietario_id)
VALUES
(1, '2024-01-10', 'Disponible', 1),
(2, '2023-05-20', 'Vendido', 2);

-- Casa
INSERT INTO casa (numero, cantidad_pisos) VALUES
(1, 2);

-- Apartamento
INSERT INTO apartamento (numero, numero_piso) VALUES
(2, 5);


-- =====================================
-- CONSULTAS
-- =====================================

SELECT * FROM casa;
SELECT * FROM  inmueble;
SELECT * FROM  propietario;
SELECT * FROM  apartamento;
