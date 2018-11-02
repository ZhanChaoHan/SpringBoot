DROP TABLE operatorlogs;
CREATE TABLE operatorlogs ( ID varchar(32) NOT NULL, USERCODE varchar(20) NOT NULL, OPERATIONDATE datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, ROLECODE varchar(20) NOT NULL, ROLENAME varchar(100) NOT NULL, AREACODE varchar(10) NOT NULL, COMPANYCODE varchar(20) NOT NULL, FINDCONTENT varchar(1000), DATAID varchar(32), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (ID) ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE relationship;
CREATE TABLE relationship ( id varchar(50) NOT NULL, ComCode varchar(500) NOT NULL, UserCode varchar(500) NOT NULL, ValidStatus varchar(2), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysarea;
CREATE TABLE sysarea ( AreaCode varchar(50) NOT NULL, SupAreaCode varchar(50) NOT NULL, HasSubArea varchar(2) NOT NULL COMMENT '00 - 无
            01 - 有', AreaCname varchar(300) NOT NULL, ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime date DEFAULT '0000-00-00' NOT NULL, CreateUserCode varchar(50), ModifyTime date DEFAULT '0000-00-00' NOT NULL, ModifyUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (AreaCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE syscode;
CREATE TABLE syscode ( CodeType varchar(50) NOT NULL, CodeCode varchar(50) NOT NULL, CodeCname varchar(300) NOT NULL, ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), ModifyTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, ModifyUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (CodeType, CodeCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE syscompany;
CREATE TABLE syscompany ( ComCode varchar(50) NOT NULL, AreaCode varchar(50) NOT NULL, ComLevel varchar(2) NOT NULL COMMENT '1 - 省级
            2 - 市级
            3 - 县级', ComType varchar(2) NOT NULL COMMENT '01 - 保监局
            02 - 保险协会
            03 - 保险公司
            04 - 中介代理', SupComCode varchar(50) NOT NULL, HasSubCom varchar(2) NOT NULL COMMENT '00 - 无
            01 - 有', ComCname varchar(300) NOT NULL, ComAddress varchar(300), ComPhone varchar(50), ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), ModifyTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, ModifyUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (ComCode), INDEX FK_COM_AREA (AreaCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysconfig;
CREATE TABLE sysconfig ( ConfigName varchar(50) NOT NULL, ConfigValue varchar(500) NOT NULL, ConfigExp varchar(500) NOT NULL, ConfigDes varchar(1000) NOT NULL, ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), ModifyTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, ModifyUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (ConfigName) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysgroup;
CREATE TABLE sysgroup ( GroupCode varchar(50) NOT NULL, ComLevel varchar(2) NOT NULL COMMENT '1 - 省级
            2 - 市级
            3 - 县级
            4 - 乡镇', ComType varchar(2) NOT NULL COMMENT '01 - 保监局
            02 - 保险协会
            03 - 保险公司
            04 - 中介代理', GroupName varchar(300) NOT NULL, ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), ModifyTime datetime DEFAULT '0000-00-00 00:00:00', ModifyUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (GroupCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysgrouprole;
CREATE TABLE sysgrouprole ( GroupCode varchar(50) NOT NULL, RoleCode varchar(50) NOT NULL, ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateUserCode varchar(50), CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (GroupCode, RoleCode), INDEX FK_GROUPROLE_ROLE (RoleCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysrole;
CREATE TABLE sysrole ( RoleCode varchar(50) NOT NULL, UpperRoleCode varchar(50) NOT NULL, HasSubRole varchar(2) NOT NULL COMMENT '00 - 无
            01 - 有', RoleName varchar(300) NOT NULL, RoleType varchar(2) NOT NULL COMMENT '01 - 功能权限
            02 - 数据权限', ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), ModifyTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, ModifyUserCode varchar(50), Url varchar(200), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (RoleCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysuser;
CREATE TABLE sysuser ( UserCode varchar(50) NOT NULL, ComCode varchar(50) NOT NULL, UserName varchar(300) NOT NULL, PassWord varchar(100) NOT NULL, TelePhone varchar(50), Email varchar(300), ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), ModifyTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, ModifyUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (UserCode), INDEX FK_USER_COM (ComCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE sysusergroup;
CREATE TABLE sysusergroup ( UserCode varchar(50) NOT NULL, GroupCode varchar(50) NOT NULL, ValidStatus varchar(2) COMMENT '00 - 无效
            01 - 有效', CreateTime datetime DEFAULT '0000-00-00 00:00:00' NOT NULL, CreateUserCode varchar(50), Reverse1 varchar(10), Reverse2 varchar(10), Reverse3 varchar(10), PRIMARY KEY (UserCode, GroupCode), INDEX FK_USERGROUP_GROUP (GroupCode) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
