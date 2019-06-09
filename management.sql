/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/8 12:15:23                            */
/*==============================================================*/


drop table if exists permission;

drop table if exists role;

drop table if exists role_permission;

drop table if exists user;

drop table if exists user_role;

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   int not null auto_increment,
   name                 varchar(64) not null,
   url                  varchar(64) not null,
   parentid             int,
   sort                 int,
   type                 int(1) not null default 1 comment '0-menu,1-button',
   available            int(1) default 0 comment '0-可用,1-不可用',
   primary key (id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null auto_increment,
   name                 varchar(64) not null,
   available            int(1) default 0 comment '0-可用,1-不可用',
   primary key (id)
);

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   id                   int not null,
   role_id              int not null,
   permission_id        int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment,
   phone                varchar(11),
   username             varchar(32) not null,
   password             varchar(128) not null,
   locked               int(1) default 0 comment '0-未锁定,1-锁定',
   status               int(1) default 0 comment '0-正常,1-暂停',
   primary key (id),
   unique key AK_username (username)
);

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id                   int not null auto_increment,
   user_id              int not null,
   role_id              int not null,
   primary key (id)
);

