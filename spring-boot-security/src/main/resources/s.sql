DROP TABLE roleuser;
CREATE TABLE roleuser ( Id bigint NOT NULL AUTO_INCREMENT, Name varchar(50), Phone bigint, Enabled varchar(1) COMMENT '0 - 无效
    1 - 有效', Username varchar(50), Password varchar(50), AccountNonExpired varchar(1) COMMENT '0 - 无效
    1 - 有效', AccountNonLocked varchar(1) COMMENT '0 - 无效
    1 - 有效', CredentialsNonExpired varchar(1) COMMENT '0 - 无效
    1 - 有效', PRIMARY KEY (Id), CONSTRAINT Username UNIQUE (Username), CONSTRAINT Name UNIQUE (Name) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (1, 'zhanchaohan', 13236365841, '1', 'Jachs', '123', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (2, '用户一', 12365412541, '1', 'UserA', '123456', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (3, '用户二', 25415263542, '1', 'UserB', '123456', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (4, 'PostA用户', 13454323456, '1', 'PostA', '123456', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (5, 'PostB用户', 23456789766, '1', 'PostB', '123456', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (6, 'GetA用户', 13454323456, '1', 'GetA', '123456', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (7, 'GetB用户', 23456789766, '1', 'GetB', '123456', '1', '1', '1');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (11, '李四', 13432568654, '1', 'lisi', '123456', '1', '1', '1');
DROP TABLE securityuser;
CREATE TABLE securityuser ( Username varchar(50), Code int DEFAULT '0' NOT NULL, Authority varchar(50) NOT NULL, PRIMARY KEY (Code, Authority), INDEX Username (Username) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
insert into securityuser (Username, Code, Authority) values ('GetA', 3, 'GetA');
insert into securityuser (Username, Code, Authority) values ('GetB', 2, 'GetB');
insert into securityuser (Username, Code, Authority) values ('Jachs', 1, 'Jachs');
insert into securityuser (Username, Code, Authority) values ('PostA', 3, 'ROLE_GetA');
insert into securityuser (Username, Code, Authority) values ('PostA', 4, 'PostA');
insert into securityuser (Username, Code, Authority) values ('PostB', 5, 'PostB');
insert into securityuser (Username, Code, Authority) values ('UserA', 6, 'UserA');
insert into securityuser (Username, Code, Authority) values ('UserB', 7, 'UserB');
ALTER TABLE securityuser ADD CONSTRAINT securityuser_fk1 FOREIGN KEY (Username) REFERENCES roleuser (Username) ON DELETE CASCADE ON UPDATE CASCADE;
