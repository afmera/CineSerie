/*==============================================================*/
/* create database: db_cine_serie                              	*/
/*==============================================================*/
-- create database db_cine_serie;
----------------------------------------------------------------------------------------------------
/*==============================================================*/
/* table: pelicula_serie                                        */
/*==============================================================*/
create table pelicula_serie
(
    ps_id serial not null,
    ps_titulo varchar(100) not null,
    ps_ano_lanzamiento date null,
    ps_longitud_minutos varchar(15) not null,
    ps_sinopsis varchar(10000) not null,
	ps_tipo varchar(15) not null
);
/*==============================================================*/
/* table: genero                                             	*/
/*==============================================================*/
create table genero
(
    gen_id serial not null,
    gen_nombre varchar(100) not null
);
/*==============================================================*/
/* table: compania                                             	*/
/*==============================================================*/
create table compania
(
    com_id serial not null,
    com_nombre varchar(100) not null,
    com_majos varchar(2) not null,
    com_nacionalidad varchar(100) not null,
    com_streaming varchar(2) not null
);
/*==============================================================*/
/* table: favorita                                             	*/
/*==============================================================*/
create table favorita
(
    fav_id serial not null,
    fav_calificacion varchar(1) not null,
    fav_comentario varchar(10000) not null,
	
	ps_id int null
);
/*==============================================================*/
/* table: persona                                             	*/
/*==============================================================*/
create table persona
(
    per_id serial not null,
    per_nombre varchar(100) not null,
    per_genero varchar(1) not null,
    per_fecha_nacimiento date null
);
/*==============================================================*/
/* table: genero_pelicula_serie                                 */
/*==============================================================*/
create table genero_pelicula_serie
(
    gps_id serial not null,
    ps_id int not null,
    gen_id int not null
);
/*==============================================================*/
/* table: compania_pelicula_serie                               */
/*==============================================================*/
create table compania_pelicula_serie
(
    cps_id serial not null,
    com_id int not null,
    ps_id int not null
);
/*==============================================================*/
/* table: persona_pelicula_serie_screenwriter                   */
/*==============================================================*/
create table persona_pelicula_serie_screenwriter
(
    ppssw_id serial not null,
    per_id int not null,
    ps_id int not null
);
/*===============================================================*/
/* table: persona_pelicula_serie_productor                    	 */
/*===============================================================*/
create table persona_pelicula_serie_productor
(
    ppsp_id serial not null,
    per_id int not null,
    ps_id int not null,
	ppsp_tipo varchar(23) not null
);
/*==============================================================*/
/* table: persona_pelicula_serie_director                       */
/*==============================================================*/
create table persona_pelicula_serie_director
(
    ppsd_id serial not null,
    per_id int not null,
    ps_id int not null,
    ppsd_tipo varchar(100) null
);
/*==============================================================*/
/* table: persona_pelicula_serie_actor                          */
/*==============================================================*/
create table persona_pelicula_serie_actor
(
    ppsa_id serial not null,
    per_id int not null,
    ps_id int not null,
    ppsa_tipo varchar(100) not null,
    ppsa_tiempo_pantalla varchar(15) null
);
---------------------------------------------------------------------------------------------------------------------------------
-- primery key
alter table pelicula_serie add constraint pk_pelicula_serie primary key (ps_id);
alter table genero add constraint pk_genero primary key (gen_id);
alter table compania add constraint pk_compania primary key (com_id);
alter table favorita add constraint pk_favorita primary key (fav_id);
alter table persona add constraint pk_persona primary key (per_id);
alter table genero_pelicula_serie add constraint pk_genero_pelicula_serie primary key (gps_id);
alter table compania_pelicula_serie add constraint pk_compania_pelicula_serie primary key (cps_id);
alter table persona_pelicula_serie_screenwriter add constraint pk_persona_pelicula_serie_screenwriter primary key (ppssw_id);
alter table persona_pelicula_serie_productor add constraint pk_persona_pelicula_serie_productor primary key (ppsp_id);
alter table persona_pelicula_serie_director add constraint pk_persona_pelicula_serie_director primary key (ppsd_id);
alter table persona_pelicula_serie_actor add constraint pk_persona_pelicula_serie_actor primary key (ppsa_id);
----------------------------------------------------------------------------------------------------------------------------------
-- foreign key
alter table favorita
   add constraint fk_favorita_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
-----------------------------------------------------------------------------------------------------------
alter table genero_pelicula_serie
   add constraint fk_genero_pelicula_serie_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
alter table genero_pelicula_serie
   add constraint fk_genero_pelicula_serie_relations_genero foreign key (gen_id)
      references genero (gen_id);
-----------------------------------------------------------------------------------------------------------
alter table compania_pelicula_serie
   add constraint fk_compania_pelicula_serie_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
alter table compania_pelicula_serie
   add constraint fk_compania_pelicula_serie_relations_compania foreign key (com_id)
      references compania (com_id);
-----------------------------------------------------------------------------------------------------------
alter table persona_pelicula_serie_screenwriter
   add constraint fk_persona_pelicula_serie_screenwriter_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
alter table persona_pelicula_serie_screenwriter
   add constraint fk_persona_pelicula_serie_screenwriter_relations_persona foreign key (per_id)
      references persona (per_id);
-----------------------------------------------------------------------------------------------------------
alter table persona_pelicula_serie_productor
   add constraint fk_persona_pelicula_serie_productor_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
alter table persona_pelicula_serie_productor
   add constraint fk_persona_pelicula_serie_productor_relations_persona foreign key (per_id)
      references persona (per_id);
-----------------------------------------------------------------------------------------------------------
alter table persona_pelicula_serie_director
   add constraint fk_persona_pelicula_serie_director_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
alter table persona_pelicula_serie_director
   add constraint fk_persona_pelicula_serie_director_relations_persona foreign key (per_id)
      references persona (per_id);
-----------------------------------------------------------------------------------------------------------
alter table persona_pelicula_serie_actor
   add constraint fk_persona_pelicula_serie_actor_relations_pelicula_serie foreign key (ps_id)
      references pelicula_serie (ps_id);
alter table persona_pelicula_serie_actor
   add constraint fk_persona_pelicula_serie_actor_relations_persona foreign key (per_id)
      references persona (per_id);
/*---------------*---------------*---------------*---------------*---------------*---------------*---------------*---------------*---------------*/
-- check
alter table compania add constraint ck_com_majos check(com_majos in ('Si','No'));
alter table compania add constraint ck_com_streaming check(com_streaming in ('Si','No'));
alter table persona add constraint ck_per_genero check(per_genero in ('F','M'));
alter table persona_pelicula_serie_productor add constraint ck_ppsp_tipo check(ppsp_tipo in ('Productor Ejecutivo','Coproductor','Productor Asociado','Productor en Línea','Gerente de Producción','Asistente de Producción'));
alter table persona_pelicula_serie_director add constraint ck_ppsd_tipo check(ppsd_tipo in ('Director Principal','Director Fotografia','Director de Libretos','Director de Compositor','Director de Montaje'));
alter table persona_pelicula_serie_actor add constraint ck_ppsa_tipo check(ppsa_tipo in ('Protagonista','Secundario','De Reparto','De Pequeñas Partes'));
/*---------------*---------------*---------------*---------------*---------------*---------------*---------------*---------------*---------------*/
-- Datos basicos de la base de datos.
INSERT INTO genero (gen_nombre)VALUES('Clásicos');
INSERT INTO genero (gen_nombre)VALUES('Mudas');
INSERT INTO genero (gen_nombre)VALUES('Sonoras');
INSERT INTO genero (gen_nombre)VALUES('Blanco y negro');
INSERT INTO genero (gen_nombre)VALUES('Color');
INSERT INTO genero (gen_nombre)VALUES('Acción');
INSERT INTO genero (gen_nombre)VALUES('Aventuras');
INSERT INTO genero (gen_nombre)VALUES('Comedias');
INSERT INTO genero (gen_nombre)VALUES('Dramáticas');
INSERT INTO genero (gen_nombre)VALUES('Terror');
INSERT INTO genero (gen_nombre)VALUES('Musicales');
INSERT INTO genero (gen_nombre)VALUES('Ciencia ficción');
INSERT INTO genero (gen_nombre)VALUES('Guerra o bélicas');
INSERT INTO genero (gen_nombre)VALUES('Oeste (Wester)');
INSERT INTO genero (gen_nombre)VALUES('Crimen (Suspense)');
INSERT INTO genero (gen_nombre)VALUES('Infantiles');
INSERT INTO genero (gen_nombre)VALUES('Animacion');
INSERT INTO genero (gen_nombre)VALUES('Adultos');
INSERT INTO genero (gen_nombre)VALUES('Horror');
INSERT INTO genero (gen_nombre)VALUES('Anime');