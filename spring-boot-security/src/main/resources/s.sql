use security;

CREATE TABLE `roleuser` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `Name` varchar(50)  DEFAULT NULL ,
  `Phone` bigint DEFAULT NULL,
  `Enabled` varchar(1) DEFAULT NULL COMMENT '0 - 无效\n    1 - 有效',
  `Username` varchar(50),
  `Password` varchar(50) DEFAULT NULL,
  `AccountNonExpired` varchar(1) DEFAULT NULL COMMENT '0 - 无效\n    1 - 有效',
  `AccountNonLocked` varchar(1) DEFAULT NULL COMMENT '0 - 无效\n    1 - 有效',
  `CredentialsNonExpired` varchar(1) DEFAULT NULL COMMENT '0 - 无效\n    1 - 有效',
  PRIMARY KEY (`Id`),
  UNIQUE KEY (`Username`)
) ENGINE=InnoDB;

insert into roleuser values(null,'zhanchaohan','13236365841','0','Jachs','123','0','0','0');

CREATE TABLE `SecurityUser` (
  `Username` varchar(50),
  `Code` int,
  `Authority` varchar(50),
  PRIMARY KEY (`Code`,`Authority`),
  FOREIGN KEY (Username) REFERENCES roleuser (Username)
) ENGINE=InnoDB;

insert into SecurityUser values('Jachs',1,'ROLE_Jachs');
