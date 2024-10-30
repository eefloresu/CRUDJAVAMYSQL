create database bdusuarios;
use bdusuarios;

create table sexo(
id int auto_increment not null primary key,
sexo varchar(50)
);

insert into sexo (sexo) values ("Masculino");
insert into sexo (sexo) values ("Femenino");

select * from sexo;

create table usuarios(
id int auto_increment not null primary key,
nombres varchar(100),
apellidos varchar(100),
fksexo int,
edad int,
fnacimiento date,
foto longblob,
FOREIGN KEY (fksexo) REFERENCES sexo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into usuarios (nombres, apellidos, fksexo, edad, fnacimiento, foto) Values ("Juan","Perez",1,21,"01/01/2003","foto");

select * from usuarios;

-- Mostrar limite de conexiones
show variables like 'max_connections';
-- Mostrar conexiones abiertas
show status like 'threads_connected';


SELECT usuarios.id,usuarios.nombres,usuarios.apellidos,sexo.sexo,usuarios.edad,usuarios.fnacimiento,usuarios.foto
FROM usuarios INNER JOIN sexo ON usuarios.fksexo = sexo.id;
