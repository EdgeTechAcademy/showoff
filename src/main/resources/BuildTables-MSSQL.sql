create database showoff;
use showoff;
DROP TABLE IF EXISTS auth_user_role;
DROP TABLE IF EXISTS auth_role;
DROP TABLE IF EXISTS auth_user;
CREATE TABLE auth_role (
  auth_role_id int NOT NULL identity,
  role_name varchar(255) DEFAULT NULL,
  role_desc varchar(255) DEFAULT NULL,
  PRIMARY KEY (auth_role_id)
);
INSERT INTO auth_role VALUES ('SUPER_USER','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES ('ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES ('SITE_USER','This user has access to site, after login - normal user');

CREATE TABLE auth_user (
  auth_user_id int NOT NULL identity,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  status varchar(255),
  PRIMARY KEY (auth_user_id)
);

CREATE TABLE auth_user_role (
  auth_user_id int NOT NULL,
  auth_role_id int NOT NULL,
  PRIMARY KEY (auth_user_id,auth_role_id),
  CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
  CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id)
) ;

insert into auth_user (first_name,last_name,email,password,status) values ('Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','3');