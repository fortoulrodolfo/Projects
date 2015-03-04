  /*Insertando Proyecto*/
 insert into proyecto(proyecto_codigo,proyecto_descripcion, proyecto_status, proyecto_nombre) values ('PCTAM','abuelos tecnologicos','a', 'PCTAM proyecto');
 insert into proyecto(proyecto_codigo,proyecto_descripcion, proyecto_status, proyecto_nombre) values ('PCTAM02','abuelos tecnologicos 2.0','a', 'PCTAM Proyecot 2');
 
/*Insertando Actividad*/
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (001,5,'registrar abuelos1', 'PCTAM');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (002,4,'registrar abuelos2', 'PCTAM');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (003,3,'registrar abuelos3', 'PCTAM');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (004,2,'registrar abuelos4', 'PCTAM');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (005,1,'registrar abuelos5', 'PCTAM');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (006,5,'registrar abuelos6', 'PCTAM02');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (007,6,'registrar abuelos7', 'PCTAM02');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (008,7,'registrar abuelos8', 'PCTAM02');
 insert into actividad (actividad_codigo, actividad_cantidad_horas, actividad_descripcion, proyecto_codigo) values (009,4,'registrar abuelos9', 'PCTAM02');

/*Insertando Profesores*/
INSERT INTO profesor(profesor_cedula, profesor_apellido, profesor_direccion, profesor_email,profesor_nombre, profesor_status, profesor_telefono)
    VALUES ('124578987', 'Vergara', 'cabudare', 'adawdawd@hotmail.com','Randy', 'N', '04144547457');
INSERT INTO profesor(profesor_cedula, profesor_apellido, profesor_direccion, profesor_email,profesor_nombre, profesor_status, profesor_telefono)
    VALUES ('124574444', 'patriciana', 'trinitarias', 'ad4aw123d@hotmail.com','juan', 'A', '04264555555');
INSERT INTO profesor(profesor_cedula, profesor_apellido, profesor_direccion, profesor_email,profesor_nombre, profesor_status, profesor_telefono)
    VALUES ('1222222', 'cuello', 'la 50', '1234ksksks@hotmail.com','manuel', 'A', '0416555555');
INSERT INTO profesor(profesor_cedula, profesor_apellido, profesor_direccion, profesor_email,profesor_nombre, profesor_status, profesor_telefono)
    VALUES ('1333333', 'gonzalez', 'obelisco', 'junacho@hotmail.com','jessica', 'N', '04126666666');
INSERT INTO profesor(profesor_cedula, profesor_apellido, profesor_direccion, profesor_email,profesor_nombre, profesor_status, profesor_telefono)
    VALUES ('1444444', 'manaure', 'carora', 'unidos@hotmail.com','patricia', 'N', '042458779999');

/*Insertando Profesor participante*/
    INSERT INTO profesor_participante(profesor_cedula, actividad_codigo)VALUES ('124578987', 001);
    INSERT INTO profesor_participante( profesor_cedula, actividad_codigo) VALUES ('124578987', 002);
    INSERT INTO profesor_participante( profesor_cedula, actividad_codigo) VALUES ('124578987', 003);
    INSERT INTO profesor_participante( profesor_cedula, actividad_codigo) VALUES ('124578987', 004);
    INSERT INTO profesor_participante(profesor_cedula, actividad_codigo)VALUES ('124578987', 005);
    INSERT INTO profesor_participante(profesor_cedula, actividad_codigo)VALUES ('124574444', 006);
    INSERT INTO profesor_participante(profesor_cedula, actividad_codigo) VALUES ('124574444', 007);
    INSERT INTO profesor_participante(profesor_cedula, actividad_codigo) VALUES ('124574444', 008);
    INSERT INTO profesor_participante( profesor_cedula, actividad_codigo) VALUES ('124574444', 009);

/*Insertando Responsables por proyecto*/

INSERT INTO profesor_responsable(
            profesor_cedula, proyecto_codigo)
    VALUES ('1444444', 'PCTAM');INSERT INTO profesor_responsable(
            profesor_cedula, proyecto_codigo)
    VALUES ('1333333', 'PCTAM02');

/*Insertando Direccion de programa*/
INSERT INTO direccion_programa(direccion_codigo, direccion_nombre, direccion_unidades_credito)VALUES (001, 'INFORMATICA', 160);
INSERT INTO direccion_programa(direccion_codigo, direccion_nombre, direccion_unidades_credito)VALUES (002, 'TELEMATICA', 175);

/*Insertando Estudiante*/
INSERT INTO estudiante(estudiante_cedula, estudiante_apellido, estudiante_direccion, 
            estudiante_email, estudiante_nombre, estudiante_status, estudiante_telefono, 
            estudiante_unidades_aprobadas, direccion_codigo)
    VALUES ('19294894', 'Lobig','carrera 20 entre calle 30 y 31' ,'lobigm@hotmail.com','Sergio', 'N','04145573270',145, 001);
INSERT INTO estudiante(estudiante_cedula, estudiante_apellido, estudiante_direccion, 
            estudiante_email, estudiante_nombre, estudiante_status, estudiante_telefono, 
            estudiante_unidades_aprobadas, direccion_codigo)
    VALUES ('111111', 'camacaro','carrera 21 con 50' ,'lobigm@gmail.com','efigenia', 'A','0414444444',20, 001);
INSERT INTO estudiante(estudiante_cedula, estudiante_apellido, estudiante_direccion, 
            estudiante_email, estudiante_nombre, estudiante_status, estudiante_telefono, 
            estudiante_unidades_aprobadas, direccion_codigo)
    VALUES ('111234555', 'Smith',' 30 y 31' ,'smithi@hotmail.com','Simur', 'N','04145555555',113, 002);
INSERT INTO estudiante(estudiante_cedula, estudiante_apellido, estudiante_direccion, 
            estudiante_email, estudiante_nombre, estudiante_status, estudiante_telefono, 
            estudiante_unidades_aprobadas, direccion_codigo)
    VALUES ('4214142', 'Matos','avenida oceano pacifico' ,'matomota@hotmail.com','petra', 'A','02512315151',100, 002);
INSERT INTO estudiante(estudiante_cedula, estudiante_apellido, estudiante_direccion, 
            estudiante_email, estudiante_nombre, estudiante_status, estudiante_telefono, 
            estudiante_unidades_aprobadas, direccion_codigo)
    VALUES ('123456789', 'rodriguez','carrera con calle y ' ,'rodriguezpierozucaro@hotmail.com','Piero', 'N','04165454545',101, 002);


/*Insertando Preinscripciones Proyecto*/
INSERT INTO preinscripcion_proyecto(
            preinscripcion_codigo, estudiante_cedula, proyecto_codigo)
    VALUES (001, '19294894', 'PCTAM');
INSERT INTO preinscripcion_proyecto(
            preinscripcion_codigo, estudiante_cedula, proyecto_codigo)
    VALUES (002, '111111', 'PCTAM02');


/*Insertando Inscripcione Proyecto*/
INSERT INTO inscripcion_proyecto(
            inscripcion_codigo, preinscripcion_codigo, proyecto_codigo)
    VALUES (001, 001, 'PCTAM');
INSERT INTO inscripcion_proyecto(
            inscripcion_codigo, preinscripcion_codigo, proyecto_codigo)
    VALUES (002, 002, 'PCTAM02');

/*Insertando Actividad Ejecutada*/
INSERT INTO actividad_asiganada(
            actividad_asignada_codigo, actividad_asignada_fecha_culminacion, 
            actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_codigo)
    VALUES (001, '07/12/2013','06/12/2013', 001, 001);
INSERT INTO actividad_asiganada(
            actividad_asignada_codigo, actividad_asignada_fecha_culminacion, 
            actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_codigo)
    VALUES (002, '14/12/2013','13/12/2013', 002, 001);
INSERT INTO actividad_asiganada(
            actividad_asignada_codigo, actividad_asignada_fecha_culminacion, 
            actividad_asignada_fecha_inicio, actividad_codigo, inscripcion_codigo)
    VALUES (003, '24/12/2013','23/12/2013', 006, 002);

/*Insertando Actividad Ejecutada*/
INSERT INTO actividad_ejecutada(
            actividad_ejecutada_codigo, actividad_ejecutada_horas_cumplidas, 
            actividadasignada_codigo)
    VALUES (001, 5, 
            001);
INSERT INTO actividad_ejecutada(
            actividad_ejecutada_codigo, actividad_ejecutada_horas_cumplidas, 
            actividadasignada_codigo)
    VALUES (002, 4, 
            002);

/*Insertando Director de Programa*/
INSERT INTO director_programa(directorprograma_codigo, directorprograma_estatus, direccion_programa_fecha,direccion_codigo, profesor_cedula)
    VALUES (001, 'A', '07/12/2013', 001, '124578987');
INSERT INTO director_programa(directorprograma_codigo, directorprograma_estatus, direccion_programa_fecha, direccion_codigo, profesor_cedula)
    VALUES (002, 'n', '07/12/2012', 001, '124574444');

/*Insertando Coordinacion*/
INSERT INTO coordinacion_sce(
            coordinacion_codigo, coordinacion_nombre)
    VALUES (001, 'Coordinacion General Estudiantil');

/*Insertando Coordinacion*/
INSERT INTO coordinadorsce( coordinadorsce_codigo, coordinadorsce_estatus, coordinadorsce_fecha, coordinacion_codigo, profesor_cedula)
    VALUES (002, 'A', '07/12/2013', 001, '1222222');
INSERT INTO coordinadorsce( coordinadorsce_codigo, coordinadorsce_estatus, coordinadorsce_fecha, coordinacion_codigo, profesor_cedula)
    VALUES (001, 'I', '07/12/2010', 001, '1444444');


/*Insertando Taller*/
INSERT INTO taller( taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio,  taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, 
            taller_fecha_inicio)
    VALUES (001, '30/11/2013', '01/11/2013', 24, 'Taller para alumnos de informatica', '05/12/2013','01/12/2013');

INSERT INTO taller( taller_codigo, taller_inscripcion_fecha_final, taller_inscripcion_fecha_inicio,  taller_cantidad_horas, taller_descripcion, taller_fecha_culminacion, 
            taller_fecha_inicio)
    VALUES (002, '30/10/2013', '01/10/2013', 24, 'Taller para TELEMATICA', '05/11/2013','01/11/2013');

/*Insertando inscripciones en el taller*/
INSERT INTO inscripcion_taller(inscripciontaller_codigo, estudiante_cedula, taller_codigo)
    VALUES (001, '111234555', 001);
INSERT INTO inscripcion_taller(inscripciontaller_codigo, estudiante_cedula, taller_codigo)
    VALUES (002, '19294894', 001);

/*Insertando Miembros de coordinacion*/
INSERT INTO miembros_coordinacion( miembros_codigo, direccion_codigo, profesor_cedula)
    VALUES (001, 001, '124574444');
INSERT INTO miembros_coordinacion( miembros_codigo, direccion_codigo, profesor_cedula)
    VALUES (002, 002, '124578987');
INSERT INTO miembros_coordinacion( miembros_codigo, direccion_codigo, profesor_cedula)
    VALUES (003, 001, '1333333');
INSERT INTO miembros_coordinacion( miembros_codigo, direccion_codigo, profesor_cedula)
    VALUES (004, 002, '1444444');


/*Insert Proyecto Por Programa*/

INSERT INTO proyectos_por_programa(
            direccion_codigo, proyecto_codigo)
    VALUES (001, 'PCTAM');
INSERT INTO proyectos_por_programa(
            direccion_codigo, proyecto_codigo)
    VALUES (001, 'PCTAM02');
