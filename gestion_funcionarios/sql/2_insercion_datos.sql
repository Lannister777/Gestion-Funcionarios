-- Script de Inserción de Datos Iniciales
USE gestion_funcionarios;

-- Datos para tipos_documento
INSERT INTO tipos_documento (nombre) VALUES 
('Cédula de Ciudadanía'),
('Cédula de Extranjería'),
('Pasaporte');

-- Datos para dependencias
INSERT INTO dependencias (nombre) VALUES 
('Recursos Humanos'),
('Tecnología y Sistemas'),
('Finanzas y Contabilidad'),
('Operaciones');

-- Datos para cargos
INSERT INTO cargos (nombre) VALUES 
('Director'),
('Desarrollador de Software'),
('Contador'),
('Analista de Datos'),
('Asistente Administrativo');

-- Datos iniciales de funcionarios (opcional)
INSERT INTO funcionarios (numero_documento, nombres, apellidos, correo, telefono, salario, fecha_ingreso, id_tipo_documento, id_dependencia, id_cargo) VALUES 
('1001234567', 'Juan Manuel', 'Pérez Silva', 'juan.perez@empresa.com', '3001234567', 4500000.00, '2023-01-15', 1, 2, 2),
('1009876543', 'María Camila', 'Gómez Ríos', 'maria.gomez@empresa.com', '3109876543', 6000000.00, '2022-06-01', 1, 1, 1),
('CE998877', 'John', 'Doe', 'john.doe@empresa.com', '3205556677', 5200000.00, '2023-08-10', 2, 3, 3);
