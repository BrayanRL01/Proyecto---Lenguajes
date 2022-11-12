set serveroutput on;
--Cambia la sesion al pluggable DB. Este script debe de ser corrido con user que tenga SYSDBA grants
ALTER DATABASE OPEN;
ALTER SESSION SET container = orclpdb;
--ALTER SESSION SET CONTAINER=cdb$root;
set serveroutput on;

--Esto elimina a los usuarios NEGOCIO y WEB_ACCESS y todos sus objetos. Permite recrear todos los objetos facilmente.
DECLARE
    v_count     NUMBER := 0;
    v_statement VARCHAR2(500);
    v_count2     INTEGER := 0;
    v_statement2 VARCHAR2(500);
BEGIN
    SELECT
        COUNT(1)
    INTO v_count
    FROM
        DBA_USERS
    WHERE
        username = upper('NEGOCIO');
    IF v_count != 0 THEN
        EXECUTE IMMEDIATE ('DROP USER NEGOCIO CASCADE' );
    END IF;
    v_count := 0;
    
    SELECT
        COUNT(1)
    INTO v_count2
    FROM
        DBA_USERS
    WHERE
        username = upper('WEB_ACCESS');
    IF v_count2 != 0 THEN
        EXECUTE IMMEDIATE ('DROP USER WEB_ACCESS CASCADE' );
    END IF;
    v_count2 := 0;  
END;
/


CREATE USER NEGOCIO IDENTIFIED BY NEGOCIO;
GRANT CREATE SESSION,RESTRICTED SESSION, UNLIMITED TABLESPACE TO NEGOCIO;
/*
GRANT CREATE VIEW, ALTER SESSION, CREATE SEQUENCE,RESTRICTED SESSION TO NEGOCIO;
GRANT CREATE SYNONYM, CREATE DATABASE LINK, RESOURCE;
*/


CREATE USER WEB_ACCESS IDENTIFIED BY ExternalWeb22; 
GRANT CONNECT, CREATE SESSION, RESTRICTED SESSION TO  WEB_ACCESS;

/*
SELECT * FROM DBA_USERS WHERE USERNAME = LOCAL_USER;
CREATE USER LOCAL_USER IDENTIFIED BY LOCAL_PASSWORD;
GRANT CREATE SESSION,SYSDBA, RESTRICTED SESSION TO LOCAL_USER;
*/

--SELECT USERNAME, DEFAULT_TABLESPACE, TEMPORARY_TABLESPACE, CREATED FROM DBA_USERS WHERE USERNAME ='NEGOCIO';

--Se selecciona el nuevo usuario como schema default
ALTER SESSION SET CURRENT_SCHEMA = NEGOCIO;

--Creacion de tablas
CREATE TABLE  TAB_TIPOS_PERSONA(
ID_TIPO_PERSONA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(30),
PRIMARY KEY(ID_TIPO_PERSONA)
);

CREATE TABLE  TAB_ROLES(
ID_ROLE NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(30),
PRIMARY KEY(ID_ROLE)
);

CREATE TABLE TAB_ESTADOS_USUARIO(
ID_ESTADO_USUARIO NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(30),
PRIMARY KEY(ID_ESTADO_USUARIO)
);

CREATE TABLE TAB_PERSONAS(
ID_PERSONA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
CEDULA NUMBER UNIQUE NOT NULL,
NOMBRE VARCHAR2(30) NOT NULL,
PRIMER_APELLIDO VARCHAR2(30) NOT NULL,
SEGUNDO_APELLIDO VARCHAR2(30),
DIRECCION VARCHAR2(100),
EMAIL VARCHAR2(50),
TELEFONO VARCHAR2(15) NOT NULL,
TIPO_PERSONA_ID NUMBER,
PRIMARY KEY(ID_PERSONA),
CONSTRAINT PK_TAB_PERSONAS_TAB_TIPOS_PERSONA FOREIGN KEY(TIPO_PERSONA_ID) REFERENCES TAB_TIPOS_PERSONA);


CREATE TABLE TAB_USUARIOS(
ID_USUARIO NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE_USUARIO VARCHAR2(30) NOT NULL,
NOMBRE VARCHAR2(30) NOT NULL,
PRIMER_APELLIDO VARCHAR2(30) NOT NULL,
SEGUNDO_APELLIDO VARCHAR2(30) NOT NULL,
EMAIL VARCHAR2(50),
CONTRASENA VARCHAR2(20),
TELEFONO VARCHAR2(15) NOT NULL,
ROLE_ID NUMBER,
ESTADO_ID NUMBER,
PRIMARY KEY(ID_USUARIO),
CONSTRAINT PK_TAB_USUARIOS_TAB_ROLES FOREIGN KEY(ROLE_ID) REFERENCES TAB_ROLES,
CONSTRAINT PK_TAB_USUARIOS_TAB_ESTADOS FOREIGN KEY(ESTADO_ID) REFERENCES TAB_ESTADOS_USUARIO);


CREATE TABLE TAB_CATEGORIAS(
ID_CATEGORIA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(50),
CATEGORIA_MADRE_ID NUMBER,
PRIMARY KEY(ID_CATEGORIA),
CONSTRAINT PK_TAB_CATEGORIAS_TAB_CATEGORIAS FOREIGN KEY(CATEGORIA_MADRE_ID) REFERENCES TAB_CATEGORIAS);


CREATE TABLE TAB_MARCAS(
MARCA_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(50),
PRIMARY KEY(MARCA_ID));

CREATE TABLE TAB_UNIDADES_CANTIDAD(
ID_UNIDAD_CANTIDAD NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(30),
PRIMARY KEY(ID_UNIDAD_CANTIDAD)
);

CREATE TABLE TAB_PRODUCTOS(
ID_PRODUCTO NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
CODIGO VARCHAR2(30),
CATEGORIA_ID NUMBER,
MARCA_ID NUMBER,
NOMBRE VARCHAR2(30),
DETALLE VARCHAR2(50),
PRECIO NUMBER(8, 2) NOT NULL,
TAMANO VARCHAR2(20),
UNIDAD_CANTIDAD_ID NUMBER,
CANTIDAD NUMBER(5),
PRIMARY KEY(ID_PRODUCTO),
CONSTRAINT PK_TAB_PRODUCTOS_TAB_CATEGORIAS FOREIGN KEY(CATEGORIA_ID) REFERENCES TAB_CATEGORIAS,
CONSTRAINT PK_TAB_PRODUCTOS_TAB_MARCAS FOREIGN KEY(MARCA_ID) REFERENCES TAB_MARCAS,
CONSTRAINT PK_TAB_PRODUCTOS_TAB_UNIDADES_CANTIDAD FOREIGN KEY(UNIDAD_CANTIDAD_ID) REFERENCES TAB_UNIDADES_CANTIDAD);

CREATE TABLE TAB_TIPOS_VENTA(
ID_TIPO_VENTA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(30),
PRIMARY KEY(ID_TIPO_VENTA)
);

CREATE TABLE TAB_MEDIOS_PAGO(
ID_MEDIO_PAGO NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
NOMBRE VARCHAR2(30),
PRIMARY KEY(ID_MEDIO_PAGO)
);

CREATE TABLE TAB_FACTURAS (
ID_FACTURA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
VENDEDOR_ID NUMBER,
CLIENTE_ID NUMBER,
TIPO_VENTA_ID NUMBER,
TOTAL_ENTREGA NUMBER (8,2),
TOTAL NUMBER(8, 2),
MEDIO_PAGO_ID NUMBER,
FECHA_HORA_VENTA TIMESTAMP DEFAULT SYSDATE,
CONSTRAINT PK_TAB_FACTURAS PRIMARY KEY(ID_FACTURA),
CONSTRAINT PK_TAB_FACTURAS_TAB_USUARIOS FOREIGN KEY(VENDEDOR_ID) REFERENCES TAB_USUARIOS,
CONSTRAINT PK_TAB_FACTURAS_TAB_PERSONAS FOREIGN KEY(CLIENTE_ID) REFERENCES TAB_PERSONAS,
CONSTRAINT PK_TAB_FACTURAS_TAB_TIPOS_VENTA FOREIGN KEY(TIPO_VENTA_ID) REFERENCES TAB_TIPOS_VENTA,
CONSTRAINT PK_TAB_FACTURAS_TAB_MEDIOS_PAGO FOREIGN KEY(MEDIO_PAGO_ID) REFERENCES TAB_MEDIOS_PAGO);

CREATE TABLE TAB_DETALLES_FACTURA(
ID_FACTURA NUMBER NOT NULL,
ID_DETALLE NUMBER NOT NULL, 
PRODUCTO_ID NUMBER, 
CANTIDAD NUMBER(3), 
PRECIO NUMBER(8, 2),
TOTAL_SIN_IVA NUMBER(8, 2),
IVA NUMBER(2,2),
SUBTOTAL NUMBER(8, 2),
PRIMARY KEY(ID_FACTURA,ID_DETALLE), 
CONSTRAINT PK_TAB_DETALLES_FACTURA_TAB_FACTURAS FOREIGN KEY(ID_FACTURA) REFERENCES TAB_FACTURAS,
CONSTRAINT PK_TAB_DETALLES_FACTURA_TAB_PRODUCTOS FOREIGN KEY(PRODUCTO_ID) REFERENCES TAB_PRODUCTOS);

INSERT INTO TAB_TIPOS_PERSONA (NOMBRE) VALUES ('Colaborador Interno');
INSERT INTO TAB_TIPOS_PERSONA (NOMBRE) VALUES ('Cliente');
INSERT INTO TAB_TIPOS_PERSONA (NOMBRE) VALUES ('Proveedor');

INSERT INTO TAB_ESTADOS_USUARIO (NOMBRE) VALUES ('Activo');
INSERT INTO TAB_ESTADOS_USUARIO (NOMBRE) VALUES ('Bloqueado');

INSERT INTO TAB_ROLES (NOMBRE) VALUES ('Admin');

INSERT INTO TAB_USUARIOS (NOMBRE_USUARIO, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, EMAIL, CONTRASENA, TELEFONO, ROLE_ID, ESTADO_ID) VALUES ('KeylorCR','Keylor','Chacon', 'Salas', 'keylorchs@Gmail.com','Test123','61328064',1,1);

INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (117380925,'Shreya','Mcdonald','Strickland','Whitefield View','Shreya@gmail.com','86543422',1);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (168549854,'Xaver','Berry','Mejia','Whitefield View','Xaver@gmail.com','86423531',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (258216514,'Eduard','Fernandez','Palmer','Port Riddell','Eduard@gmail.com','832341',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (296853265,'Dipti','Thompson','Alexander','Fell Heath','Dipti@gmail.com','83153431',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (375481256,'Nuria','Davidson','Mann','Thirlwall Drive','Nuria@gmail.com','83234133',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (596587455,'Ketut','Ramirez','Wise','Southburn Terrace','Ketut@gmail.com','64234131',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (436589754,'Wolfgang','Stevenson','Thornton','Oakley Paddock','Wolfgang@gmail.com','83134133',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (556895687,'Subrahmanya','Burns','Khan','Williamson Bank','Subrahmanya@gmail.com','64354212',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (656895845,'Ptah','Robinson','Kaye','Holden Elms','Ptah@gmail.com','86434231',2);
INSERT INTO TAB_PERSONAS (CEDULA,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,EMAIL,TELEFONO,TIPO_PERSONA_ID) VALUES (736524558,'Barbara','Kirby','Rossi','Crispin Coppice','Barbara@gmail.com','75331243',2);
 
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Ropa','');  --1
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Perfumes','');--2
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Licores',''); --3
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Camisas',1); --4
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Pantalonetas',1); --5
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Zapatos',1); --6
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Camisetas',1); --7
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Vino',3); --8
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Pantalonetas',1); --9
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Zapatos',1); --10
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Camisas',1); --11
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Perfumes Hombre',2); --12
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Perfumes Mujer',2); --13
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Whisky',3); --14
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Tipo licor 2',3); --15
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Tipo licor 3',3); --16
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Vestidos',1); --17
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Pantalones',1); --18
INSERT INTO TAB_CATEGORIAS  (NOMBRE,CATEGORIA_MADRE_ID) VALUES ('Chaquetas',1); --19

INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Nike'); --1
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Adidas');--2
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Puma');--3
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Bocceli');--4
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('CHANEL');--5
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('HERMES Paris');--6
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('ck');--7
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Hugo BOSS');--8
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Jack Daniels');--9
INSERT INTO TAB_MARCAS (NOMBRE) VALUES ('Johhny Walker');--10

INSERT INTO TAB_TIPOS_VENTA(NOMBRE) VALUES ('Contado');--1
INSERT INTO TAB_TIPOS_VENTA(NOMBRE) VALUES ('Credito');--2

INSERT INTO TAB_MEDIOS_PAGO(NOMBRE) VALUES ('Tarjeta');--1
INSERT INTO TAB_MEDIOS_PAGO(NOMBRE) VALUES ('Efectivo');--2
INSERT INTO TAB_MEDIOS_PAGO(NOMBRE) VALUES ('Cheque');--3
INSERT INTO TAB_MEDIOS_PAGO(NOMBRE) VALUES ('Transferencia');--4
INSERT INTO TAB_MEDIOS_PAGO(NOMBRE) VALUES ('Sinpe');--5

INSERT INTO TAB_UNIDADES_CANTIDAD(NOMBRE) VALUES ('Unidad');--1
INSERT INTO TAB_UNIDADES_CANTIDAD(NOMBRE) VALUES ('Kg');--2
INSERT INTO TAB_UNIDADES_CANTIDAD(NOMBRE) VALUES ('l');--3

INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('B9330313',4,4,'Camisa manga larga','Negra',25500,'S',1,24); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('B9330313',4,4,'Camisa manga larga','Negra',25500,'M',1,10); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('B9330313',4,4,'Camisa manga larga','Negra',25500,'L',1,15);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35027996',18,4,'Jeans Denim Stretch','Bota Slim, Color Negro',24900,'28',1,5); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35027996',18,4,'Jeans Denim Stretch','Bota Slim, Color Negro',24900,'30',1,6); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35027996',18,4,'Jeans Denim Stretch','Bota Slim, Color Negro',24900,'32',1,4); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35027996',18,4,'Jeans Denim Stretch','Bota Slim, Color Negro',24900,'34',1,7); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35027996',18,4,'Jeans Denim Stretch','Bota Slim, Color Negro',24900,'36',1,8); 
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('79330563',7,4,'Camiseta tipo polo','Tela Piqu�',23900,'S',1,14);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('79330563',7,4,'Camiseta tipo polo','Tela Piqu�',23900,'M',1,10);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('79330563',7,4,'Camiseta tipo polo','Tela Piqu�',23900,'L',1,9);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('79330563',7,4,'Camiseta tipo polo','Tela Piqu�',23900,'XL',1,7);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35025956',19,4,'Chaqueta impermeable','Gorro Removible, Negra',41900,'S',1,6);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35025956',19,4,'Chaqueta impermeable','Gorro Removible, Negra',41900,'M',1,6);
INSERT INTO TAB_PRODUCTOS(CODIGO, CATEGORIA_ID , MARCA_ID, NOMBRE, DETALLE, PRECIO, TAMANO, UNIDAD_CANTIDAD_ID, CANTIDAD) VALUES ('35025956',19,4,'Chaqueta impermeable','Gorro Removible, Negra',41900,'L',1,6);


--Se crea el custom type OBJ_FACTURA_DETALLE
CREATE OR REPLACE TYPE OBJ_DETALLE_FACTURA AS OBJECT 
(
ProductoID NUMBER,
Cantidad NUMBER(3),
Precio  NUMBER(8, 2),
IVA NUMBER(2,2)
);
/
--Se crea el custom type OBJ_DETALLES_FACTURA el cual contiene objetos tipo OBJ_FACTURA_DETALLE
CREATE OR REPLACE TYPE OBJ_DETALLES_FACTURA AS TABLE OF OBJ_DETALLE_FACTURA;
/

--View para hacer Detalles legible
CREATE OR REPLACE VIEW VW_DETALLES_FACTURA AS 
SELECT DET.ID_FACTURA, DET.ID_DETALLE, PROD.PRECIO, DET.CANTIDAD, UNI.NOMBRE 
FROM TAB_DETALLES_FACTURA DET
    LEFT JOIN TAB_PRODUCTOS PROD 
    ON DET.PRODUCTO_ID = PROD.ID_PRODUCTO
    LEFT JOIN TAB_UNIDADES_CANTIDAD UNI
    ON PROD.UNIDAD_CANTIDAD_ID = UNI.ID_UNIDAD_CANTIDAD;
/


-- Obtener la factura maxima existente que haya sido creada por el usuario actual.
CREATE OR REPLACE PROCEDURE SP_GET_MAX_FACTURA (IN_ID_USUARIO IN NUMBER, OUT_MAX_FACTURA OUT NUMBER) AS 
BEGIN
SELECT MAX(ID_FACTURA) INTO OUT_MAX_FACTURA FROM TAB_FACTURAS
WHERE VENDEDOR_ID = IN_ID_USUARIO;
END;
/

-- Obtener el detalle maximo de una factura en especifico
CREATE OR REPLACE PROCEDURE SP_GET_MAX_DETALLE_EN_FACTURA (IN_ID_FACTURA IN NUMBER , OUT_MAX_DETALLE OUT NUMBER) AS 
BEGIN
SELECT MAX(ID_DETALLE) INTO OUT_MAX_DETALLE 
FROM TAB_DETALLES_FACTURA DET
WHERE DET.ID_FACTURA = IN_ID_FACTURA;
IF OUT_MAX_DETALLE IS NULL THEN
OUT_MAX_DETALLE :=0;
END IF;
END;
/

--Este procedimiento remueve X cantidad de un producto del inventario
CREATE OR REPLACE PROCEDURE SP_REMOVER_PRODUCTO (IN_ID_PRODUCTO IN NUMBER, IN_CANTIDAD IN NUMBER, RESULTADO OUT NUMBER) AS 
BEGIN
UPDATE TAB_PRODUCTOS SET CANTIDAD = CANTIDAD-IN_CANTIDAD WHERE ID_PRODUCTO = IN_ID_PRODUCTO;
DBMS_OUTPUT.put_line(IN_CANTIDAD || ' unidades del producto '  || IN_ID_PRODUCTO || ' removidas'  );
RESULTADO := 0;
END;
/

--Este procedimiento se encarga de revisar si existe o no suficiente inventario como para remover X cantidad.
CREATE OR REPLACE PROCEDURE SP_INVENTARIO_REVISION (IN_ID_PRODUCTO IN NUMBER, IN_CANTIDAD IN NUMBER, RESULTADO OUT NUMBER) AS
V_CANTIDAD_DISPONIBLE NUMBER;
BEGIN
SELECT CANTIDAD INTO V_CANTIDAD_DISPONIBLE FROM TAB_PRODUCTOS PRODS WHERE ID_PRODUCTO = IN_ID_PRODUCTO;
    IF V_CANTIDAD_DISPONIBLE >= IN_CANTIDAD THEN
        RESULTADO := 0;
       
    ELSE 
        RESULTADO := 1;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE SP_REVISION_Y_SUBSTRACCION_PRODUCTOS(IN_OBJ_DETALLES_FACTURA IN OBJ_DETALLES_FACTURA, OUT_PRODUCTO_PROBLEMA OUT VARCHAR, RESULTADO OUT NUMBER) AS 
V_RESPUESTA_INV_REVISION NUMBER ;
V_RESULTADO_REMOVER_PRODUCTO NUMBER;
BEGIN
  FOR i IN 1..IN_OBJ_DETALLES_FACTURA.COUNT LOOP 
    SP_INVENTARIO_REVISION(IN_OBJ_DETALLES_FACTURA(i).ProductoID, IN_OBJ_DETALLES_FACTURA(i).Cantidad, V_RESPUESTA_INV_REVISION);
    OUT_PRODUCTO_PROBLEMA  := IN_OBJ_DETALLES_FACTURA(i).ProductoID ;
    EXIT WHEN V_RESPUESTA_INV_REVISION = 1;
  END LOOP;
  IF V_RESPUESTA_INV_REVISION=1 THEN
      SELECT NOMBRE INTO OUT_PRODUCTO_PROBLEMA FROM TAB_PRODUCTOS WHERE ID_PRODUCTO = OUT_PRODUCTO_PROBLEMA; 
      RESULTADO := 1;
  ELSE
      FOR i IN 1..IN_OBJ_DETALLES_FACTURA.COUNT LOOP 
      SP_REMOVER_PRODUCTO (IN_OBJ_DETALLES_FACTURA(i).ProductoID, IN_OBJ_DETALLES_FACTURA(i).Cantidad, V_RESULTADO_REMOVER_PRODUCTO);
      END LOOP;
      RESULTADO := 0;
  END IF;
END;
/

--Este procedimiento tiene como objetivo permitir ingresar un detalle de factura
CREATE OR REPLACE PROCEDURE SP_INSERTAR_DETALLE_FACTURA (IN_ID_USUARIO IN NUMBER, IN_PRODUCTO_ID IN NUMBER, IN_CANTIDAD IN NUMBER, IN_IVA IN NUMBER) AS 
V_MAX_FACTURA NUMBER;
OUT_MAX_DETALLE NUMBER;
V_TOTAL_SIN_IVA NUMBER(8, 2);
V_PRECIO_PRODUCTO NUMBER(8,2);
V_SUBTOTAL NUMBER(8, 2);
BEGIN
SP_GET_MAX_FACTURA(IN_ID_USUARIO, V_MAX_FACTURA);
SP_GET_MAX_DETALLE_EN_FACTURA (V_MAX_FACTURA, OUT_MAX_DETALLE);
OUT_MAX_DETALLE := OUT_MAX_DETALLE+1;
SELECT PRECIO INTO V_PRECIO_PRODUCTO FROM TAB_PRODUCTOS P WHERE  P.ID_PRODUCTO = IN_PRODUCTO_ID;

V_TOTAL_SIN_IVA := IN_CANTIDAD * V_PRECIO_PRODUCTO;
V_SUBTOTAL  := V_TOTAL_SIN_IVA + (V_TOTAL_SIN_IVA * IN_IVA);

INSERT INTO TAB_DETALLES_FACTURA (ID_FACTURA, ID_DETALLE, PRODUCTO_ID, CANTIDAD, PRECIO, TOTAL_SIN_IVA, IVA, SUBTOTAL) 
VALUES (V_MAX_FACTURA, OUT_MAX_DETALLE, IN_PRODUCTO_ID, IN_CANTIDAD, V_PRECIO_PRODUCTO, V_TOTAL_SIN_IVA, IN_IVA, V_SUBTOTAL);

END; 
/

CREATE OR REPLACE PROCEDURE SP_CALCULAR_TOTAL_FACTURA(IN_FACTURA_ID NUMBER) AS 
OUT_TOTAL_FACTURA DECIMAL;
BEGIN
SELECT SUM(SUBTOTAL) INTO OUT_TOTAL_FACTURA FROM TAB_DETALLES_FACTURA WHERE ID_FACTURA = IN_FACTURA_ID;
UPDATE TAB_FACTURAS SET TOTAL = OUT_TOTAL_FACTURA  WHERE ID_FACTURA = IN_FACTURA_ID;
END;
/

--Este procedimiento crea la factura una vez la verificacion 1 es exitosa
CREATE OR REPLACE PROCEDURE SP_INSERTAR_FACTURA 
(IN_ID_VENDEDOR IN NUMBER, IN_ID_CLIENTE IN NUMBER, IN_TIPO_VENTA_ID IN NUMBER, IN_TOTAL_ENTREGA IN NUMBER, IN_MEDIO_PAGO_ID IN NUMBER, IN_OBJ_DETALLES_FACTURA IN OBJ_DETALLES_FACTURA, RESULTADO OUT NUMBER, MENSAJE OUT VARCHAR2) AS
V_FECHA TIMESTAMP := SYSDATE;
V_OUT_PRODUCTO_PROBLEMA TAB_PRODUCTOS.NOMBRE%TYPE;
V_RESULTADO__REVISION_Y_SUBTRACCION NUMBER;
V_MAX_FACTURA NUMBER;
BEGIN
    SP_REVISION_Y_SUBSTRACCION_PRODUCTOS(IN_OBJ_DETALLES_FACTURA, V_OUT_PRODUCTO_PROBLEMA,  V_RESULTADO__REVISION_Y_SUBTRACCION);
    IF V_RESULTADO__REVISION_Y_SUBTRACCION = 0
        THEN
            INSERT INTO TAB_FACTURAS (VENDEDOR_ID, CLIENTE_ID, TIPO_VENTA_ID, TOTAL_ENTREGA, TOTAL, MEDIO_PAGO_ID, FECHA_HORA_VENTA) 
            VALUES (IN_ID_VENDEDOR, IN_ID_CLIENTE, IN_TIPO_VENTA_ID, IN_TOTAL_ENTREGA, 0, IN_MEDIO_PAGO_ID, V_FECHA); 
            FOR i IN 1..IN_OBJ_DETALLES_FACTURA.COUNT LOOP
                SP_INSERTAR_DETALLE_FACTURA(IN_ID_VENDEDOR, IN_OBJ_DETALLES_FACTURA(i).ProductoID, IN_OBJ_DETALLES_FACTURA(i).Cantidad, IN_OBJ_DETALLES_FACTURA(i).IVA);
                RESULTADO := 0;
            END LOOP; 
            SP_GET_MAX_FACTURA(IN_ID_VENDEDOR, V_MAX_FACTURA);
            SP_CALCULAR_TOTAL_FACTURA(V_MAX_FACTURA);
        ELSE
        RESULTADO := 1;
        MENSAJE := 'No hay suficientes unidades del Producto: ' || V_OUT_PRODUCTO_PROBLEMA;
    END IF;
END; 
/

----------------------------------------------------------------------------------------------

-- View de PERSONAS
CREATE OR REPLACE VIEW VW_PERSONAS AS
SELECT
ID_PERSONA, PER.NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, DIRECCION, EMAIL, TELEFONO, T.NOMBRE AS TIPO_PERSONA, PER.TIPO_PERSONA_ID 
FROM TAB_PERSONAS PER
LEFT JOIN TAB_TIPOS_PERSONA T ON PER.TIPO_PERSONA_ID = T.ID_TIPO_PERSONA;
/


--Insertar una persona
CREATE OR REPLACE PROCEDURE SP_INSERTAR_PERSONA (IN_CEDULA IN NUMBER, IN_NOMBRE IN VARCHAR2, IN_PRIMER_APELLIDO IN VARCHAR2, IN_SEGUNDO_APELLIDO IN VARCHAR2, IN_DIRECCION IN VARCHAR2, IN_EMAIL VARCHAR2, IN_TELEFONO IN VARCHAR2, IN_ID_TIPO_PERSONA IN NUMBER) AS
BEGIN
INSERT INTO TAB_PERSONAS (CEDULA, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, DIRECCION, EMAIL, TELEFONO, TIPO_PERSONA_ID) 
VALUES (IN_CEDULA, IN_NOMBRE, IN_PRIMER_APELLIDO, IN_SEGUNDO_APELLIDO, IN_DIRECCION, IN_EMAIL, IN_TELEFONO, IN_ID_TIPO_PERSONA);
END;
/

--Devuelve todas las personas
CREATE OR REPLACE PROCEDURE SP_MOSTRAR_PERSONAS (PERSONAS OUT SYS_REFCURSOR) AS 
BEGIN
OPEN PERSONAS FOR 
SELECT PER.NOMBRE, PER.PRIMER_APELLIDO, PER.SEGUNDO_APELLIDO, PER.DIRECCION, PER.EMAIL, PER.TELEFONO, PER.TIPO_PERSONA
FROM NEGOCIO.VW_PERSONAS PER 
JOIN TAB_PERSONAS ON PER.ID_PERSONA =  PER.ID_PERSONA;
END;
/

--Devolver personas que pertenezcan a un role en especifico
CREATE OR REPLACE PROCEDURE SP_PERSONAS_POR_TIPO (V_ID_TIPO_PERSONA IN NUMBER, PERSONAS OUT SYS_REFCURSOR)
AS
BEGIN
OPEN PERSONAS FOR 
SELECT PER.NOMBRE, PER.PRIMER_APELLIDO, PER.SEGUNDO_APELLIDO, PER.DIRECCION, PER.EMAIL, PER.TELEFONO, PER.TIPO_PERSONA
FROM VW_PERSONAS PER
WHERE PER.TIPO_PERSONA_ID = V_ID_TIPO_PERSONA;
END;
/

--Devolver los diferentes tipos de persona
CREATE OR REPLACE PROCEDURE SP_GET_TIPOS_PERSONA (TIPOS OUT SYS_REFCURSOR)
AS
BEGIN
OPEN TIPOS FOR 
SELECT ID_TIPO_PERSONA, NOMBRE
FROM TAB_TIPOS_PERSONA;
END;
/

--Devolver categoria en base a un id
CREATE OR REPLACE PROCEDURE SP_CATEGORIA_FROM_ID (V_ID_CAT IN NUMBER, CATEGORIA OUT VARCHAR2) AS
BEGIN
SELECT NOMBRE INTO CATEGORIA FROM TAB_CATEGORIAS WHERE ID_CATEGORIA = V_ID_CAT;
END;
/

--Devolver todas las categorias principales
CREATE OR REPLACE PROCEDURE SP_CATEGORIAS (CATS OUT SYS_REFCURSOR)
AS
BEGIN
OPEN CATS FOR SELECT NOMBRE FROM TAB_CATEGORIAS WHERE CATEGORIA_MADRE_ID IS NOT NULL;
END;
/

--Devolver todas las subcategorias que pertenezcan a una categoria en especifico
CREATE OR REPLACE PROCEDURE SP_SUBCATS_FROM_CAT (V_ID_CAT IN NUMBER, SUB_CATS OUT SYS_REFCURSOR)
AS
BEGIN
OPEN SUB_CATS FOR SELECT NOMBRE FROM TAB_CATEGORIAS WHERE CATEGORIA_MADRE_ID= V_ID_CAT;
END;
/

--Devolver los diferentes estados de usuario
CREATE OR REPLACE PROCEDURE SP_GET_ESTADOS_USUARIO (ESTADOS OUT SYS_REFCURSOR)
AS
BEGIN
OPEN ESTADOS FOR 
SELECT ID_ESTADO_USUARIO, NOMBRE
FROM TAB_ESTADOS_USUARIO;
END;
/

--GRANT EXECUTE ON NEGOCIO TO WEB_ACCESS;
begin
for x in ( SELECT DISTINCT OBJECT_NAME 
  FROM DBA_OBJECTS
 WHERE OBJECT_TYPE = 'TABLE'
   AND OWNER = 'NEGOCIO'
 )
loop

execute immediate 'grant select on ' || x.OBJECT_NAME ||
' to WEB_ACCESS';
end loop;
end;
/

begin
for x in ( SELECT DISTINCT OBJECT_NAME 
  FROM DBA_OBJECTS
 WHERE OBJECT_TYPE = 'PROCEDURE'
   AND OWNER = 'NEGOCIO'
 )
loop

execute immediate 'grant EXECUTE on ' || x.OBJECT_NAME ||
' to WEB_ACCESS';
end loop;
end;
/


--Se instancian objetos de prueba
DECLARE
Detalle_Factura1 OBJ_DETALLE_FACTURA;
Detalle_Factura2 OBJ_DETALLE_FACTURA;
Detalle_Factura3 OBJ_DETALLE_FACTURA;
Detalle_Factura4 OBJ_DETALLE_FACTURA;
Detalle_Factura5 OBJ_DETALLE_FACTURA;
Detalles_Factura OBJ_DETALLES_FACTURA;
Detalles_Factura2 OBJ_DETALLES_FACTURA;
Resultado_Insert_Factura NUMBER;
Resultado_Mensaje_Factura VARCHAR2(100);
MENSAJE2 VARCHAR(50);
BEGIN
Detalle_Factura1 := new OBJ_DETALLE_FACTURA(1, 5, 5000,0.13);
Detalle_Factura2 := new OBJ_DETALLE_FACTURA(2, 6, 6000,0.12);
Detalle_Factura3 := new OBJ_DETALLE_FACTURA(3, 8, 9000,0.14);
Detalle_Factura4 := new OBJ_DETALLE_FACTURA(7, 7, 24900,0.12);
Detalle_Factura5 := new OBJ_DETALLE_FACTURA(13, 5, 41900,0.14);
Detalles_Factura := new OBJ_DETALLES_FACTURA(Detalle_Factura1, Detalle_Factura2, Detalle_Factura3);
Detalles_Factura2 := new OBJ_DETALLES_FACTURA(Detalle_Factura4, Detalle_Factura5);
--Se ingresa: IN_ID_VENDEDOR, IN_ID_CLIENTE, IN_TIPO_VENTA_ID, IN_TOTAL_ENTREGA, IN_MEDIO_PAGO_ID, , y salen RESULTADO y MENSAJE AS
SP_INSERTAR_FACTURA (1, 2, 1, 6500, 2, Detalles_Factura, Resultado_Insert_Factura, Resultado_Mensaje_Factura);
IF Resultado_Insert_Factura = 1
THEN
DBMS_OUTPUT.put_line(Resultado_Mensaje_Factura);
ELSE 
DBMS_OUTPUT.put_line('Factura Ingresada');
END IF;

SP_INSERTAR_FACTURA (1, 2, 1, 6500, 2, Detalles_Factura2, Resultado_Insert_Factura, Resultado_Mensaje_Factura);
IF Resultado_Insert_Factura = 1
THEN
DBMS_OUTPUT.put_line(Resultado_Mensaje_Factura);
ELSE 
DBMS_OUTPUT.put_line('Factura Ingresada');
END IF;

END;
/
