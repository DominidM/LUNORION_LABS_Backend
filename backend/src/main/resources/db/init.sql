CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS tenant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ruc VARCHAR(11) UNIQUE NOT NULL,
    razon_social VARCHAR(200) NOT NULL,
    nombre_comercial VARCHAR(200),
    domicilio_fiscal VARCHAR(300),
    email VARCHAR(100),
    telefono VARCHAR(20),
    regimen_tributario VARCHAR(30),
    logo_url VARCHAR(500),
    color_primario VARCHAR(7),
    color_secundario VARCHAR(7),
    plan VARCHAR(30),
    estado VARCHAR(20),
    certificado_p12 BYTEA,
    certificado_password TEXT,
    certificado_validez DATE,
    ruc_validado_sunat BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS permiso (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    modulo VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(8),
    telefono VARCHAR(20),
    rol VARCHAR(12) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    ultimo_acceso TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS usuario_permiso (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    permiso_id UUID NOT NULL REFERENCES permiso(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categoria_producto (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    nombre VARCHAR(100) NOT NULL,
    categoria_padre_id UUID REFERENCES categoria_producto(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS proveedor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    ruc VARCHAR(11) NOT NULL,
    razon_social VARCHAR(200) NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(300),
    condiciones_pago VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cliente (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    tipo_documento VARCHAR(3) NOT NULL,
    numero_documento VARCHAR(15) UNIQUE NOT NULL,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    razon_social VARCHAR(200),
    direccion VARCHAR(300),
    telefono VARCHAR(20),
    email VARCHAR(100),
    consentimiento_datos BOOLEAN DEFAULT FALSE,
    fecha_consentimiento DATE,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS resumen_diario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    fecha_resumen DATE NOT NULL,
    codigo_resumen VARCHAR(20) UNIQUE NOT NULL,
    xml_firmado TEXT,
    xml_cdr TEXT,
    estado VARCHAR(20),
    total_boletas INTEGER,
    total_anulaciones INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tecnico (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    usuario_id UUID UNIQUE NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    especialidades TEXT,
    tarifa_hora DECIMAL(10,2),
    numero_contacto VARCHAR(20),
    fecha_ingreso DATE,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS vehiculo (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    cliente_id UUID NOT NULL REFERENCES cliente(id) ON DELETE CASCADE,
    placa VARCHAR(10) UNIQUE NOT NULL,
    numero_chasis VARCHAR(30),
    numero_motor VARCHAR(30),
    marca VARCHAR(50),
    modelo VARCHAR(50),
    año INTEGER,
    color VARCHAR(30),
    kilometraje INTEGER,
    tipo_combustible VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS producto (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    categoria_id UUID REFERENCES categoria_producto(id) ON DELETE SET NULL,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    codigo_barra VARCHAR(50),
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    unidad_medida VARCHAR(20) NOT NULL,
    precio_compra DECIMAL(10,2),
    precio_venta DECIMAL(10,2) NOT NULL,
    stock_actual DECIMAL(10,2) DEFAULT 0,
    stock_minimo DECIMAL(10,2) DEFAULT 0,
    ubicacion VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS movimiento_stock (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    producto_id UUID NOT NULL REFERENCES producto(id) ON DELETE CASCADE,
    tipo VARCHAR(20) NOT NULL,
    subtipo VARCHAR(30),
    cantidad DECIMAL(10,2) NOT NULL,
    costo_unitario DECIMAL(10,2),
    stock_anterior DECIMAL(10,2),
    stock_posterior DECIMAL(10,2),
    documento_origen VARCHAR(50),
    tipo_documento_origen VARCHAR(30),
    observacion TEXT,
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orden_compra (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    proveedor_id UUID NOT NULL REFERENCES proveedor(id),
    numero_orden VARCHAR(20) UNIQUE NOT NULL,
    fecha_emision DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    observacion TEXT,
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orden_compra_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    orden_compra_id UUID NOT NULL REFERENCES orden_compra(id) ON DELETE CASCADE,
    producto_id UUID NOT NULL REFERENCES producto(id),
    cantidad DECIMAL(10,2) NOT NULL,
    cantidad_recibida DECIMAL(10,2) DEFAULT 0,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS venta (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    cliente_id UUID NOT NULL REFERENCES cliente(id),
    tipo VARCHAR(20) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    igv DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) DEFAULT 0,
    metodo_pago VARCHAR(30),
    estado_pago VARCHAR(20),
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS venta_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    venta_id UUID NOT NULL REFERENCES venta(id) ON DELETE CASCADE,
    producto_id UUID NOT NULL REFERENCES producto(id),
    cantidad DECIMAL(10,2) NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) DEFAULT 0,
    subtotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS comprobante_electronico (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    venta_id UUID NOT NULL REFERENCES venta(id),
    tipo VARCHAR(2) NOT NULL,
    serie VARCHAR(4) NOT NULL,
    numero INTEGER NOT NULL,
    fecha_emision DATE NOT NULL,
    hora_emision TIME NOT NULL,
    xml_firmado TEXT,
    xml_cdr TEXT,
    hash_firma VARCHAR(64),
    estado_sunat VARCHAR(20),
    codigo_error_sunat VARCHAR(10),
    descripcion_error TEXT,
    comprobante_referencia_id UUID REFERENCES comprobante_electronico(id) ON DELETE SET NULL,
    monto_operaciones_gravadas DECIMAL(10,2) DEFAULT 0,
    monto_igv DECIMAL(10,2) DEFAULT 0,
    monto_total DECIMAL(10,2) NOT NULL,
    ruc_cliente VARCHAR(11),
    razon_social_cliente VARCHAR(200),
    intentos_envio INTEGER DEFAULT 0,
    ultimo_envio TIMESTAMP,
    enviado_por UUID REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS resumen_diario_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    resumen_id UUID NOT NULL REFERENCES resumen_diario(id) ON DELETE CASCADE,
    comprobante_id UUID NOT NULL REFERENCES comprobante_electronico(id),
    tipo_operacion VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS orden_trabajo (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    cliente_id UUID NOT NULL REFERENCES cliente(id),
    vehiculo_id UUID NOT NULL REFERENCES vehiculo(id),
    tecnico_id UUID REFERENCES tecnico(id),
    numero_ot VARCHAR(20) UNIQUE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    motivo_ingreso TEXT,
    kilometraje_ingreso INTEGER,
    fecha_ingreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_prometida DATE,
    fecha_cierre TIMESTAMP,
    total_repuestos DECIMAL(10,2) DEFAULT 0,
    total_mano_obra DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) DEFAULT 0,
    ot_origen_id UUID REFERENCES orden_trabajo(id) ON DELETE SET NULL,
    motivo_garantia TEXT,
    usuario_creo UUID NOT NULL REFERENCES usuario(id),
    usuario_cerro UUID REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ot_insumo (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ot_id UUID NOT NULL REFERENCES orden_trabajo(id) ON DELETE CASCADE,
    producto_id UUID NOT NULL REFERENCES producto(id),
    cantidad DECIMAL(10,2) NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ot_mano_obra (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ot_id UUID NOT NULL REFERENCES orden_trabajo(id) ON DELETE CASCADE,
    tecnico_id UUID NOT NULL REFERENCES tecnico(id),
    servicio_descripcion VARCHAR(200) NOT NULL,
    horas DECIMAL(5,2) NOT NULL,
    tarifa_hora DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cita (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    cliente_id UUID NOT NULL REFERENCES cliente(id),
    vehiculo_id UUID REFERENCES vehiculo(id),
    tecnico_id UUID REFERENCES tecnico(id),
    servicio_descripcion TEXT,
    fecha_hora TIMESTAMP NOT NULL,
    duracion_minutos INTEGER,
    estado VARCHAR(20) NOT NULL,
    recordatorio_enviado BOOLEAN DEFAULT FALSE,
    notificar_whatsapp BOOLEAN DEFAULT FALSE,
    usuario_creo UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cierre_caja (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    fecha DATE NOT NULL,
    hora_apertura TIME,
    hora_cierre TIME,
    saldo_inicial DECIMAL(10,2) NOT NULL,
    total_ingresos DECIMAL(10,2) DEFAULT 0,
    total_egresos DECIMAL(10,2) DEFAULT 0,
    saldo_esperado DECIMAL(10,2) NOT NULL,
    saldo_real DECIMAL(10,2),
    descuadre DECIMAL(10,2),
    observacion TEXT,
    usuario_apertura UUID NOT NULL REFERENCES usuario(id),
    usuario_cierre UUID REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS movimiento_caja (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    cierre_caja_id UUID NOT NULL REFERENCES cierre_caja(id) ON DELETE CASCADE,
    tipo VARCHAR(20) NOT NULL,
    metodo_pago VARCHAR(30) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    referencia VARCHAR(100),
    concepto VARCHAR(200) NOT NULL,
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS checkin (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    cliente_id UUID NOT NULL REFERENCES cliente(id),
    vehiculo_id UUID NOT NULL REFERENCES vehiculo(id),
    kilometraje INTEGER,
    nivel_combustible VARCHAR(10),
    observaciones_cliente TEXT,
    firma_cliente TEXT,
    pdf_acta TEXT,
    ot_id UUID REFERENCES orden_trabajo(id),
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS checkin_foto (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    checkin_id UUID NOT NULL REFERENCES checkin(id) ON DELETE CASCADE,
    tipo VARCHAR(20),
    angulo VARCHAR(20),
    url TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS garantia (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    ot_original_id UUID NOT NULL REFERENCES orden_trabajo(id),
    ot_garantia_id UUID NOT NULL REFERENCES orden_trabajo(id),
    motivo TEXT,
    costo_repuestos DECIMAL(10,2) DEFAULT 0,
    costo_mano_obra DECIMAL(10,2) DEFAULT 0,
    costo_total DECIMAL(10,2) DEFAULT 0,
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS asistencia (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    tecnico_id UUID NOT NULL REFERENCES tecnico(id) ON DELETE CASCADE,
    fecha DATE NOT NULL,
    hora_entrada TIME,
    hora_salida TIME,
    horas_trabajadas DECIMAL(5,2),
    tipo VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS boleta_pago (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    tecnico_id UUID NOT NULL REFERENCES tecnico(id) ON DELETE CASCADE,
    periodo VARCHAR(6) NOT NULL,
    sueldo_basico DECIMAL(10,2) NOT NULL,
    horas_extras DECIMAL(10,2) DEFAULT 0,
    comisiones DECIMAL(10,2) DEFAULT 0,
    asignacion_familiar DECIMAL(10,2) DEFAULT 0,
    total_ingresos DECIMAL(10,2) NOT NULL,
    descuento_onp DECIMAL(10,2) DEFAULT 0,
    descuento_afp DECIMAL(10,2) DEFAULT 0,
    descuento_otros DECIMAL(10,2) DEFAULT 0,
    total_descuentos DECIMAL(10,2) NOT NULL,
    neto_pagar DECIMAL(10,2) NOT NULL,
    essalud DECIMAL(10,2) DEFAULT 0,
    pdf_generado TEXT,
    pdf_firmado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS configuracion_comision (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    tecnico_id UUID REFERENCES tecnico(id) ON DELETE CASCADE,
    producto_id UUID REFERENCES producto(id) ON DELETE CASCADE,
    tipo VARCHAR(20) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ple_generado (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    tipo_libro VARCHAR(20) NOT NULL,
    codigo_sunat VARCHAR(10) NOT NULL,
    periodo VARCHAR(6) NOT NULL,
    archivo_txt TEXT,
    hash_archivo VARCHAR(64),
    estado VARCHAR(20),
    usuario_genero UUID NOT NULL REFERENCES usuario(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS auditoria (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id) ON DELETE CASCADE,
    evento VARCHAR(50) NOT NULL,
    entidad VARCHAR(50) NOT NULL,
    entidad_id VARCHAR(50) NOT NULL,
    usuario_id UUID NOT NULL REFERENCES usuario(id),
    ip_origen VARCHAR(45),
    valor_anterior JSONB,
    valor_nuevo JSONB,
    metadatos JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
