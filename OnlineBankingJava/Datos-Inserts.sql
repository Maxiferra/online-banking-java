-- SAMPLE DATA INSERTS
-- USE banco
-- Usuarios

-- Cuentas
INSERT INTO Cuenta (DNICliente, FechaCreacion, idTipoCuenta, numeroCuenta, CBU, Saldo, eliminado) VALUES
('12345678', '2023-01-15', 1, '100234567', '1700999820000001234567', 50000.00, FALSE),
('23456789', '2023-03-20', 2, '200345678', '1700999820000001234568', 75000.00, FALSE),
('34567890', '2023-06-10', 1, '300456789', '1700999820000001234579', 30000.00, FALSE),
('45678901', '2023-09-05', 2, '400567890', '1700999820000001234580', 100000.00, FALSE);

-- Prestamo
INSERT INTO Prestamo (IDCliente, IDCuenta, fechaAlta, importePedido, plazoMeses, importePorMes, CantidadCuotas, EstadoPrestamo) VALUES
(1, 1, '2023-02-01', 100000.00, 12, 9166.67, 12, 1), -- PrÃ©stamo aprobado para Juan
(2, 2, '2023-04-15', 200000.00, 24, 9583.33, 24, 2), -- PrÃ©stamo pendiente para MarÃ­a
(3, 3, '2023-07-01', 150000.00, 18, 9444.44, 18, 1), -- PrÃ©stamo aprobado para Carlos
(4, 4, '2023-10-01', 300000.00, 36, 9722.22, 36, 3); -- PrÃ©stamo rechazado para Silvia

-- Cuotas
INSERT INTO Cuotas (IDPrestamo, NumeroCuota, Monto, FechaPago) VALUES
-- Cuotas para el prÃ©stamo de Juan (pagadas)
(1, 1, 9166.67, '2023-03-01'),
(1, 2, 9166.67, '2023-04-01'),
(1, 3, 9166.67, '2023-05-01'),
(1, 4, 9166.67, '2023-06-01'),

-- Cuotas para el prÃ©stamo de Carlos (algunas pagadas)
(3, 1, 9444.44, '2023-08-01'),
(3, 2, 9444.44, '2023-09-01');

-- Movimiento
INSERT INTO Movimiento (fecha, detalle, idTipoMovimiento, importe, tipoMovimiento, IdCuenta) VALUES
-- Movimientos cuenta Juan
('2023-01-15', 'Alta de cuenta - Deposito inicial', 1, 50000.00, 'Ingreso', 1),
('2023-02-01', 'Deposito de prestamo', 2, 100000.00, 'Ingreso', 1),
('2023-03-01', 'Pago de cuota 1 prestamo', 3, 9166.67, 'Egreso', 1),
('2023-04-01', 'Transferencia a terceros', 4, 5000.00, 'Egreso', 1),

-- Movimientos cuenta MarÃ­a
('2023-03-20', 'Alta de cuenta - Deposito inicial', 1, 75000.00, 'Ingreso', 2),
('2023-04-15', 'Solicitud de prestamo', 2, 200000.00, 'Ingreso', 2),
('2023-05-01', 'Transferencia recibida', 4, 15000.00, 'Ingreso', 2),
('2023-05-15', 'Transferencia a comercio', 4, 25000.00, 'Egreso', 2),

-- Movimientos cuenta Carlos
('2023-06-10', 'Alta de cuenta - Deposito inicial', 1, 30000.00, 'Ingreso', 3),
('2023-07-01', 'DepÃ³sito de prestamo', 2, 150000.00, 'Ingreso', 3),
('2023-08-01', 'Pago de cuota 1 prestamo', 3, 9444.44, 'Egreso', 3),
('2023-09-01', 'Pago de cuota 2 prestamo', 3, 9444.44, 'Egreso', 3),

-- Movimientos cuenta Silvia
('2023-09-05', 'Alta de cuenta - Deposito inicial', 1, 100000.00, 'Ingreso', 4),
('2023-10-01', 'Solicitud de prestamo rechazada', 2, 0.00, 'Ingreso', 4),
('2023-10-15', 'Transferencia recibida sueldo', 4, 250000.00, 'Ingreso', 4),
('2023-11-01', 'Pago servicios', 4, 35000.00, 'Egreso', 4);