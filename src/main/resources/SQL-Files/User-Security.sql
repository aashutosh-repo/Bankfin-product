  -- User
  CREATE TABLE CHANNEL_USERS
   (	EMPID int NOT NULL, 
	USERID VARCHAR(25 ) NOT NULL , 
	PASSWORD VARCHAR(100 ), 
	STATUS int, 
	 PRIMARY KEY (USERID));
insert into channel_users (EMPID,USERID,PASSWORD,STATUS) values(18625,'raj','{bcrypt}$2a$10$.v.FEaZh84twhNJFnUI7SeEsygl9KPVFYzLR2ToeqbZPJng5ko5LK' ,1);
insert into channel_users (EMPID,USERID,PASSWORD,STATUS) values(18626,'pawan','{bcrypt}$2a$10$.v.FEaZh84twhNJFnUI7SeEsygl9KPVFYzLR2ToeqbZPJng5ko5LK' ,1);
insert into channel_users (EMPID,USERID,PASSWORD,STATUS) values(18627,'amar','{bcrypt}$2a$10$.v.FEaZh84twhNJFnUI7SeEsygl9KPVFYzLR2ToeqbZPJng5ko5LK' ,1);
insert into channel_users (EMPID,USERID,PASSWORD,STATUS) values(18629,'aashu','{bcrypt}$2a$10$.v.FEaZh84twhNJFnUI7SeEsygl9KPVFYzLR2ToeqbZPJng5ko5LK' ,1);
-- USER_ROLE table for assiging the Role  password- bank123.
CREATE TABLE USER_ROLE 
   (	USERID VARCHAR(25) NOT NULL, 
	USER_ROLE VARCHAR(25) NOT NULL, 
	 PRIMARY KEY (USERID, USER_ROLE));
     truncate table USER_ROLE;
insert into user_role (USERID,user_role) value
('raj','ROLE_EMPLOYEE'),
('pawan','ROLE_MANAGER'),
('amar','ROLE_MANAGER'),
('aashu','ROLE_ADMIN');