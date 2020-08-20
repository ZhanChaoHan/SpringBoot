
DROP TABLE roleuser;
CREATE TABLE roleuser ( Id bigint NOT NULL AUTO_INCREMENT, Name varchar(50), Phone bigint, Enabled varchar(1) COMMENT '0 - ��Ч
    1 - ��Ч', Username varchar(50), Password varchar(50), AccountNonExpired varchar(1) COMMENT '0 - ��Ч
    1 - ��Ч', AccountNonLocked varchar(1) COMMENT '0 - ��Ч
    1 - ��Ч', CredentialsNonExpired varchar(1) COMMENT '0 - ��Ч
    1 - ��Ч', PRIMARY KEY (Id), CONSTRAINT Username UNIQUE (Username) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (1, 'zhanchaohan', 13236365841, '0', 'Jachs', '123', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (2, '�û�һ', 12365412541, '0', 'UserA', '123456', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (3, '�û���', 25415263542, '0', 'UserB', '123456', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (4, 'Post�û�', 13454323456, '0', 'PostA', '123456', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (5, 'Post�û�', 23456789766, '0', 'PostB', '123456', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (6, 'Get�û�', 13454323456, '0', 'GetA', '123456', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (7, 'Get�û�', 23456789766, '0', 'GetB', '123456', '0', '0', '0');
DROP TABLE securityuser;
CREATE TABLE securityuser ( Username varchar(50), Code int DEFAULT '0' NOT NULL, Authority varchar(50) NOT NULL, PRIMARY KEY (Code, Authority), INDEX Username (Username) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
insert into securityuser (Username, Code, Authority) values ('GetA', 3, 'ROLE_Geta');
insert into securityuser (Username, Code, Authority) values ('GetB', 2, 'ROLE_Getb');
insert into securityuser (Username, Code, Authority) values ('Jachs', 1, 'ROLE_Jachs');
insert into securityuser (Username, Code, Authority) values ('PostA', 4, 'ROLE_Posta');
insert into securityuser (Username, Code, Authority) values ('PostB', 5, 'ROLE_Postb');
insert into securityuser (Username, Code, Authority) values ('UserA', 6, 'ROLE_Usera');
insert into securityuser (Username, Code, Authority) values ('UserB', 7, 'ROLE_Userb');
ALTER TABLE securityuser ADD FOREIGN KEY (Username) REFERENCES roleuser (Username);
