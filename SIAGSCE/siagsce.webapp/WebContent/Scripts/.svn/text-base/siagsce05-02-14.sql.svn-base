--
-- PostgreSQL database dump
--

-- Started on 2014-02-05 21:23:54

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1993 (class 0 OID 0)
-- Dependencies: 152
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 7, true);


--
-- TOC entry 1985 (class 0 OID 54765)
-- Dependencies: 162
-- Data for Name: proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO proyecto (proyecto_codigo, proyecto_descripcion, proyecto_nombre, proyecto_status) VALUES ('RORA', 'Programa para la capacitacion de escuelas rurales', 'PRORAIMA', 'Activo');
INSERT INTO proyecto (proyecto_codigo, proyecto_descripcion, proyecto_nombre, proyecto_status) VALUES ('IGN', 'Programa de Enseñanza de Calculos y Fisica', 'IGNMAF', 'Activo');
INSERT INTO proyecto (proyecto_codigo, proyecto_descripcion, proyecto_nombre, proyecto_status) VALUES ('PCTAM02', 'Proyecto de capacitacion tecnologica para el Adulto Mayor 2.0', 'PCTAM Proyeto 2.0', 'Activo');
INSERT INTO proyecto (proyecto_codigo, proyecto_descripcion, proyecto_nombre, proyecto_status) VALUES ('PIT', 'Programa de Preescolar de la UCLA', 'PITNEI', 'Activo');


--
-- TOC entry 1964 (class 0 OID 54688)
-- Dependencies: 140 1985
-- Data for Name: actividad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1006, 5, 'Registrar abuelos', 'Inscripcion Abuelitos 2.0', 'PCTAM02');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1007, 6, 'Introducción a las Redes sociales', 'Clases 1', 'PCTAM02');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1008, 7, 'Facebook', 'Clases 2', 'PCTAM02');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1009, 4, 'Twitter', 'Clases 3', 'PCTAM02');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1010, 3, 'Registrar niños', 'Inscripcion de niños', 'PIT');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1011, 3, 'Clase introductoria', 'Clases 1', 'PIT');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1012, 1, 'Uso de Herramienta Office', 'Clases 2', 'PIT');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1013, 3, 'Registrar niños', 'Inscripcion de niños', 'RORA');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1014, 3, 'Registrar Participantes', 'Inscripcion de estudiante', 'IGN');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1015, 3, 'Introduccion a Windows', 'Clases 1', 'RORA');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1016, 2, 'Usar los Navegadores de Internet', 'Clases 2', 'RORA');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1017, 3, 'Introduccion al Mundo de las Matemáticas', 'Clases 1', 'IGN');
INSERT INTO actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, actividad_nombre, proyecto_codigo) VALUES (1018, 3, 'Logica de la Fisica', 'Clases 2', 'IGN');


--
-- TOC entry 1970 (class 0 OID 54709)
-- Dependencies: 146
-- Data for Name: direccion_programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO direccion_programa (direccion_codigo, direccion_nombre, direccion_unidades_credito) VALUES (1001, 'ING. INFORMATICA', 160);
INSERT INTO direccion_programa (direccion_codigo, direccion_nombre, direccion_unidades_credito) VALUES (1002, 'LIC. TELEMATICA', 175);
INSERT INTO direccion_programa (direccion_codigo, direccion_nombre, direccion_unidades_credito) VALUES (1003, 'LIC. MATEMATICA', 170);
INSERT INTO direccion_programa (direccion_codigo, direccion_nombre, direccion_unidades_credito) VALUES (1004, 'ANALISIS DE SISTEMAS', 80);
INSERT INTO direccion_programa (direccion_codigo, direccion_nombre, direccion_unidades_credito) VALUES (1005, 'ING. PRODUCCION', 170);
INSERT INTO direccion_programa (direccion_codigo, direccion_nombre, direccion_unidades_credito) VALUES (1006, 'LIC. FISICA', 160);


--
-- TOC entry 1972 (class 0 OID 54715)
-- Dependencies: 148 1970
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19294894', 'Lobig', 'Carrera 20 entre calle 30 y 31', 'lobigm@hotmail.com', '2013-02-20', 'Sergio', 'Apto', '04145573270', 145, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('20111111', 'Camacaro', 'Carrera 21 con 50', 'lobigm@gmail.com', '2013-02-20', 'Efigenia', 'Acreditado', '0414444444', 20, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('11234555', 'Smith', ' 30 y 31', 'smithi@hotmail.com', '2013-02-20', 'Simur', 'Apto', '04145555555', 113, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('10214142', 'Matos', 'Avenida oceano pacifico', 'matomota@hotmail.com', '2013-02-20', 'Petra', 'Apto', '02512315151', 100, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('12456789', 'Rodriguez', 'Carrera con calle y ', 'rodriguezpierozucaro@hotmail.com', '2013-02-20', 'Piero', 'Apto', '04165454545', 101, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('17726019', 'Mejias', '60 con 17', 'greidamejias@gmail.com', '2013-02-20', 'Greida', 'Acreditado', '04268588418', 110, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19887067', 'Mendez', 'Patarata', 'march.mdma@gmail.com', '2013-04-15', 'Maryelis', 'Acreditado', '04126705780', 113, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18052474', 'Alvarado', 'Patarata', 'robert.alvarado6@gmail.com', '2013-04-15', 'Robert', 'Acreditado', '04120586535', 105, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18997067', 'Rodriguez', 'Patarata', 'pedror90@gmail.com', '2013-04-15', 'Pedro', 'Acreditado', '0424555555', 111, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('17727505', 'Lopez', 'Barrio Union', 'delbaclg@gmail.com', '2013-04-15', 'Delba', 'Acreditado', '04120544009', 112, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19171220', 'Rodriguez', 'Acarigua', 'mgrodriguez@gmail.com', '2013-04-15', 'Maria Gracia', 'Acreditado', '04246266255', 110, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18684069', 'Andrade', 'Obelisco', 'elvisandradeb@gmail.com', '2013-04-15', 'Elvis', 'Acreditado', '0424599955', 109, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('20178011', 'Oropeza', 'Caracas', 'norbelisoropeza@gmail.com', '2012-10-15', 'Norbelis', 'Acreditado', '0424599955', 111, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19104287', 'Hernandez', 'Cubiro', 'emilio25informatic@gmail.com', '2012-10-15', 'Emilio', 'Acreditado', '0424599098', 112, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('17666666', 'Aldana', 'Piritu', 'kagerhe@gmail.com', '2012-10-15', 'Karina', 'Acreditado', '04120909098', 112, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19110001', 'Gallardo', 'Cubiro', 'gneilao@gmail.com', '2012-10-15', 'Neila', 'Acreditado', '0414599218', 102, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19112221', 'Paez', 'Carora', 'mariaapaezt@gmail.com', '2012-10-15', 'Maria A', 'Acreditado', '0414599218', 102, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19113331', 'Colmenarez', 'Lara', 'cjoseg@gmail.com', '2012-10-15', 'Jose G', 'Apto', '0414599218', 80, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19111111', 'Sequera', 'Lara', 'sandreina@gmail.com', '2012-06-10', 'Andreina', 'Apto', '0414599218', 55, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('17019344', 'Melendez', 'Concordia', 'rmelendez@gmail.com', '2012-06-10', 'Raquel', 'Apto', '04266567313', 80, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18762896', 'Perez', 'La Represa', 'dperez@gmail.com', '2012-06-10', 'Dassajeth', 'Acreditado', '04166087621', 100, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19873109', 'Alvarez', 'Jacinto Lara', 'yalvarez@gmail.com', '2012-06-10', 'Yennifer', 'Apto', '04263107832', 79, 1003);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('20908312', 'Alvarado', 'Lito Arenas', 'jalvarado@hotmail.com', '2012-06-10', 'Jesus', 'Acreditado', '04126731723', 66, 1004);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('16783213', 'Villanueva', 'Antonio Jose de Sucre', 'vedwin@hotmail.com', '2012-06-10', 'Edwin', 'Apto', '04267483121', 110, 1005);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('22763123', 'Guzman', 'Francisco', 'gedmalit@yahoo.com', '2012-06-10', 'Edmalit', 'Acreditado', '0412763123', 84, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('16809009', 'Torbello', 'Torrellas', 'tranyey@gmail.com', '2013-06-10', 'Ranyey', 'Apto', '04264120098', 74, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('15762123', 'Cuyamo', 'Calicanto', 'cteresa@gmail.com', '2013-06-10', 'Teresa', 'Acreditado', '04248742334', 93, 1003);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('20001764', 'Castillo', 'Calicanto', 'mcastillo@gmail.com', '2013-06-10', 'Marco', 'Apto', '04168741220', 102, 1004);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('15852123', 'Hernandez', 'San Vicente', 'hgrelvys@gmail.com', '2013-06-10', 'Grelvys', 'Acreditado', '0426765098', 110, 1006);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19618874', 'Torcates', 'Santo Domingo', 'torcale@gmail.com', '2013-06-10', 'Alejandra', 'Acreditado', '04265150985', 110, 1006);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('21965874', 'Infante', 'La Mendera', 'jesuinfan@gmail.com', '2013-01-15', 'Jesús', 'Aprobado', '04129857415', 79, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('22950014', 'Guerrero', 'El Tocuyo', 'Victor@yahoo.es', '2013-02-25', 'Victor', 'Aprobado', '04269854100', 95, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('20985120', 'Guevara', 'La Pieda', 'mariaguevara@hotmail.com', '2013-03-15', 'María', 'Aprobado', '04168548896', 105, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('23001547', 'Serrano', 'Carora', 'carmense@hotmail.com', '2013-05-05', 'Carmen', 'Aprobado', '04149852102', 80, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('19958774', 'Serra', 'Cerritos Blanco', 'mayeserra@gmail.com', '2013-04-14', 'Mayerlin', 'Aprobado', '04248751102', 60, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000000', 'Machado', 'Carrera 20 entre calle 30 y 31', 'lobigm@hotmail.com', '2013-02-20', 'Alejandro', 'Aprobado', '04145573270', 145, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000001', 'Perdomo', 'Carrera 21 con 50', 'lobigm@gmail.com', '2013-02-20', 'Eufemia', 'Aprobado', '0414444444', 20, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000002', 'Smith', ' 30 y 31', 'smithi@hotmail.com', '2013-02-20', 'Teolinda', 'Aprobado', '04145555555', 113, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000003', 'Matos', 'Avenida oceano pacifico', 'matomota@hotmail.com', '2013-02-20', 'Antonia', 'Aprobado', '02512315151', 100, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000004', 'Rodriguez', 'Carrera con calle y ', 'rodriguezpierozucaro@hotmail.com', '2013-02-20', 'Pedro', 'Aprobado', '04165454545', 101, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000005', 'Mejias', '60 con 17', 'greidamejias@gmail.com', '2013-02-20', 'Juana', 'Aprobado', '04268588418', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000006', 'Mendez', 'Patarata', 'march.mdma@gmail.com', '2013-04-15', 'Gladys', 'Aprobado', '04126705780', 113, 1002);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000007', 'Alvarado', 'Patarata', 'robert.alvarado6@gmail.com', '2013-04-15', 'Roberto', 'Aprobado', '04120586535', 105, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000008', 'Rodriguez', 'Patarata', 'pedror90@gmail.com', '2013-04-15', 'Pablo', 'Aprobado', '0424555555', 111, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000009', 'Lopez', 'Barrio Union', 'delbaclg@gmail.com', '2013-04-15', 'Oracio', 'Aprobado', '04120544009', 112, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000010', 'Rodriguez', 'Acarigua', 'mgrodriguez@gmail.com', '2013-04-15', 'Gracia', 'Aprobado', '04246266255', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000011', 'Andrade', 'Obelisco', 'elvisandradeb@gmail.com', '2013-04-15', 'Wiston', 'Aprobado', '0424599955', 109, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000012', 'Oropeza', 'Caracas', 'norbelisoropeza@gmail.com', '2012-10-15', 'Teresa', 'Aprobado', '0424599955', 111, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000013', 'Hernandez', 'Cubiro', 'emilio25informatic@gmail.com', '2012-10-15', 'Francisco', 'Aprobado', '0424599098', 112, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000014', 'Aldana', 'Piritu', 'kagerhe@gmail.com', '2012-10-15', 'Francisco', 'Aprobado', '04120909098', 112, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000015', 'Gallardo', 'Cubiro', 'gneilao@gmail.com', '2012-10-15', 'Carlos', 'Aprobado', '0414599218', 102, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000016', 'Paez', 'Carora', 'mariaapaezt@gmail.com', '2012-10-15', 'Claudia', 'Aprobado', '0414599218', 102, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000017', 'Colmenarez', 'Lara', 'cjoseg@gmail.com', '2012-10-15', 'Jose Manuel', 'Aprobado', '0414599218', 80, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000018', 'Sequera', 'Lara', 'sandreina@gmail.com', '2012-06-10', 'Maria', 'Aprobado', '0414599218', 55, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000019', 'Melendez', 'Concordia', 'rmelendez@gmail.com', '2012-06-10', 'Stefanie', 'Aprobado', '04266567313', 80, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000020', 'Perez', 'La Represa', 'dperez@gmail.com', '2012-06-10', 'Nathaly', 'Aprobado', '04166087621', 100, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000021', 'Alvarez', 'Jacinto Lara', 'yalvarez@gmail.com', '2012-06-10', 'Jenireth', 'Aprobado', '04263107832', 79, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000022', 'Alvarado', 'Lito Arenas', 'jalvarado@hotmail.com', '2012-06-10', 'Jesus', 'Aprobado', '04126731723', 66, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000023', 'Villanueva', 'Antonio Jose de Sucre', 'vedwin@hotmail.com', '2012-06-10', 'Edward', 'Aprobado', '04267483121', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000024', 'Guzman', 'Francisco', 'gedmalit@yahoo.com', '2012-06-10', 'Eustacio', 'Aprobado', '0412763123', 84, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000025', 'Torbello', 'Torrellas', 'tranyey@gmail.com', '2013-06-10', 'Karim', 'Aprobado', '04264120098', 74, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000026', 'Cuyamo', 'Calicanto', 'cteresa@gmail.com', '2013-06-10', 'Nohemy', 'Aprobado', '04248742334', 93, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000027', 'Castillo', 'Calicanto', 'mcastillo@gmail.com', '2013-06-10', 'Mauricio', 'Aprobado', '04168741220', 102, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000028', 'Hernandez', 'San Vicente', 'hgrelvys@gmail.com', '2013-06-10', 'Grelis', 'Aprobado', '0426765098', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000029', 'Torcates', 'Santo Domingo', 'torcale@gmail.com', '2013-06-10', 'Maria', 'Aprobado', '04265150985', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000030', 'Infante', 'La Mendera', 'jesuinfan@gmail.com', '2013-01-15', 'Manuel', 'Aprobado', '04129857415', 79, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000031', 'Guerrero', 'El Tocuyo', 'Victor@yahoo.es', '2013-02-25', 'David', 'Aprobado', '04269854100', 95, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000032', 'Guevara', 'La Pieda', 'mariaguevara@hotmail.com', '2013-03-15', 'Mariana', 'Aprobado', '04168548896', 105, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000033', 'Serrano', 'Carora', 'carmense@hotmail.com', '2013-05-05', 'Xiomara', 'Aprobado', '04149852102', 80, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000034', 'Serra', 'Cerritos Blanco', 'mayeserra@gmail.com', '2013-04-14', 'Geleynni', 'Aprobado', '04248751102', 60, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000035', 'Hernandez', 'San Vicente', 'hgrelvys@gmail.com', '2013-06-10', 'Gisel', 'Aprobado', '0426765098', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000036', 'Torcates', 'Santo Domingo', 'torcale@gmail.com', '2013-06-10', 'Luna', 'Aprobado', '04265150985', 110, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000037', 'Infante', 'La Mendera', 'jesuinfan@gmail.com', '2013-01-15', 'Juan', 'Aprobado', '04129857415', 79, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000038', 'Guerrero', 'El Tocuyo', 'Victor@yahoo.es', '2013-02-25', 'Pedro Pablo', 'Aprobado', '04269854100', 95, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000039', 'Guevara', 'La Pieda', 'mariaguevara@hotmail.com', '2013-03-15', 'Yenny', 'Aprobado', '04168548896', 105, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000040', 'Serrano', 'Carora', 'carmense@hotmail.com', '2013-05-05', 'Nohaly', 'Aprobado', '04149852102', 80, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18000041', 'Serra', 'Cerritos Blanco', 'mayeserra@gmail.com', '2013-04-14', 'Leanna', 'Aprobado', '04248751102', 60, 1001);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('232323', 'Alvarado', 'Calle 14 pueblo nuevo', 'robert@gmail.com', NULL, 'Robert', 'a', '02512662797', 100, NULL);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('23457972', 'Alcon', 'Calle 15 pueblo nuevo', 'manuel@gmail', NULL, 'Manuel', 'a', '02512662797', 100, NULL);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18782036', 'Lara', 'Calle 15 barrio union', 'sofia@gmail', NULL, 'Sofia', 'a', '02516189282', 100, NULL);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('18736025', 'Gomez', 'urb. la rosaleda', 'laura@gmail', NULL, 'Laura', 'a', '02517269208', 120, NULL);
INSERT INTO estudiante (estudiante_cedula, estudiante_apellido, estudiante_direccion, estudiante_email, estudiante_fecha, estudiante_nombre, estudiante_status, estudiante_telefono, estudiante_unidades_aprobadas, direccion_codigo) VALUES ('92637826', 'Colmenarez', 'urb. la paz', 'carlos@gmail', NULL, 'Carlos', 'a', '02518257392', 1, NULL);


--
-- TOC entry 1981 (class 0 OID 54750)
-- Dependencies: 158 1972 1985
-- Data for Name: preinscripcion_proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1001, '2013-01-22', 'Activa', '20111111', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1002, '2013-04-12', 'Inactiva', '17726019', 'RORA');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1003, '2013-02-25', 'En espera', '19887067', 'IGN');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1004, '2013-05-04', 'Activa', '18052474', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1005, '2013-03-14', 'Inactiva', '18997067', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1006, '2013-04-18', 'En espera', '17727505', 'IGN');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1007, '2013-06-16', 'Activa', '19171220', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1008, '2013-02-09', 'Inactiva', '18684069', 'RORA');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1009, '2013-07-25', 'En espera', '20178011', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1010, '2013-08-17', 'Activa', '19104287', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1011, '2013-07-02', 'Inactiva', '17666666', 'PIT');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1012, '2013-09-28', 'Activa', '19110001', 'RORA');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1013, '2013-07-14', 'Inactiva', '19112221', 'IGN');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1014, '2013-10-27', 'En espera', '18762896', 'RORA');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1015, '2013-11-26', 'Activa', '20908312', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1016, '2013-10-04', 'Inactiva', '22763123', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1017, '2013-09-13', 'En espera', '16809009', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1018, '2013-10-24', 'Activa', '15762123', 'RORA');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1019, '2013-06-19', 'Inactiva', '15762123', 'IGN');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1020, '2013-03-28', 'Activa', '21965874', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1021, '2013-03-25', 'Activa', '20985120', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1022, '2013-03-27', 'Activa', '22950014', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1023, '2013-06-01', 'Activa', '23001547', 'PCTAM02');
INSERT INTO preinscripcion_proyecto (preinscripcion_proyecto_codigo, preinscripcion_proyecto_fecha, preinscripcion_proyecto_status, estudiante_cedula, proyecto_codigo) VALUES (1024, '2013-05-28', 'Activa', '19958774', 'PCTAM02');


--
-- TOC entry 1976 (class 0 OID 54732)
-- Dependencies: 153 1985 1981
-- Data for Name: inscripcion_proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1001, '2013-02-22', 'Activo', 1001, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1002, '2013-06-12', 'Activo', 1002, 'RORA');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1003, '2013-03-25', 'Activo', 1003, 'IGN');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1004, '2013-06-04', 'Activo', 1004, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1005, '2013-06-14', 'Activo', 1005, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1006, '2013-06-18', 'Activo', 1006, 'IGN');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1007, '2013-09-16', 'Activo', 1007, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1008, '2013-03-09', 'Activo', 1008, 'RORA');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1009, '2013-10-25', 'Activo', 1009, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1010, '2013-09-17', 'Activo', 1010, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1011, '2013-09-02', 'Activo', 1011, 'PIT');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1012, '2013-11-28', 'Activo', 1012, 'RORA');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1013, '2013-09-14', 'Activo', 1013, 'IGN');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1014, '2013-12-27', 'Activo', 1014, 'RORA');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1015, '2013-12-26', 'Activo', 1015, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1016, '2013-12-04', 'Activo', 1016, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1017, '2013-10-13', 'Activo', 1017, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1018, '2013-12-24', 'Activo', 1018, 'RORA');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1019, '2013-09-19', 'Activo', 1019, 'IGN');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1020, '2013-04-04', 'Activo', 1020, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1021, '2013-03-30', 'Activo', 1021, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1022, '2013-04-02', 'Activo', 1022, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1023, '2013-06-10', 'Activo', 1023, 'PCTAM02');
INSERT INTO inscripcion_proyecto (inscripcion_proyecto_codigo, inscripcion_proyecto_fecha, inscripcion_proyecto_status, preinscripcion_proyecto_codigo, proyecto_codigo) VALUES (1024, '2013-06-01', 'Activo', 1024, 'PCTAM02');


--
-- TOC entry 1965 (class 0 OID 54694)
-- Dependencies: 141 1976 1964
-- Data for Name: actividad_asignada; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1022, '2013-06-10 00:00:00', '2013-06-05 00:00:00', 1006, 1023);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1023, '2013-06-10 00:00:00', '2013-06-05 00:00:00', 1006, 1024);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1024, '2013-06-20 00:00:00', '2013-06-18 00:00:00', 1007, 1020);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1025, '2013-09-22 00:00:00', '2013-09-19 00:00:00', 1008, 1020);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1026, '2013-06-20 00:00:00', '2013-06-18 00:00:00', 1007, 1021);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1027, '2013-09-22 00:00:00', '2013-09-19 00:00:00', 1008, 1021);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1028, '2013-06-20 00:00:00', '2013-06-18 00:00:00', 1007, 1022);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1029, '2013-09-22 00:00:00', '2013-09-19 00:00:00', 1008, 1022);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1030, '2013-07-30 00:00:00', '2013-07-25 00:00:00', 1007, 1023);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1031, '2013-10-29 00:00:00', '2013-10-19 00:00:00', 1008, 1023);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1032, '2013-07-30 00:00:00', '2013-07-25 00:00:00', 1007, 1024);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1033, '2013-10-29 00:00:00', '2013-10-19 00:00:00', 1008, 1024);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1001, '2013-12-07 00:00:00', '2013-12-06 00:00:00', 1006, 1001);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1002, '2013-12-14 00:00:00', '2013-12-13 00:00:00', 1007, 1001);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1003, '2013-12-24 00:00:00', '2013-12-23 00:00:00', 1006, 1002);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1004, '2013-12-20 00:00:00', '2013-11-20 00:00:00', 1010, 1003);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1005, '2013-12-10 00:00:00', '2013-12-03 00:00:00', 1011, 1003);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1006, '2013-12-19 00:00:00', '2013-12-14 00:00:00', 1012, 1004);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1007, '2013-12-19 00:00:00', '2013-12-13 00:00:00', 1009, 1005);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1008, '2013-12-19 00:00:00', '2013-12-17 00:00:00', 1007, 1006);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1009, '2013-12-19 00:00:00', '2013-12-16 00:00:00', 1013, 1007);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1010, '2013-12-19 00:00:00', '2013-12-18 00:00:00', 1014, 1008);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1011, '2013-12-19 00:00:00', '2013-12-19 00:00:00', 1013, 1009);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1012, '2013-12-19 00:00:00', '2013-12-11 00:00:00', 1015, 1010);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1013, '2013-12-19 00:00:00', '2013-12-14 00:00:00', 1006, 1011);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1014, '2013-12-19 00:00:00', '2013-12-13 00:00:00', 1016, 1012);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1015, '2013-12-15 00:00:00', '2013-12-01 00:00:00', 1013, 1016);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1016, '2013-11-11 00:00:00', '2013-10-22 00:00:00', 1015, 1017);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1017, '2013-12-15 00:00:00', '2013-12-10 00:00:00', 1007, 1018);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1018, '2013-11-19 00:00:00', '2013-11-13 00:00:00', 1016, 1019);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1019, '2013-04-15 00:00:00', '2013-04-10 00:00:00', 1006, 1020);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1020, '2013-04-15 00:00:00', '2013-04-10 00:00:00', 1006, 1021);
INSERT INTO actividad_asignada (actividad_asignada_codigo, actividad_asignada_fecha_culminacion, actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_proyecto_codigo) VALUES (1021, '2013-04-15 00:00:00', '2013-04-10 00:00:00', 1006, 1022);


--
-- TOC entry 1966 (class 0 OID 54697)
-- Dependencies: 142 1965
-- Data for Name: actividad_ejecutada; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1001, '2013-12-07 00:00:00', 5, 1001);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1002, '2013-12-14 00:00:00', 4, 1002);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1003, '2013-12-14 00:00:00', 3, 1002);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1004, '2013-12-19 00:00:00', 1, 1006);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1005, '2013-12-14 00:00:00', 4, 1002);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1006, '2013-12-16 00:00:00', 1, 1009);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1007, '2013-12-18 00:00:00', 1, 1010);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1008, '2013-12-15 00:00:00', 2, 1015);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1009, '2013-10-22 00:00:00', 4, 1016);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1010, '2013-12-15 00:00:00', 3, 1017);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1011, '2013-11-19 00:00:00', 2, 1018);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1012, '2013-12-24 00:00:00', 4, 1003);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1013, '2013-04-15 00:00:00', 40, 1019);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1014, '2013-04-15 00:00:00', 40, 1020);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1015, '2013-04-15 00:00:00', 40, 1021);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1016, '2013-06-10 00:00:00', 40, 1022);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1017, '2013-06-10 00:00:00', 40, 1023);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1018, '2013-06-20 00:00:00', 40, 1024);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1019, '2013-09-22 00:00:00', 40, 1025);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1020, '2013-06-20 00:00:00', 40, 1026);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1021, '2013-09-22 00:00:00', 40, 1027);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1022, '2013-06-20 00:00:00', 40, 1028);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1023, '2013-09-22 00:00:00', 40, 1029);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1024, '2013-07-30 00:00:00', 40, 1030);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1025, '2013-10-29 00:00:00', 40, 1031);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1026, '2013-07-30 00:00:00', 40, 1032);
INSERT INTO actividad_ejecutada (actividad_ejecutada_codigo, actividad_ejecutada_fecha, actividad_ejecutada_horas_cumplidas, actividadasignada_codigo) VALUES (1027, '2013-10-19 00:00:00', 40, 1033);


--
-- TOC entry 1967 (class 0 OID 54700)
-- Dependencies: 143
-- Data for Name: causa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO causa (causa_codigo, causa_nombre) VALUES (1001, 'Retiro');
INSERT INTO causa (causa_codigo, causa_nombre) VALUES (1002, 'Exoneracion');


--
-- TOC entry 1968 (class 0 OID 54703)
-- Dependencies: 144
-- Data for Name: coordinacion_sce; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO coordinacion_sce (coordinacion_sce_codigo, coordinacion_sce_nombre) VALUES (1001, 'Coordinacion General Estudiantil');


--
-- TOC entry 1982 (class 0 OID 54753)
-- Dependencies: 159
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('12574444', NULL, 'Patriciana', 'Trinitarias', 'patricianajuan@hotmail.com', 'Juan', 'Acreditado', '04264555555');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('15444444', NULL, 'Manaure', 'Carora', 'patriciamana@hotmail.com', 'Patricia', 'Apto', '04245877999');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('18555555', NULL, 'Valera', 'Cubiro', 'edwardva@ucla.edu.ve', 'Edward', 'Apto', '04247777777');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('146852666', NULL, 'Rodriguez', 'Barquisimeto Oeste', 'nohemyro@ucla.edu.ve', 'Nohemy', 'Acreditado', '04249999999');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('13777777', NULL, 'Ynfante', 'Sanare', 'aynfante@ucla.edu.ve', 'Alfredo', 'Acreditado', '04240008957');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('17534252', NULL, 'García', 'Barquisimeto Este', 'lgarcia@ucla.edu.ve', 'Luis', 'Acreditado', '04246870010');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('13774444', NULL, 'Perez', 'Lara', 'mperez@ucla.edu.ve', 'Maria', 'Acreditado', '0424000030');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('16775555', NULL, 'Espinoza', 'Cabudare', 'gespinoza@ucla.edu.ve', 'Gerana', 'Apto', '04240000040');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('5879094', NULL, 'Alvarez', 'Calicanto', 'palvarez@ucla.edu.ve', 'Petra', 'Apto', '02524219988');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('4321987', NULL, 'Abreu', 'San Pedro', 'aabreu@ucla.edu.ve', 'Anibal', 'Acreditado', '04245109834');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('8432098', NULL, 'Rodriguez', 'Calicanto', 'arodriguez@ucla.edu.ve', 'Adriana', 'Apto', '02524448829');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('13983783', NULL, 'Guedez', 'Ceneterio', 'nguedez@ucla.edu.ve', 'Nelson', 'Apto', '04165108231');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('11983009', NULL, 'Melendez', 'Francisco Torres', 'amelendez@ucla.edu.ve', 'Alejandra', 'Acreditado', '04263129087');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('13562089', NULL, 'Perez', 'El Stadium', 'jperez@ucla.edu.ve', 'Joimir', 'Acreditado', '04261631373');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('8901244', NULL, 'Vargas', 'Jacinto Lara', 'dvargas@ucla.edu.ve', 'Diana', 'Apto', '04146529087');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('6901732', NULL, 'Lopez', 'La Pastora', 'slopez@ucla.edu.ve', 'Solis', 'Acreditado', '04268523180');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('10342802', NULL, 'Villanueva', 'Antonio Jose de Sucre', 'evillanueva@ucla.edu.ve', 'Elsy', 'Apto', '02522428912');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('14890265', NULL, 'Olivera', 'Torrellas', 'yolivera@ucla.edu.ve', 'Yanrey', 'Acreditado', '04244308756');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('12345678', 9, 'Guzman', 'La Mendera', 'guzmanh@ucla.edu.ve', 'Hernan', 'Acreditado', '04148549874');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('14666666', NULL, 'Gonzalez', 'Obelisco', 'jessicaGo@hotmail.com', 'Jessica', 'Acreditado', '04126666666');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('1222222', NULL, 'Cuello', 'La 50', 'manuelcuello@hotmail.com', 'Manuel', 'Apto', '04165554555');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('19696020', NULL, 'Santeliz', 'Don Aurelio', 'santeliz27@gmail.com', 'Daniel', 'A', '04120536301');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('19637704', NULL, 'D''Amelio', 'Acarigua', 'nicolas22_08@gmail.com', 'Nicolas', 'A', '04141576220');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('18655464', NULL, 'Medina', 'Cerro Gordo', 'stalker@gmail.com', 'Moises', 'A', '04168596321');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('20009907', NULL, 'Rodriguez', 'Pueblo Nuevo', 'maria@gmail.com', 'Maria', 'A', '04128574156');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('19482533', NULL, 'Freitez', 'El valle', 'freitez@gmail.com', 'Maria', 'A', '04148579632');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('19779598', NULL, 'Bonier', 'Calle 46', 'greciabonier@gmail.com', 'Grecia', 'A', '04167558965');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('19857632', NULL, 'Perez', 'Crepusculos', 'pedroperez@gmail.com', 'Pedro', 'A', '04128569321');
INSERT INTO profesor (profesor_cedula, usuario_codigo, profesor_apellido, profesor_direccion, profesor_email, profesor_nombre, profesor_status, profesor_telefono) VALUES ('12578987', 1, 'Vergara', 'Cabudare', 'randyver@hotmail.com', 'Randy', 'Apto', '04144547457');


--
-- TOC entry 1969 (class 0 OID 54706)
-- Dependencies: 145 1982 1968
-- Data for Name: coordinador_sce; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO coordinador_sce (coordinador_sce_codigo, coordinador_sce_fecha_culminacion, coordinador_sce_fecha_inicio, coordinador_sce_status, coordinacion_codigo, profesor_cedula) VALUES (1002, '2013-12-07', '2012-12-07', 'Activo', 1001, '12574444');
INSERT INTO coordinador_sce (coordinador_sce_codigo, coordinador_sce_fecha_culminacion, coordinador_sce_fecha_inicio, coordinador_sce_status, coordinacion_codigo, profesor_cedula) VALUES (1001, '2012-12-07', '2010-12-07', 'Inactivo', 1001, '17534252');


--
-- TOC entry 1971 (class 0 OID 54712)
-- Dependencies: 147 1970 1982
-- Data for Name: director_programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO director_programa (director_programa_codigo, director_programa_fecha_culminacion, director_programa_fecha_inicio, director_programa_status, direccion_codigo, profesor_cedula) VALUES (1001, '2014-12-07', '2013-12-07', 'Activo', 1002, '1222222');
INSERT INTO director_programa (director_programa_codigo, director_programa_fecha_culminacion, director_programa_fecha_inicio, director_programa_status, direccion_codigo, profesor_cedula) VALUES (1002, '2014-12-07', '2012-12-07', 'Activo', 1001, '13777777');
INSERT INTO director_programa (director_programa_codigo, director_programa_fecha_culminacion, director_programa_fecha_inicio, director_programa_status, direccion_codigo, profesor_cedula) VALUES (1003, '2012-12-07', '2010-12-07', 'Inactivo', 1001, '18555555');
INSERT INTO director_programa (director_programa_codigo, director_programa_fecha_culminacion, director_programa_fecha_inicio, director_programa_status, direccion_codigo, profesor_cedula) VALUES (1004, '2014-12-07', '2012-12-07', 'Activo', 1004, '14666666');
INSERT INTO director_programa (director_programa_codigo, director_programa_fecha_culminacion, director_programa_fecha_inicio, director_programa_status, direccion_codigo, profesor_cedula) VALUES (1005, '2012-12-07', '2010-12-07', 'Inactivo', 1003, '14666666');


--
-- TOC entry 1979 (class 0 OID 54741)
-- Dependencies: 156 1967
-- Data for Name: motivo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO motivo (motivo_codigo, motivo_descripcion, motivo_nombre, causa_codigo) VALUES (1001, 'Se retiro por enfermedad', 'Retiro enfermedad', 1001);
INSERT INTO motivo (motivo_codigo, motivo_descripcion, motivo_nombre, causa_codigo) VALUES (1002, 'Exoneracion por haber hecho el servicio en otra carrera', 'Exoneracion simur', 1002);


--
-- TOC entry 1973 (class 0 OID 54718)
-- Dependencies: 149 1972 1979
-- Data for Name: exonerado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO exonerado (exonerado_codigo, exonerado_fecha, estudiante_cedula, motivo_codigo) VALUES (1001, '2012-10-20', '20001764', 1002);
INSERT INTO exonerado (exonerado_codigo, exonerado_fecha, estudiante_cedula, motivo_codigo) VALUES (1002, '2013-06-10', '22763123', 1001);


--
-- TOC entry 1974 (class 0 OID 54721)
-- Dependencies: 150
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO grupo (grupo_codigo, grupo_descripcion, grupo_status, grupo_nombre) VALUES (1001, 'administrador', 'a', 'admin');
INSERT INTO grupo (grupo_codigo, grupo_descripcion, grupo_status, grupo_nombre) VALUES (1002, 'coordinación Servicio Comunitario Estudiantil', 'a', 'coordinacionSCE');
INSERT INTO grupo (grupo_codigo, grupo_descripcion, grupo_status, grupo_nombre) VALUES (1003, 'representante profesoral', 'a', 'representanteprofesoral');
INSERT INTO grupo (grupo_codigo, grupo_descripcion, grupo_status, grupo_nombre) VALUES (1004, 'responsableproyecto', 'a', 'responsableproyecto');
INSERT INTO grupo (grupo_codigo, grupo_descripcion, grupo_status, grupo_nombre) VALUES (1005, 'directorprograma', 'a', 'directorprograma');
INSERT INTO grupo (grupo_codigo, grupo_descripcion, grupo_status, grupo_nombre) VALUES (1006, 'hace todo', 'a', 'todo');


--
-- TOC entry 1980 (class 0 OID 54744)
-- Dependencies: 157
-- Data for Name: nodo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1002, 'a', 'Direccion de Programa', 1001, 0, 'F', '/WEB-INF/vista/view/view.maestros/DireccionPrograma.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1004, 'a', 'Estudiantes Aptos', 1001, 2, 'F', '/WEB-INF/vista/view/view.maestros/RegistrarEstudianteApto.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1003, 'a', 'Profesores', 1001, 1, 'F', '/WEB-INF/vista/view/view.maestros/RegistrarProfesores.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1017, 'a', 'Inscripcion de Estudiantes', 1016, 12, 'F', '/WEB-INF/vista/view/view.transacciones/FormalizarInscripcionProyecto.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1018, 'a', 'Asignar Actividades', 1016, 13, 'F', '/WEB-INF/vista/view/view.transacciones/AsignarActividades.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1077, 'a', 'Restaurar Base de Datos', 1074, 68, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1069, 'a', 'Registrar Motivo', 1005, 61, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1001, 'a', 'Registros de Datos Basicos', 0, 100, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1071, 'a', 'Noticias', 1073, 62, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1072, 'a', 'Documentos', 1073, 63, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1073, 'a', 'Portal', 1070, 64, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1074, 'a', 'Generales', 1070, 65, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1075, 'a', 'Grupo', 1001, 66, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1076, 'a', 'Respaldar Base de Datos', 1074, 67, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1006, 'a', 'Integrantes SCE', 1005, 3, 'F', '/WEB-INF/vista/view/view.maestros/IntegrantesSce.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1007, 'a', 'Acreditar Profesor', 1005, 4, 'F', '/WEB-INF/vista/view/view.maestros/RegistroProfesoresAcreditados.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1008, 'a', 'Exonerar Estudiantes', 1005, 5, 'F', '/WEB-INF/vista/view/view.maestros/Exonerados.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1010, 'a', 'Planificar Taller', 1009, 5, 'F', '/WEB-INF/vista/view/view.maestros/PlanificacionDelTaller.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1011, 'a', 'Inscripcion de Estudiantes', 1009, 7, 'F', '/WEB-INF/vista/view/view.maestros/InscripcionAlTaller.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1012, 'a', 'Evaluacion del Taller', 1009, 8, 'F', '/WEB-INF/vista/view/view.maestros/EvaluacionDelTaller.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1014, 'a', 'Registrar Proyectos', 1013, 9, 'F', '/WEB-INF/vista/view/view.maestros/RegistrarProyecto.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1015, 'a', 'Evaluar Preinscripcion', 1013, 10, 'F', '/WEB-INF/vista/view/view.transacciones/EvaluacionDeInscripcion.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1044, 'a', 'Estatus de Estudiantes', 1034, 35, 'F', '/WEB-INF/vista/view/view.reportes.abiertos/ReporteEstatusEstudiante.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1040, 'a', 'Ejecutandose', 1037, 36, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1048, 'a', 'Estudiantes Pre-Inscritos', 1035, 42, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1065, 'a', 'Proyectos mas Demandados ', 1060, 58, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1041, 'a', 'Ejecutados', 1037, 37, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1059, 'a', 'Promedios', 1033, 52, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1055, 'a', 'Consulta Profesores Participantes
', 1036, 48, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1050, 'a', 'Estudiantes Inscritos por Taller', 1035, 44, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1060, 'a', 'Porcentajes', 1033, 53, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1045, 'a', 'Estatus de Proyectos', 1038, 39, 'F', '/WEB-INF/vista/view/view.reportes.abiertos/ProyectoEstatud.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1043, 'a', 'Actividades Asignadas por Estudiante', 1039, 70, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1046, 'a', 'Proyectos por Profesor Responsable', 1038, 40, 'F', '/WEB-INF/vista/view/view.reportes.abiertos/ReporteProyectoProfResponsable.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1051, 'a', 'Estudiantes Retirados por Proyecto', 1035, 45, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1053, 'a', 'Consulta de Profesores', 1036, 50, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1057, 'a', 'Estudiantes por Estatus', 1059, 50, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1047, 'a', 'Horas Cumplidas por Estudiantes', 1035, 41, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1052, 'a', 'Desempeño del Estudiante en el SCE', 1035, 46, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1058, 'a', 'Estudiantes Aptos por U.C. Aprobadas', 1059, 51, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1054, 'a', 'Consulta Profesores Responsables', 1036, 47, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1029, 'a', 'Estudiantes', 1022, 1000, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1078, 'a', 'Ejecutandose', 1037, 69, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1042, 'a', 'Proyectos Disponibles', 1038, 37, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1061, 'a', 'Tiempo que tarda un Estud. en cumplir SCE', 1059, 54, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1030, 'a', 'Profesores', 1022, 1100, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1031, 'a', 'Talleres', 1022, 1200, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1056, 'a', 'Actividades Cumplidas por Estudiante', 1039, 49, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1062, 'a', 'Estud. que participan en SCE del DCYT', 1060, 55, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1067, 'a', 'Desempeño del Estud. en SCE por Carga Acad.', 1060, 59, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1034, 'a', 'Abiertos', 1029, 29, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1035, 'a', 'Especiales', 1029, 30, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1063, 'a', 'Estudiantes Acreditados por Año', 1060, 56, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1036, 'a', 'Abiertos', 1030, 31, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1064, 'a', 'Estud. que finalizaron el SCE', 1060, 57, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1037, 'a', 'Abiertos', 1031, 32, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1038, 'a', 'Abiertos', 1032, 33, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1039, 'a', 'Especiales', 1032, 34, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1032, 'a', 'Proyectos', 1022, 1300, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1033, 'a', 'Estadisticos', 1022, 1400, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1005, 'a', 'Coordinacion SCE', 0, 200, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1009, 'a', 'Talleres', 0, 300, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1013, 'a', 'Proyectos', 0, 400, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1016, 'a', 'Prestacion SCE', 0, 500, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1020, 'a', 'Culminacion SCE', 0, 600, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1022, 'a', 'Reportes', 0, 900, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1028, 'a', 'Administrar Sistema', 0, 800, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1049, 'a', 'Estudiantes Incritos por Proyecto', 1035, 43, 'F', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1070, 'a', 'Configuraciones', 0, 700, 'M', NULL);
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1021, 'a', 'Registro de Culminación SCE', 1020, 15, 'F', '/WEB-INF/vista/view/view.maestros/EstudiantesCulminado.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1019, 'a', 'Registrar Ejecución', 1016, 14, 'F', '/WEB-INF/vista/view/view.transacciones/RegistrarPrestacionSCE.zul');
INSERT INTO nodo (nodo_codigo, nodo_status, nodo_funcion, nodo_padre, nodo_prioridad, nodo_tipo, nodo_vinculo) VALUES (1079, 'a', 'Carta de Culminación', 1020, 70, 'F', '/WEB-INF/vista/view/view.reportes.abiertos/ReporteEstudianteAprobado.zul');


--
-- TOC entry 1975 (class 0 OID 54727)
-- Dependencies: 151 1980 1974
-- Data for Name: grupo_funcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1001, 1002);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1001, 1003);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1001, 1004);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1010);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1014);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1021);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1030);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1031);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1032);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1033);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1034);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1035);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1036);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1037);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1038);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1039);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1040);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1041);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1042);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1044);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1045);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1046);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1047);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1048);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1049);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1050);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1051);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1052);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1054);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1055);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1056);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1057);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1058);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1059);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1060);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1061);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1062);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1063);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1064);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1065);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1067);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1006);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1007);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1002, 1008);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1003, 1008);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1003, 1011);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1003, 1012);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1003, 1015);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1003, 1017);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1003, 1021);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1015);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1017);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1018);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1019);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1021);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1030);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1031);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1032);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1033);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1034);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1035);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1036);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1037);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1038);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1039);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1040);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1041);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1042);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1044);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1045);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1046);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1047);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1048);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1049);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1050);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1051);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1052);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1054);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1055);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1056);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1057);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1058);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1059);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1060);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1061);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1062);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1063);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1064);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1065);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1004, 1067);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1006);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1030);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1031);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1032);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1033);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1034);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1035);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1036);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1037);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1038);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1039);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1040);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1041);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1042);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1044);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1045);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1046);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1047);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1048);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1049);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1050);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1051);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1052);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1054);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1055);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1056);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1057);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1058);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1059);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1060);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1061);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1062);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1063);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1064);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1065);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1005, 1067);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1002);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1003);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1004);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1006);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1007);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1008);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1014);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1015);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1010);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1011);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1012);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1017);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1018);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1019);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1030);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1031);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1032);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1033);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1034);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1035);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1036);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1037);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1038);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1039);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1040);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1041);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1042);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1044);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1045);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1046);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1047);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1048);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1049);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1050);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1051);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1052);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1054);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1055);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1056);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1057);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1058);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1059);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1060);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1061);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1062);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1063);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1064);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1065);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1067);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1021);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1069);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1070);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1071);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1072);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1075);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1076);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1077);
INSERT INTO grupo_funcion (grupo_codigo, nodo_codigo) VALUES (1006, 1079);


--
-- TOC entry 1989 (class 0 OID 54780)
-- Dependencies: 166 1982
-- Data for Name: taller; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (1001, '2013-11-30 00:00:00', '2013-11-01 00:00:00', 24, 'Taller para alumnos de 

informatica', '2013-12-05 00:00:00', '2013-12-01 00:00:00', 'SEDUCLA', 'Virtual', 'TInformatica', 'Activo', '11983009');
INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (1002, '2013-10-30 00:00:00', '2013-10-01 00:00:00', 24, 'Taller para TELEMATICA', '2013-11-05 00:00:00', '2013-11-01 00:00:00', 'SEDUCLA', 'Virtual', 'TTelematica', 'Activo', '6901732');
INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (1003, '2013-10-30 00:00:00', '2013-10-01 00:00:00', 24, 'Taller para Matematica', '2013-11-05 00:00:00', '2013-11-01 00:00:00', 'Aula de 

Clase', 'Presencial', 'TMatematica', 'Activo', '14890265');
INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (1004, '2013-10-30 00:00:00', '2013-10-01 00:00:00', 24, 'Taller para Analisis', '2013-11-05 00:00:00', '2013-11-01 00:00:00', 'SEDUCLA', 'Virtual', 'TAnalisis', 'Activo', '11983009');
INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (1005, '2013-10-29 00:00:00', '2013-10-01 00:00:00', 24, 'Taller para Produccion', '2013-11-05 00:00:00', '2013-10-30 00:00:00', 'SEDUCLA', 'Virtual', 'TProduccion', 'Activo', '4321987');
INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (1006, '2013-10-29 00:00:00', '2013-10-01 00:00:00', 24, 'Taller para Fisica', '2013-11-05 00:00:00', '2013-10-30 00:00:00', 'SEDUCLA', 'Virtual', 'TFisica', 'Activo', '11983009');
INSERT INTO taller (taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio, taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, taller_fecha_inicio, taller_lugar, taller_modalidad, taller_nombre, taller_status, profesor_cedula) VALUES (4, '2014-02-14 00:00:00', '2014-02-06 00:00:00', 16, 'tttt', '2014-02-22 00:00:00', '2014-02-15 00:00:00', 'asasss', 'Presencial', 'Realidad Comunitaria', 'Activo', '12574444');


--
-- TOC entry 1977 (class 0 OID 54735)
-- Dependencies: 154 1972 1989
-- Data for Name: inscripcion_taller; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1003, '2013-10-10', 'Activo', '12456789', 1003);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1004, '2013-10-20', 'Activo', '19113331', 1004);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1005, '2013-10-10', 'Activo', '19111111', 1005);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1006, '2013-03-20', 'Activo', '21965874', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1007, '2013-03-20', 'Activo', '20985120', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1008, '2013-03-20', 'Activo', '22950014', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1009, '2013-05-20', 'Activo', '23001547', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1010, '2013-05-20', 'Activo', '19958774', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1050, '2013-05-20', 'Activo', '18000000', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1051, '2013-05-20', 'Activo', '18000001', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1052, '2013-05-20', 'Activo', '18000002', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1053, '2013-05-20', 'Activo', '18000003', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1054, '2013-05-20', 'Activo', '18000004', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1055, '2013-05-20', 'Activo', '18000005', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1056, '2013-05-20', 'Activo', '18000006', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1057, '2013-05-21', 'Activo', '18000007', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1058, '2013-05-21', 'Activo', '18000008', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1059, '2013-05-21', 'Activo', '18000009', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1060, '2013-05-22', 'Activo', '18000010', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1061, '2013-05-22', 'Activo', '18000011', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1062, '2013-05-22', 'Activo', '18000012', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1063, '2013-05-21', 'Activo', '18000013', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1065, '2013-05-21', 'Activo', '18000015', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1066, '2013-05-19', 'Activo', '18000016', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1067, '2013-05-19', 'Activo', '18000017', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1068, '2013-05-19', 'Activo', '18000018', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1069, '2013-05-20', 'Activo', '18000019', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1070, '2013-05-19', 'Activo', '18000020', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1071, '2013-05-20', 'Activo', '18000021', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1072, '2013-05-20', 'Activo', '18000022', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1073, '2013-05-20', 'Activo', '18000023', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1074, '2013-05-20', 'Activo', '18000024', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1075, '2013-05-23', 'Activo', '18000025', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1076, '2013-05-23', 'Activo', '18000026', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1077, '2013-05-23', 'Activo', '18000027', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1078, '2013-05-23', 'Activo', '18000028', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1079, '2013-05-23', 'Activo', '18000029', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1080, '2013-05-23', 'Activo', '18000030', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1081, '2013-05-23', 'Activo', '18000031', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1082, '2013-05-23', 'Activo', '18000032', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1083, '2013-05-21', 'Activo', '18000033', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1084, '2013-05-21', 'Activo', '18000034', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1085, '2013-05-22', 'Activo', '18000035', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1086, '2013-05-22', 'Activo', '18000036', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1087, '2013-05-22', 'Activo', '18000037', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1088, '2013-05-20', 'Activo', '18000038', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1089, '2013-05-23', 'Activo', '18000039', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1090, '2013-05-21', 'Activo', '18000040', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (1091, '2013-05-20', 'Activo', '18000041', 1001);
INSERT INTO inscripcion_taller (inscripcion_taller_codigo, inscripcion_taller_fecha, inscripcion_taller_status, estudiante_cedula, taller_codigo) VALUES (6, '2014-02-05', 'Activo', '20001764', 1001);


--
-- TOC entry 1990 (class 0 OID 54786)
-- Dependencies: 167
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1041, '75N0FC2G', true, '', '', 0, '', '1222222');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1042, '9GJLENAQ', true, '', '', 0, '', '13774444');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1043, '07UCZ8WI', true, '', '', 0, '', '14890265');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1044, 'JZBKKLG9', true, '', '', 0, '', '17534252');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1045, 'CBC81GLA', true, '', '', 0, '', '11983009');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1046, '7NA6GXB5', true, '', '', 0, '', '13777777');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1047, 'GTZUYZWU', true, '', '', 0, '', '12574444');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1048, 'admin', true, '', '', 0, '', '12345678');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (1, '6UXF4L4V', true, '', '', 0, '', '12578987');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (2, 'XSUDZY5B', true, '', '', 0, '', '12574444');
INSERT INTO usuario (usuario_codigo, usuario_clave, usuario_status, archivo_contenido, archivo_nombre, archivo_tamano, archivo_tipo, usuario_nombre) VALUES (3, 'LABQUYX6', true, '', '', 0, '', '12574444');


--
-- TOC entry 1978 (class 0 OID 54738)
-- Dependencies: 155 1990 1974
-- Data for Name: miembro_grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO miembro_grupo (grupo_codigo, usuario_codigo) VALUES (1006, 1048);


--
-- TOC entry 1983 (class 0 OID 54759)
-- Dependencies: 160 1982 1964
-- Data for Name: profesor_participante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1006, '17534252');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1007, '13774444');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1008, '4321987');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1009, '11983009');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1010, '13562089');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1011, '6901732');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1012, '10342802');
INSERT INTO profesor_participante (actividad_codigo, profesor_cedula) VALUES (1013, '14890265');


--
-- TOC entry 1984 (class 0 OID 54762)
-- Dependencies: 161 1985 1982
-- Data for Name: profesor_responsable; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('PCTAM02', '15444444');
INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('PIT', '13777777');
INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('RORA', '17534252');
INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('IGN', '13774444');
INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('PIT', '4321987');
INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('RORA', '11983009');
INSERT INTO profesor_responsable (proyecto_codigo, profesor_cedula) VALUES ('IGN', '13562089');


--
-- TOC entry 1986 (class 0 OID 54771)
-- Dependencies: 163 1970 1985
-- Data for Name: proyectos_por_programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO proyectos_por_programa (proyecto_codigo, direccion_codigo) VALUES ('PCTAM02', 1001);
INSERT INTO proyectos_por_programa (proyecto_codigo, direccion_codigo) VALUES ('PCTAM02', 1004);
INSERT INTO proyectos_por_programa (proyecto_codigo, direccion_codigo) VALUES ('IGN', 1005);
INSERT INTO proyectos_por_programa (proyecto_codigo, direccion_codigo) VALUES ('IGN', 1006);


--
-- TOC entry 1987 (class 0 OID 54774)
-- Dependencies: 164 1970 1982
-- Data for Name: representante_profesoral; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO representante_profesoral (representante_profesoral_codigo, representante_profesoral_estatus, representante_profesoral_fecha_culminacion, representante_profesoral_fecha_inicio, direccion_codigo, profesor_cedula) VALUES (1001, 'Activo', '2013-07-10', '2011-10-20', 1001, '14890265');
INSERT INTO representante_profesoral (representante_profesoral_codigo, representante_profesoral_estatus, representante_profesoral_fecha_culminacion, representante_profesoral_fecha_inicio, direccion_codigo, profesor_cedula) VALUES (1002, 'Activo', '2013-11-20', '2012-10-20', 1002, '13562089');
INSERT INTO representante_profesoral (representante_profesoral_codigo, representante_profesoral_estatus, representante_profesoral_fecha_culminacion, representante_profesoral_fecha_inicio, direccion_codigo, profesor_cedula) VALUES (1003, 'Activo', '2013-12-12', '2012-09-20', 1001, '11983009');
INSERT INTO representante_profesoral (representante_profesoral_codigo, representante_profesoral_estatus, representante_profesoral_fecha_culminacion, representante_profesoral_fecha_inicio, direccion_codigo, profesor_cedula) VALUES (1004, 'Activo', '2013-10-20', '2012-04-03', 1002, '13774444');


--
-- TOC entry 1988 (class 0 OID 54777)
-- Dependencies: 165 1976 1979
-- Data for Name: retiro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO retiro (retiro_codigo, retiro_fecha, retiro_status, inscripcion_proyecto_codigo, motivo_codigo) VALUES (1001, '2013-04-25', 'Contable', 1001, 1001);
INSERT INTO retiro (retiro_codigo, retiro_fecha, retiro_status, inscripcion_proyecto_codigo, motivo_codigo) VALUES (1002, '2013-04-30', 'Contable', 1001, 1001);
INSERT INTO retiro (retiro_codigo, retiro_fecha, retiro_status, inscripcion_proyecto_codigo, motivo_codigo) VALUES (1003, '2013-10-10', 'Contable', 1001, 1002);
INSERT INTO retiro (retiro_codigo, retiro_fecha, retiro_status, inscripcion_proyecto_codigo, motivo_codigo) VALUES (1004, '2013-12-19', 'Contable', 1001, 1002);


-- Completed on 2014-02-05 21:23:56

--
-- PostgreSQL database dump complete
--

