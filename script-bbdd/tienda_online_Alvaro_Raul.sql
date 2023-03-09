DROP DATABASE IF EXISTS tienda_online;
CREATE DATABASE tienda_online CHARACTER SET utf8mb4;
USE tienda_online;

# Esta tabla tiene una clave primaria id_rol, que es un entero autoincrementado, 
# y una columna nombre_rol, que contiene el nombre del rol.
CREATE TABLE roles (
	id_rol INT PRIMARY KEY,
    nombre VARCHAR(60)
);

CREATE TABLE usuarios (
  username VARCHAR(100) PRIMARY KEY,
  nombre VARCHAR(100),
  apellidos VARCHAR(100),
  fecha_nacimiento DATE,
  fecha_registro DATE,
  contrasena VARCHAR(100),
  enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario_rol (
  username varchar(100),
  id_rol INT,
  PRIMARY KEY (username, id_rol),
  FOREIGN KEY (username) REFERENCES usuarios(username),
  FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

# Esta tabla tiene una clave primaria id_direccion, que es un entero autoincrementado,
# y la información necesaria para almacenar uan dirección
CREATE TABLE direcciones (
  id_direccion INT  AUTO_INCREMENT PRIMARY KEY,
  codigo_postal VARCHAR(100) NOT NULL,
  localidad VARCHAR(100) NOT NULL,
  calle VARCHAR(100),
  numero VARCHAR(100),
  piso VARCHAR(100),
  letra VARCHAR(100)
);

# Creamos la tabla usuario_dirección para establecer una relación de muchos a muchos entre la tabla
# 'usuarios' y la tabla 'direcciones', permitiendo al usuario que tenga varias direcciones y una direccion
# esté asociada a varios usuarios.
CREATE TABLE usuario_direccion (
    username VARCHAR(100),
    id_direccion INT,
    PRIMARY KEY (username, id_direccion),
    FOREIGN KEY (username) REFERENCES usuarios (username),
    FOREIGN KEY (id_direccion) REFERENCES direcciones (id_direccion)
);

# Esta tabla tendrá como id, un entero autoincrementable, la información necesaria y una clave foranea
# que hace referencia al id del usuario
CREATE TABLE tarjetas_bancarias (
  id_tarjeta_bancaria INT AUTO_INCREMENT PRIMARY KEY,
  numero_tarjeta VARCHAR(255) UNIQUE,
  nombre_titular VARCHAR(50),
  fecha_caducidad DATE,
  cvv INT
);

# Creamos tabla intermedia para poder hacer una relación de muchos a muchos, por lo que una tarjeta puede
# tener muchos usuarios y un usuario puede tener muchas tarjetas
CREATE TABLE usuarios_tarjetas_bancarias (
  username VARCHAR(100) NOT NULL,
  id_tarjeta_bancaria INT NOT NULL,
  PRIMARY KEY (username, id_tarjeta_bancaria),
  FOREIGN KEY (username) REFERENCES usuarios (username),
  FOREIGN KEY (id_tarjeta_bancaria) REFERENCES tarjetas_bancarias (id_tarjeta_bancaria)
);
CREATE TABLE tipos(
	id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(100)
    );
# Creamos la tabla productos
CREATE TABLE productos (
  id_producto INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL UNIQUE,
  descripcion VARCHAR(100),
  price DOUBLE NOT NULL,
  stock INT NOT NULL,
  estado ENUM('Destacado', 'Oferta', 'Normal'),
  img VARCHAR(255),
  id_tipo INT,
  FOREIGN KEY (id_tipo) REFERENCES tipos(id_tipo),
  PRIMARY KEY (id_producto)
);

  
# Creamos la tabla pedidos con clave foranea username
CREATE TABLE pedidos (
  id_pedido INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha_realizacion DATE,
  username varchar(100),
  precioTotal DECIMAL(10,2) NOT NULL,
  id_direccion INT,
  id_tarjeta_bancaria INT,
  estado ENUM('Comprado', 'En el carrito'),
  FOREIGN KEY (username) REFERENCES usuarios(username),
  FOREIGN KEY (id_direccion) REFERENCES direcciones(id_direccion),  
  FOREIGN KEY (id_tarjeta_bancaria) REFERENCES tarjetas_bancarias(id_tarjeta_bancaria)
);

CREATE TABLE aticulos_pedidos (
  id_pedArticulo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  Cantidad INT NOT NULL,
  FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

INSERT INTO roles (id_rol, nombre)
VALUES (1, 'ROLE_CLIENTE');
INSERT INTO roles (id_rol, nombre)
VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles (id_rol, nombre)
VALUES (3, 'ROLE_INVITADO');
INSERT INTO usuarios (username, nombre, apellidos, fecha_nacimiento, fecha_registro, contrasena, enabled)
VALUES ('admin', 'Admin', 'Admin', '2000-01-01', CURDATE(), 'password', TRUE);
INSERT INTO usuario_rol (username, id_rol) VALUES ('admin', 2);
commit;

#Creamos usuario y le damos permisos
create user utienda_proyecto identified by 'utienda';
grant all PRIVILEGES on tienda_online.* to utienda_proyecto;
