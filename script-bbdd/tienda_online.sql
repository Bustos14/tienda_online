DROP DATABASE IF EXISTS tienda_online;
CREATE DATABASE tienda_online CHARACTER SET utf8mb4;
USE tienda_online;

# Esta tabla tiene una clave primaria id_rol, que es un entero autoincrementado, 
# y una columna nombre_rol, que contiene el nombre del rol.
CREATE TABLE roles (
	id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60)
);

# Creamos tabla usuarios con su clave foranea id_rol de la tabla roles
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
    id_usuario INT AUTO_INCREMENT,
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
  id_usuario INT NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (id_producto)
);

# Creamos la tabla pedidos con clave foranea id_usuario
CREATE TABLE pedidos (
  id_pedido INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha_realizacion DATE,
  id_usuario INT,
  id_producto INT,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
  FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
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

INSERT INTO direcciones (id_direccion, codigo_postal, localidad, calle, numero)
VALUES (1, '28000', 'Madrid', 'Calle Mayor', '1');

INSERT INTO direcciones (id_direccion, codigo_postal, localidad, calle, numero, piso, letra)
VALUES (2, '28001', 'Madrid', 'Calle de Alcalá', '20', '2', 'A');

INSERT INTO tarjetas_bancarias (id_tarjeta_bancaria, numero_tarjeta, nombre_titular, fecha_caducidad, CVV)
VALUES (1, '1234 5678 9012 3456', 'Juan Pérez', '2024-05-01', '123');

INSERT INTO tarjetas_bancarias (id_tarjeta_bancaria, numero_tarjeta, nombre_titular, fecha_caducidad, CVV)
VALUES (2, '2345 6789 0123 4567', 'María García', '2023-12-01', '456');

INSERT INTO productos (id_producto, nombre, descripcion, price, stock) 
VALUES (1, "producto 1", 'Esta es la descripción del producto 1', 2, 10);

INSERT INTO productos (id_producto, nombre, descripcion, price, stock) 
VALUES (2, "producto 2", 'Esta es la descripción del producto 2', 1.5, 10);

INSERT INTO pedidos (id_pedido, fecha_realizacion,id_usuario, id_producto) 
VALUES (1, '2023-12-01', 1, 1);

INSERT INTO pedidos (id_pedido, fecha_realizacion,id_usuario, id_producto) 
VALUES (2, '2023-12-01', 2, 2);

commit;

#Creamos usuario y le damos permisos
create user utienda_proyecto identified by 'utienda';
grant all PRIVILEGES on tienda_online.* to utienda_proyecto;
