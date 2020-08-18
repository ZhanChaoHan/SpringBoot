DROP TABLE roleuser;
CREATE TABLE roleuser ( Id bigint NOT NULL AUTO_INCREMENT, Name varchar(50), Phone bigint, Enabled varchar(1) COMMENT '0 - 无效
    1 - 有效', Username varchar(50), Password varchar(50), AccountNonExpired varchar(1) COMMENT '0 - 无效
    1 - 有效', AccountNonLocked varchar(1) COMMENT '0 - 无效
    1 - 有效', CredentialsNonExpired varchar(1) COMMENT '0 - 无效
    1 - 有效', PRIMARY KEY (Id), CONSTRAINT Username UNIQUE (Username) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (1, 'zhanchaohan', 13236365841, '0', 'Jachs', '123', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (2, '用户一', 12365412541, '0', 'UserA', '123456', '0', '0', '0');
insert into roleuser (Id, Name, Phone, Enabled, Username, Password, AccountNonExpired, AccountNonLocked, CredentialsNonExpired) values (3, '用户二', 25415263542, '0', 'UserB', '123456', '0', '0', '0');
DROP TABLE securityuser;
CREATE TABLE securityuser ( Username varchar(50), Code int DEFAULT '0' NOT NULL, Authority varchar(50) NOT NULL, PRIMARY KEY (Code, Authority), INDEX Username (Username) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
insert into securityuser (Username, Code, Authority) values ('Jachs', 1, 'ROLE_Jachs');
ALTER TABLE securityuser ADD FOREIGN KEY (Username) REFERENCES roleuser (Username);