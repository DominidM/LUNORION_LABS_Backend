-- ============================================================
-- SEED DATA - COLUMNAS EXACTAS DE INIT.SQL
-- ============================================================

-- 1. VEHICULO
INSERT INTO vehiculo (id, tenant_id, cliente_id, placa, numero_chasis, numero_motor, marca, modelo, anio, color, kilometraje, tipo_combustible)
VALUES ('44444444-4444-4444-4444-444444444401', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333301', 'ABC-123', 'CHASIS-001', 'MOTOR-001', 'Toyota', 'Corolla', 2020, 'Blanco', 50000, 'GASOLINA');

INSERT INTO vehiculo (id, tenant_id, cliente_id, placa, numero_chasis, numero_motor, marca, modelo, anio, color, kilometraje, tipo_combustible)
VALUES ('44444444-4444-4444-4444-444444444402', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333301', 'DEF-456', 'CHASIS-002', 'MOTOR-002', 'Honda', 'Civic', 2021, 'Negro', 35000, 'GASOLINA');

INSERT INTO vehiculo (id, tenant_id, cliente_id, placa, numero_chasis, numero_motor, marca, modelo, anio, color, kilometraje, tipo_combustible)
VALUES ('44444444-4444-4444-4444-444444444403', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333302', 'GHI-789', 'CHASIS-003', 'MOTOR-003', 'Nissan', 'Sentra', 2019, 'Rojo', 60000, 'DIESEL');

INSERT INTO vehiculo (id, tenant_id, cliente_id, placa, numero_chasis, numero_motor, marca, modelo, anio, color, kilometraje, tipo_combustible)
VALUES ('44444444-4444-4444-4444-444444444404', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333304', 'JKL-012', 'CHASIS-004', 'MOTOR-004', 'Kia', 'Rio', 2022, 'Azul', 15000, 'GASOLINA');

-- 2. ORDEN_TRABAJO
INSERT INTO orden_trabajo (id, tenant_id, cliente_id, vehiculo_id, tecnico_id, numero_ot, estado, motivo_ingreso, kilometraje_ingreso, fecha_ingreso, fecha_prometida, total_repuestos, total_mano_obra, total, usuario_creo, usuario_cerro)
VALUES ('88888888-8888-8888-8888-888888888801', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333301', '44444444-4444-4444-4444-444444444401', '77777777-7777-7777-7777-777777777701', 'OT-2024-001', 'COMPLETADA', 'Cambio de aceite y filtro, revision general', 50000, CURRENT_TIMESTAMP - INTERVAL '5 days', '2024-06-20', 63.00, 120.00, 183.00, '22222222-2222-2222-2222-222222222201', '22222222-2222-2222-2222-222222222201');

INSERT INTO orden_trabajo (id, tenant_id, cliente_id, vehiculo_id, tecnico_id, numero_ot, estado, motivo_ingreso, kilometraje_ingreso, fecha_ingreso, fecha_prometida, total_repuestos, total_mano_obra, total, usuario_creo, usuario_cerro)
VALUES ('88888888-8888-8888-8888-888888888802', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333302', '44444444-4444-4444-4444-444444444403', '77777777-7777-7777-7777-777777777701', 'OT-2024-002', 'EN_PROCESO', 'Reparacion de frenos delanteros y traseros', 35000, CURRENT_TIMESTAMP - INTERVAL '2 days', '2024-07-05', 170.00, 180.00, 350.00, '22222222-2222-2222-2222-222222222201', NULL);

INSERT INTO orden_trabajo (id, tenant_id, cliente_id, vehiculo_id, tecnico_id, numero_ot, estado, motivo_ingreso, kilometraje_ingreso, fecha_ingreso, fecha_prometida, total_repuestos, total_mano_obra, total, usuario_creo, usuario_cerro)
VALUES ('88888888-8888-8888-8888-888888888803', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333304', '44444444-4444-4444-4444-444444444404', '77777777-7777-7777-7777-777777777701', 'OT-2024-003', 'PENDIENTE', 'Diagnostico de falla en encendido', 12000, CURRENT_TIMESTAMP, NULL, 0.00, 60.00, 60.00, '22222222-2222-2222-2222-222222222202', NULL);

-- 3. OT_INSUMO
INSERT INTO ot_insumo (id, ot_id, producto_id, cantidad, precio_unitario, subtotal)
VALUES (gen_random_uuid(), '88888888-8888-8888-8888-888888888801', '66666666-6666-6666-6666-666666666601', 1, 35.00, 35.00);

INSERT INTO ot_insumo (id, ot_id, producto_id, cantidad, precio_unitario, subtotal)
VALUES (gen_random_uuid(), '88888888-8888-8888-8888-888888888801', '66666666-6666-6666-6666-666666666602', 1, 28.00, 28.00);

INSERT INTO ot_insumo (id, ot_id, producto_id, cantidad, precio_unitario, subtotal)
VALUES (gen_random_uuid(), '88888888-8888-8888-8888-888888888802', '66666666-6666-6666-6666-666666666603', 2, 85.00, 170.00);

-- 4. OT_MANO_OBRA
INSERT INTO ot_mano_obra (id, ot_id, tecnico_id, servicio_descripcion, horas, tarifa_hora, subtotal)
VALUES (gen_random_uuid(), '88888888-8888-8888-8888-888888888801', '77777777-7777-7777-7777-777777777701', 'Mantenimiento Preventivo', 2, 60.00, 120.00);

INSERT INTO ot_mano_obra (id, ot_id, tecnico_id, servicio_descripcion, horas, tarifa_hora, subtotal)
VALUES (gen_random_uuid(), '88888888-8888-8888-8888-888888888802', '77777777-7777-7777-7777-777777777701', 'Reparacion General', 3, 60.00, 180.00);

INSERT INTO ot_mano_obra (id, ot_id, tecnico_id, servicio_descripcion, horas, tarifa_hora, subtotal)
VALUES (gen_random_uuid(), '88888888-8888-8888-8888-888888888803', '77777777-7777-7777-7777-777777777701', 'Diagnostico', 1, 60.00, 60.00);

-- 5. CITA
INSERT INTO cita (id, tenant_id, cliente_id, vehiculo_id, tecnico_id, servicio_descripcion, fecha_hora, duracion_minutos, estado, recordatorio_enviado, notificar_whatsapp, usuario_creo)
VALUES (gen_random_uuid(), '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333301', '44444444-4444-4444-4444-444444444401', '77777777-7777-7777-7777-777777777701', 'Cambio de aceite', CURRENT_TIMESTAMP + INTERVAL '2 hours', 60, 'CONFIRMADA', false, false, '22222222-2222-2222-2222-222222222201');

INSERT INTO cita (id, tenant_id, cliente_id, vehiculo_id, tecnico_id, servicio_descripcion, fecha_hora, duracion_minutos, estado, recordatorio_enviado, notificar_whatsapp, usuario_creo)
VALUES (gen_random_uuid(), '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333304', '44444444-4444-4444-4444-444444444404', '77777777-7777-7777-7777-777777777701', 'Revision de frenos', CURRENT_TIMESTAMP + INTERVAL '1 day', 90, 'PENDIENTE', false, false, '22222222-2222-2222-2222-222222222201');

-- 6. CIERRE_CAJA
INSERT INTO cierre_caja (id, tenant_id, fecha, hora_apertura, saldo_inicial, total_ingresos, total_egresos, saldo_esperado, usuario_apertura)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa01', '11111111-1111-1111-1111-111111111111', CURRENT_DATE, CURRENT_TIME, 500.00, 708.00, 50.00, 1158.00, '22222222-2222-2222-2222-222222222203');

-- 7. MOVIMIENTO_CAJA
INSERT INTO movimiento_caja (id, tenant_id, cierre_caja_id, tipo, metodo_pago, monto, referencia, concepto, usuario_id)
VALUES (gen_random_uuid(), '11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa01', 'INGRESO', 'EFECTIVO', 118.00, 'VEN-001', 'Venta boleta', '22222222-2222-2222-2222-222222222203');

INSERT INTO movimiento_caja (id, tenant_id, cierre_caja_id, tipo, metodo_pago, monto, referencia, concepto, usuario_id)
VALUES (gen_random_uuid(), '11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa01', 'INGRESO', 'TRANSFERENCIA', 590.00, 'VEN-002', 'Venta factura', '22222222-2222-2222-2222-222222222203');

INSERT INTO movimiento_caja (id, tenant_id, cierre_caja_id, tipo, metodo_pago, monto, referencia, concepto, usuario_id)
VALUES (gen_random_uuid(), '11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaa01', 'SALIDA', 'EFECTIVO', 50.00, 'GAS-001', 'Compra de suministros', '22222222-2222-2222-2222-222222222203');
