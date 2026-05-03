# 👨‍💼 CRUD Gestión de Funcionarios (Java & MySQL)

Aplicación de escritorio desarrollada en **Java Swing** para el registro, consulta, edición y eliminación (CRUD) de la información interna de funcionarios de una empresa. El sistema hace uso del **Patrón Data Access Object (DAO)** y maneja datos relacionales con **MySQL/MariaDB**.

## ✨ Características Generales
* **100% Funcional:** Operaciones de guardado y manipulación de datos completamente integradas.
* **Interfaz Gráfica Intuitiva:** Hecha en Java Swing utilizando componentes como `JTable`, `JComboBox`, `JTextField` interactivos.
* **Manejo de Errores Limpio:** Implementación de excepciones estandarizadas (`DAOException`) en una capa superior en lugar de colapsar la base de datos, mostrándolas en un `JOptionPane`.
* **Arquitectura Escalable (Patrón DAO):** Separación total de responsabilidades, donde la base de datos no es mezclada con la interfaz gráfica.

## 🛠️ Tecnologías y Herramientas utilizadas
- **Lenguaje:** Java SE (JDK 17 o superior)
- **Framework UI:** Java Swing (Librería nativa)
- **Base de Datos:** MySQL o MariaDB
- **Driver de Conexión:** MySQL Connector/J (JDBC)

---

## ⚙️ Estructura del Proyecto y Patrones de Diseño
El código se encuentra organizado en en base a principios de encapsulación en paquetes:

* `conexion/` - Inicializa la comunicación nativa tipo Singleton con la BD (`Conexion.java`).
* `dao/` - Incluye la abstracción de contrato de métodos (`FuncionarioDAO.java`) e implementaciones por JDBC (`FuncionarioDAOImpl.java`).
* `excepciones/` - Filtro lógico y envolturas a errores SQL directos.
* `modelo/` - Mapeo Objeto-Relacional estándar de los empleados (`Funcionario.java`).
* `vista/` - Interfaz final y visual mostrada al operador (`FrmFuncionarios.java`).

---

## 🚀 Guía de Instalación y Ejecución

Si deseas correr este proyecto en tu computadora local, sigue estos sencillos pasos:

1. **Clona el Repositorio**
   ```bash
   git clone https://github.com/Lannister777/Gestion-Funcionarios.git
   ```
2. **Prepara tu Base de Datos**
   * Enciende tu servicio MySQL (XAMPP o Workbench local instalado).
   * Ingresa a tu gestor favorito (PhpMyAdmin, DBeaver) y ejecuta el Script de Creación:
     `sql/1_creacion_db.sql`
   * *(Opcional)* Carga datos iniciales al sistema ejecutando el Script Semilla:
     `sql/2_insercion_datos.sql`
   
3. **Instala o vincula el Driver en tu IDE (Eclipse/IntelliJ/Netbeans)**
   Asegúrate de agregar la dependencia externa `mysql-connector-java` a tu Build Path o carpeta `/lib`.

4. **Compilar y usar**
   Busca la clase `Main.java` en la carpeta origen e iníciala. Una pestaña se te abrirá para gestionar a tus funcionarios en la base de datos.
   
---
*Hecho para el aprendizaje de buenas metodologías y prácticas de ingeniería (POO).*
