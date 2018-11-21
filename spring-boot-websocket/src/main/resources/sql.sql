DROP TABLE game;
CREATE TABLE game ( ids varchar(20) NOT NULL, p1 varchar(20), p2 varchar(20), startTime timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, endTime timestamp DEFAULT CURRENT_TIMESTAMP, winner varchar(20), PRIMARY KEY (ids) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
DROP TABLE play;
CREATE TABLE play ( ids varchar(20) NOT NULL, gameId varchar(20), playUser varchar(20), timeConsuming varchar(20), statusType varchar(20), Mess varchar(50), PRIMARY KEY (ids), INDEX play_fk1 (gameId) ) ENGINE=InnoDB DEFAULT CHARSET=gbk;
