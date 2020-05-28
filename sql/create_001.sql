-- Выполнять под пользователем root

drop schema if exists art;
create schema art;
use art;

drop user if exists art@'localhost';
create user art@'localhost' identified by 'radmin';
grant all privileges on art.* to art@'localhost';


-- Дальше выполнять под пользователем art
use art;

create table s_user_remember_me (
	username varchar(64) not null comment 'Имя пользователя', 
	series varchar(64) primary key, 
	token varchar(64) not null, 
	last_used timestamp not null comment 'Последнее время использования'
);

create table s_role (
	id int auto_increment primary key comment 'Автоматический первичный ключ',
	name varchar(100) not null comment 'Имя роли'
);

insert into s_role (id, name) values (1,'ROLE_ADMIN');
insert into s_role (id, name) values (2,'ROLE_USER');
commit;

create table s_user (
	id int auto_increment primary key comment 'Автоматический первичный ключ',
	name varchar(100) not null comment 'Имя пользователя', 
	password varchar(100) not null comment 'Пароль пользователя (временный вариант, будет хеш)',
	email varchar(200) not null comment 'email'
);

insert into s_user (name, password, email) values ('admin','radmin','test@mailforspam.ru');
commit;

create table s_user_role (
	id int auto_increment primary key comment 'Автоматический первичный ключ',
	user_id int not null comment 'Пользователь, ссылка s_user.id',
	role_id int not null comment 'Роль, ссылка s_role.id',
	foreign key (user_id) references s_user (id),
	foreign key (role_id) references s_role (id)
);

insert into s_user_role (user_id, role_id) values (1,1);
commit;

create table firm (
	id int auto_increment primary key comment 'Автоматический первичный ключ',
	name varchar(100) not null comment 'Наименование пространства',
	owner_user_id int not null comment 'Владелец пространства (временный вариант)',
	foreign key (owner_user_id) references s_user (id)
);

insert into firm (name, owner_user_id) values ('admin',1);
commit;

create table acc (
	id int auto_increment primary key comment 'Автоматический первичный ключ', 
	firm_id int not null comment 'Пространство (ссылка firm.id)',
	name varchar(100) not null  comment 'Наименование счета', 
	sort_to int comment 'Сортировочный номер участия в операциях TO (дебет)', 
	sort_from int comment 'Сортировочный номер участия в операциях FROM (кредит)', 
	sort_status int comment 'Сортировочный номер участия в отчете Статус (состояние счета)', 
	sort_rep_in int comment 'Сортировочный номер участия в отчете Доходы', 
	sort_rep_out int comment 'Сортировочный номер участия в отчете Расходы',
	foreign key (firm_id) references firm (id)
);


/*
В планах:


CREATE TABLE  CS_LEDGER 
   (	ID NUMBER(10,0), 
	D DATE NOT NULL ENABLE, 
	ACC_TO_ID NUMBER(10,0) NOT NULL ENABLE, 
	ACC_FROM_ID NUMBER(10,0) NOT NULL ENABLE, 
	S NUMBER(15,2) NOT NULL ENABLE, 
	TXT VARCHAR(100), 
	 CONSTRAINT CS_LEDGER_PK PRIMARY KEY (ID)
  USING INDEX  ENABLE
   )
/
ALTER TABLE  CS_LEDGER ADD CONSTRAINT CS_LEDGER_FK1 FOREIGN KEY (ACC_TO_ID)
	  REFERENCES  CS_ACC (ID) ENABLE
/
ALTER TABLE  CS_LEDGER ADD CONSTRAINT CS_LEDGER_FK2 FOREIGN KEY (ACC_FROM_ID)
	  REFERENCES  CS_ACC (ID) ENABLE
/


CREATE TABLE  CS_REP 
   (	N NUMBER(10,0), 
	S0 VARCHAR(1000), 
	S1 VARCHAR(1000), 
	S2 VARCHAR(1000), 
	S3 VARCHAR(1000), 
	S4 VARCHAR(1000)
   )
/


CREATE TABLE  CS_STATUS_SUM 
   (	ID NUMBER(10,0), 
	NAME VARCHAR(200) NOT NULL ENABLE, 
	S NUMBER(10,0) NOT NULL ENABLE, 
	 CONSTRAINT CS_STATUS_SUM_PK PRIMARY KEY (ID)
  USING INDEX  ENABLE
   )
/

CREATE TABLE  CS_STATUS_SUMI 
   (	ID NUMBER(10,0), 
	SUM_ID NUMBER(10,0) NOT NULL ENABLE, 
	ACC_ID NUMBER(10,0) NOT NULL ENABLE, 
	 CONSTRAINT CS_STATUSI_SUM_PK PRIMARY KEY (ID)
  USING INDEX  ENABLE
   )
/
ALTER TABLE  CS_STATUS_SUMI ADD CONSTRAINT CS_STATUS_SUMI_FK1 FOREIGN KEY (SUM_ID)
	  REFERENCES  CS_STATUS_SUM (ID) ENABLE
/
ALTER TABLE  CS_STATUS_SUMI ADD CONSTRAINT CS_STATUS_SUMI_FK2 FOREIGN KEY (ACC_ID)
	  REFERENCES  CS_ACC (ID) ENABLE
/

*/

