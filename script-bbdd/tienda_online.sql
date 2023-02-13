DROP DATABASE IF EXISTS tienda_online;
CREATE DATABASE tienda_online CHARACTER SET utf8mb4;
USE tienda_online;

# Esta tabla tiene una clave primaria id_rol, que es un entero autoincrementado, 
# y una columna nombre_rol, que contiene el nombre del rol.
CREATE TABLE roles (
	id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60)
);

# La restricción CHECK se escribe después del tipo de datos y asegura que el valor en el campo fecha_nacimiento 
# cumpla con la condición especificada. La condición YEAR(fecha_nacimiento) < YEAR(CURDATE())-18 significa que 
# el año de la fecha de nacimiento debe ser menor que el año actual menos 18 años.
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    fecha_nacimiento DATE,
    email VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    id_rol INT,
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
    id_usuario INT,
    id_direccion INT,
    PRIMARY KEY (id_usuario, id_direccion),
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
    FOREIGN KEY (id_direccion) REFERENCES direcciones (id_direccion)
);

# Esta tabla tendrá como id, un entero autoincrementable, la información necesaria y una clave foranea
# que hace referencia al id del usuario
CREATE TABLE tarjetas_bancarias (
  id_tarjeta_bancaria INT AUTO_INCREMENT PRIMARY KEY,
  numero_tarjeta VARCHAR(255) UNIQUE,
  nombre_titular VARCHAR(50),
  fecha_caducidad DATE,
  cvv INT,
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

# Creamos tabla intermedia para poder hacer una relación de muchos a muchos, por lo que una tarjeta puede
# tener muchos usuarios y un usuario puede tener muchas tarjetas
CREATE TABLE usuarios_tarjetas_bancarias (
  id_usuario INT NOT NULL,
  id_tarjeta_bancaria INT NOT NULL,
  PRIMARY KEY (id_usuario, id_tarjeta_bancaria),
  FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
  FOREIGN KEY (id_tarjeta_bancaria) REFERENCES tarjetas_bancarias (id_tarjeta_bancaria)
);

# Creamos la tabla productos
CREATE TABLE productos (
  id_producto INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL UNIQUE,
  descripcion VARCHAR(100),
  price DOUBLE NOT NULL,
  stock INT NOT NULL,
  estado ENUM('Destacado', 'Oferta', 'Normal'),
  PRIMARY KEY (id_producto)
);

# Creamos la tabla pedidos con clave foranea id_usuario
CREATE TABLE pedidos (
  id_pedido INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha_realizacion DATE,
  id_usuario INT,
  id_producto INT,
  precioTotal DECIMAL(10,2) NOT NULL,
  estado ENUM('Comprado', 'En el carrito'),
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
  FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE aticulos_pedidos (
  id_pedArticulo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  Cantidad INT NOT NULL,
  FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

# Hacemos insert para rellenar las tablas con algunos campos por defecto
INSERT INTO usuarios (id_usuario, nombre, apellidos, fecha_nacimiento, email, contrasena)
VALUES (1, 'Juan', 'Pérez', '1997-05-10', 'juanperez@gmail.com', '123456');

INSERT INTO usuarios (id_usuario, nombre, apellidos, fecha_nacimiento, email, contrasena)
VALUES (2, 'María', 'García', '1995-02-28', 'mariagarcia@hotmail.com', '123456');

INSERT INTO roles (id_rol, nombre)
VALUES (1, 'ROLE_CLIENTE');

INSERT INTO roles (id_rol, nombre)
VALUES (2, 'ROLE_ADMIN');
commit;

#Creamos usuario y le damos permisos
create user utienda_proyecto identified by 'utienda';
grant all PRIVILEGES on tienda_online.* to utienda_proyecto;
