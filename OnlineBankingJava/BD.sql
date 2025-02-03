CREATE DATABASE Banco;
USE Banco;

CREATE TABLE TipoUsuario (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    descripcion ENUM('admin', 'usuario') NOT NULL
);

CREATE TABLE TipoCuenta (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    descripcion ENUM('caja de ahorro', 'cuenta corriente') NOT NULL
);

CREATE TABLE TipoMovimiento (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    descripcion ENUM('alta de cuenta', 'alta de prestamos', 'pago de prestamos', 'transferencias') NOT NULL
);

CREATE TABLE Provincias (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Localidades (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    idProvincia INT,
    FOREIGN KEY (idProvincia) REFERENCES Provincias(ID)
);

CREATE TABLE Nacionalidades (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Usuario (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    idTipoUsuario INT,
    TxtUsuario VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (idTipoUsuario) REFERENCES TipoUsuario(ID)
);

CREATE TABLE Cliente (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    DNI VARCHAR(20) NOT NULL UNIQUE,
    CUIL VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo ENUM('Masculino', 'Femenino', 'Otro') NOT NULL,
    idNacionalidad INT,
    fechaNacimiento DATE NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    idLocalidad INT,
    idProvincia INT,
    Email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    idUsuario INT,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (idNacionalidad) REFERENCES Nacionalidades(ID),
    FOREIGN KEY (idLocalidad) REFERENCES Localidades(ID),
    FOREIGN KEY (idProvincia) REFERENCES Provincias(ID),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(ID)
);

CREATE TABLE Cuenta (
   ID INT AUTO_INCREMENT PRIMARY KEY,
    DNICliente VARCHAR(20) NOT NULL,  -- Corregido el nombre de la columna a 'DNICliente' en lugar de 'IDCliente'
    FechaCreacion DATE NOT NULL,
    idTipoCuenta INT,
    numeroCuenta VARCHAR(30) NOT NULL UNIQUE,
    CBU VARCHAR(22) NOT NULL UNIQUE,
    Saldo DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,  -- Agregada la columna 'eliminado'
    FOREIGN KEY (DNICliente) REFERENCES Cliente(DNI),  -- Corregido para que sea 'DNICliente' en lugar de 'IDCliente'
    FOREIGN KEY (idTipoCuenta) REFERENCES TipoCuenta(ID)
);

CREATE TABLE EstadoPrestamo (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    descripcion ENUM('aprobado', 'pendiente', 'rechazado') NOT NULL UNIQUE
);

CREATE TABLE Prestamo (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDCliente INT,
    IDCuenta INT,
    fechaAlta DATE NOT NULL,
    importePedido DECIMAL(10, 2) NOT NULL,
    plazoMeses INT NOT NULL,
    importePorMes DECIMAL(10, 2) NOT NULL,
    CantidadCuotas INT NOT NULL,
	EstadoPrestamo INT NOT NULL,
    FOREIGN KEY (IDCliente) REFERENCES Cliente(ID),
    FOREIGN KEY (IDCuenta) REFERENCES Cuenta(ID),
	FOREIGN KEY (EstadoPrestamo) REFERENCES EstadoPrestamo(ID)
);

CREATE TABLE Cuotas (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDPrestamo INT,
    NumeroCuota INT NOT NULL,
    Monto DECIMAL(10, 2) NOT NULL,
    FechaPago DATE,
    FOREIGN KEY (IDPrestamo) REFERENCES Prestamo(ID),
	UNIQUE (IDPrestamo, NumeroCuota)
);

CREATE TABLE Movimiento (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    detalle VARCHAR(100) NOT NULL,
    idTipoMovimiento INT,
    importe DECIMAL(10, 2) NOT NULL,
    tipoMovimiento ENUM('Ingreso', 'Egreso') NOT NULL,
    IdCuenta INT,
    FOREIGN KEY (idTipoMovimiento) REFERENCES TipoMovimiento(ID),
    FOREIGN KEY (IdCuenta) REFERENCES Cuenta(ID)
);
CREATE TABLE Admin (
  ID int NOT NULL,
  DNI varchar(20) NOT NULL,
  CUIL varchar(20) NOT NULL,
  nombre varchar(100) NOT NULL,
  apellido varchar(100) NOT NULL,
  Email varchar(100) NOT NULL,
  telefono varchar(20) NOT NULL,
  Estado int ,
  PRIMARY KEY (`ID`)
); 

INSERT INTO TipoUsuario (descripcion) VALUES ('admin');
INSERT INTO TipoUsuario (descripcion) VALUES ('usuario');

INSERT INTO EstadoPrestamo (descripcion) 
VALUES ('aprobado'),('pendiente'),('rechazado');

INSERT INTO TipoCuenta(descripcion) 
VALUES ('caja de ahorro'),('cuenta corriente');

INSERT INTO TipoMovimiento(descripcion) VALUES('alta de cuenta'),
 ('alta de prestamos'), ('pago de prestamos'), ('transferencias');

INSERT INTO Provincias (nombre) VALUES
('Buenos Aires'), 
('Catamarca'),   
('Chaco'),        
('Chubut'),       
('Cordoba'),      
('Corrientes'),   
('Entre Rios'),   
('Formosa'),      
('Jujuy'),        
('La Pampa'),     
('La Rioja'),     
('Mendoza'),      
('Misiones'),     
('Neuquen'),      
('Rio Negro'),    
('Salta'),       
('San Juan'),    
('San Luis'),     
('Santa Cruz'),   
('Santa Fe'),     
('Santiago del Estero'), 
('Tierra del Fuego'),    
('Tucuman');

INSERT INTO Localidades (nombre, idProvincia) VALUES
('La Plata', 1),                     
('Mar del Plata', 1),                
('San Fernando del Valle de Catamarca', 2), 
('Resistencia', 3),                 
('Comodoro Rivadavia', 4),           
('Cordoba', 5),                      
('Rosario', 20),                     
('Corrientes', 6),                   
('Parana', 7),                       
('Formosa', 8),                      
('San Salvador de Jujuy', 9),        
('Santa Rosa', 10),                  
('La Rioja', 11),                    
('Mendoza', 12),                     
('Posadas', 13),                     
('Neuquen', 14),                     
('Viedma', 15),                      
('Salta', 16),                       
('San Juan', 17),                   
('Villa Mercedes', 18),              
('San Luis', 18),                    
('Rio Gallegos', 19),                
('Santa Fe', 20),                    
('Santiago del Estero', 21),         
('Ushuaia', 22),                     
('San Miguel de Tucuman', 23),      
('Tafi Viejo', 23); 

INSERT INTO Nacionalidades (nombre) VALUES
('Argentina'),
('Brasil'),
('Chile'),
('Uruguay'),
('Paraguay'),
('Bolivia'),
('PerÃº'),
('Ecuador'),
('Colombia'),
('Venezuela'),
('Mexico'),
('Estados Unidos'),
('CanadÃ¡'),
('España'),
('Francia'),
('Italia'),
('Alemania'),
('Reino Unido'),
('Japon'),
('China');

INSERT INTO Usuario (idTipoUsuario, TxtUsuario, Password, eliminado) VALUES
(2, 'usuario1', 'password123', FALSE),
(2, 'usuario2', 'password123', FALSE),
(2, 'usuario3', 'password123', TRUE),
(2, 'usuario4', 'password123', FALSE),
(2, 'usuario5', 'password123', TRUE),
(2, 'usuario6', 'password123', FALSE),
(2, 'usuario7', 'password123', TRUE),
(2, 'usuario8', 'password123', FALSE),
(2, 'usuario9', 'password123', FALSE),
(2, 'usuario10', 'password123', TRUE),
(2, 'usuario11', 'password123', FALSE),
(2, 'usuario12', 'password123', TRUE),
(2, 'usuario13', 'password123', FALSE),
(2, 'usuario14', 'password123', TRUE),
(2, 'usuario15', 'password123', FALSE),
(2, 'usuario16', 'password123', TRUE),
(2, 'usuario17', 'password123', FALSE),
(2, 'usuario18', 'password123', FALSE),
(2, 'usuario19', 'password123', TRUE),
(2, 'usuario20', 'password123', FALSE),
(1, 'admin1', 'admin', FALSE),
(1, 'admin2', 'admin', FALSE),
(1, 'admin3', 'admin', FALSE),
(1, 'admin4', 'admin', FALSE),
(1, 'admin5', 'admin', FALSE),
(1, 'admin6', 'admin', FALSE),
(1, 'admin7', 'admin', FALSE),
(1, 'admin8', 'admin', FALSE),
(1, 'admin9', 'admin', FALSE),
(1, 'admin10', 'admin', FALSE),
(1, 'admin11', 'admin', FALSE),
(1, 'admin12', 'admin', FALSE),
(1, 'admin13', 'admin', FALSE),
(1, 'admin14', 'admin', FALSE),
(1, 'admin15', 'admin', FALSE);

INSERT INTO Cliente (DNI, CUIL, nombre, apellido, sexo, idNacionalidad, fechaNacimiento, direccion, idLocalidad, idProvincia, Email, telefono, idUsuario, eliminado) VALUES
('12345678', '20-12345678-3', 'Carlos', 'Perez', 'Masculino', 1, '1985-06-15', 'Calle Falsa 123', 1, 1, 'carlos.perez@gmail.com', '123456789', 1, FALSE),
('23456789', '20-23456789-6', 'Maria', 'Gonzalez', 'Femenino', 2, '1990-11-22', 'Av. Siempre Viva 456', 2, 2, 'maria.gonzalez@gmail.com', '987654321', 2, TRUE),
('34567890', '20-34567890-9', 'Juan', 'Lopez', 'Masculino', 3, '1982-09-10', 'Pasaje del Sol 789', 3, 3, 'juan.lopez@gmail.com', '456789123', 3, FALSE),
('45678901', '20-45678901-2', 'Laura', 'Martinez', 'Femenino', 4, '1995-03-25', 'Ruta 40 km 50', 4, 4, 'laura.martinez@gmail.com', '789123456', 4, TRUE),
('56789012', '20-56789012-5', 'Pedro', 'Sanchez', 'Masculino', 5, '1987-07-04', 'Calle 1234', 6, 6, 'pedro.sanchez@gmail.com', '321456987', 5, FALSE),
('67890123', '20-67890123-8', 'Ana', 'Fernandez', 'Femenino', 6, '1992-12-18', 'Av. Libertador 567', 7, 7, 'ana.fernandez@gmail.com', '654789321', 6, TRUE),
('78901234', '20-78901234-1', 'Luis', 'Ramirez', 'Masculino', 7, '1980-01-30', 'Pasaje Colon 890', 13, 13, 'luis.ramirez@gmail.com', '987123654', 7, FALSE),
('89012345', '20-89012345-4', 'Carla', 'Herrera', 'Femenino', 8, '1994-05-16', 'Calle Mitre 123', 8, 8, 'carla.herrera@gmail.com', '123987654', 8, FALSE),
('90123456', '20-90123456-7', 'Diego', 'Mendoza', 'Masculino', 9, '1988-08-29', 'Av. Rivadavia 456', 9, 9, 'diego.mendoza@gmail.com', '789456123', 9, TRUE),
('01234567', '20-01234567-0', 'Lucia', 'Ortega', 'Femenino', 10, '1993-10-05', 'Calle Principal 789', 10, 10, 'lucia.ortega@gmail.com', '456123789', 10, FALSE),
('12345098', '20-12345098-1', 'Gustavo', 'Paredes', 'Masculino', 11, '1981-04-14', 'Ruta Nacional 50', 11, 11, 'gustavo.paredes@gmail.com', '321789654', 11, TRUE),
('23456098', '20-23456098-2', 'Cecilia', 'Vargas', 'Femenino', 12, '1996-02-20', 'Pasaje Estrella 345', 12, 12, 'cecilia.vargas@gmail.com', '987654321', 12, FALSE),
('34567098', '20-34567098-3', 'Jorge', 'Benitez', 'Masculino', 13, '1983-07-19', 'Calle San Martin 678', 13, 13, 'jorge.benitez@gmail.com', '654987123', 13, TRUE),
('45678098', '20-45678098-4', 'Marta', 'Aguirre', 'Femenino', 14, '1991-06-25', 'Calle Independencia 901', 14, 14, 'marta.aguirre@gmail.com', '789654321', 14, FALSE),
('56789098', '20-56789098-5', 'Roberto', 'Castro', 'Masculino', 15, '1989-03-11', 'Calle Belgrano 234', 15, 15, 'roberto.castro@gmail.com', '123456987', 15, TRUE),
('67890098', '20-67890098-6', 'Paula', 'Morales', 'Femenino', 16, '1997-08-02', 'Av. Alem 567', 16, 16, 'paula.morales@gmail.com', '456789321', 16, FALSE),
('78901098', '20-78901098-7', 'Daniel', 'Gomez', 'Masculino', 17, '1986-11-15', 'Ruta Provincial 30', 17, 17, 'daniel.gomez@gmail.com', '987123654', 17, TRUE),
('89012098', '20-89012098-8', 'Florencia', 'Sosa', 'Femenino', 18, '1994-09-23', 'Calle General Paz 890', 19, 19, 'florencia.sosa@gmail.com', '654123987', 18, FALSE),
('90123098', '20-90123098-9', 'Enrique', 'Ponce', 'Masculino', 19, '1992-05-01', 'Av. Mitre 123', 19, 19, 'enrique.ponce@gmail.com', '321987654', 19, TRUE),
('01234098', '20-01234098-0', 'Valeria', 'Navarro', 'Femenino', 20, '1984-12-30', 'Pasaje Cordoba 456', 20, 20, 'valeria.navarro@gmail.com', '789321456', 20, FALSE);


-- Admin
INSERT INTO Admin (ID,DNI , CUIL, nombre, apellido,Email,telefono, Estado)
VALUES
(21,'12345667', '20-12345667-2', 'admin1', 'admin', 'admin1@gmail.com', 1123435657, 1),
(22,'12345668', '20-12345668-2', 'admin2', 'admin', 'admin2@gmail.com', 1123435658, 1),
(23,'12345669', '20-12345669-2', 'admin3', 'admin', 'admin3@gmail.com', 1123435659, 1),
(24,'12345670', '20-12345670-2', 'admin4', 'admin', 'admin4@gmail.com', 1123435660, 1),
(25,'12345671', '20-12345671-2', 'admin5', 'admin', 'admin5@gmail.com', 1123435661, 1),
(26,'12345672', '20-12345672-2', 'admin6', 'admin', 'admin6@gmail.com', 1123435662, 1),
(27,'12345673', '20-12345673-2', 'admin7', 'admin', 'admin7@gmail.com', 1123435663, 1),
(28,'12345674', '20-12345674-2', 'admin8', 'admin', 'admin8@gmail.com', 1123435664, 1),
(29,'12345675', '20-12345675-2', 'admin9', 'admin', 'admin9@gmail.com', 1123435665, 1),
(30,'12345676', '20-12345676-2', 'admin10', 'admin', 'admin10@gmail.com', 1123435666, 1),
(31,'12345677', '20-12345677-2', 'admin11', 'admin', 'admin11@gmail.com', 1123435667, 1),
(32,'12345678', '20-12345678-2', 'admin12', 'admin', 'admin12@gmail.com', 1123435668, 1),
(33,'12345679', '20-12345679-2', 'admin13', 'admin', 'admin13@gmail.com', 1123435669, 1),
(34,'12345680', '20-12345680-2', 'admin14', 'admin', 'admin14@gmail.com', 1123435670, 1),
(35,'12345681', '20-12345681-2', 'admin15', 'admin', 'admin15@gmail.com', 1123435671, 1);