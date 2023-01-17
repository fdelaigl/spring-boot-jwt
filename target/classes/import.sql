/* Populate tables */
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Andres', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('John', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Linus', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Jane', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Rasmus', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Erich',  'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Richard',  'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Ralph',  'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('John', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('James', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clientes (nombre, email, create_at, foto) VALUES('Bruce',  'bruce.lee@gmail.com', '2017-08-11', '');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());


INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

INSERT INTO users (username, password, enabled) VALUES('fer', '$2a$10$12TshzBgxom6cakh5O44dONGaCZoRIDwA6G.79BkluvvUE6AOEHOe', 1);
INSERT INTO users (username, password, enabled) VALUES('admin', '$2a$10$tQheiPVY7JCPjRmaOHIBXuhMzLCvoAEc89Q2nrpbNtZuPLYsxt0Qe', 1);

INSERT INTO authorities (user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES(2, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES(2, 'ROLE_ADMIN');