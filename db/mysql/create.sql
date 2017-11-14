drop database if exists bjee;
create database bjee;
create user bjeeus@localhost identified by 'password';
grant all privileges on bjee.* to bjeeus@localhost;
flush privileges;

use bjee;

set autocommit = 0;
set foreign_key_checks = 0;

drop table if exists groups;

create table groups (
  id int(11) not null AUTO_INCREMENT,
  name varchar(64) collate utf8mb4_unicode_ci not null,   
  description varchar(254) collate utf8mb4_unicode_ci default null,   
  primary key (id),
  unique key name_unique (name) using BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 default CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;

insert into groups values (1, 'admin','admin');
insert into groups values (2, 'user','user');

drop table if exists users;
create table users (
  id int(11) not null AUTO_INCREMENT,
  username varchar(64) collate utf8mb4_unicode_ci not null,
  password varchar(254) collate utf8mb4_unicode_ci not null,
  firstname varchar(64) collate utf8mb4_unicode_ci default null,
  lastname varchar(64) collate utf8mb4_unicode_ci default null,
  mail varchar(45) collate utf8mb4_unicode_ci default null,
  created datetime default null,
  updated datetime default null,
  last_login datetime default null,
  last_logout datetime default null,
  enabled tinyint(1) not null default false,
  group_id int(11) not null,
  primary key (id),
  unique key username_unique (username),
  key fk_users_groups (group_id),
  constraint fk_users_groups foreign key (group_id) references groups(id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;

insert into users values (1, 'admin', SHA2("password", 512), 'portal', 'admin', 'admin@portal.ext', now(), null, null, null, 1, 1);
insert into users values (2, 'guest', SHA2("password", 512), 'portal', 'guest', 'guest@portal.ext', now(), null, null, null, 1, 2);

drop view if exists users_access;

create view users_access as 
select 
u.username as username,
u.password as password,
g.name as group_name 
from 
users u join groups g on u.id = g.id 
where 
u.enabled = 1 ;

set foreign_key_checks = 1;
commit;
set autocommit = 1;
