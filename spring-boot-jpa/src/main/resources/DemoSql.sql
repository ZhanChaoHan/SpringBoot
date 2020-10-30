create table MyClass(
    ClassId INT(10) NOT NULL AUTO_INCREMENT ,PRIMARY KEY (ClassId),
    ClassType INT(10) NOT NULL COMMENT '类型',
    ClassName VARCHAR(128) NOT NULL COMMENT '名字'
)

create table MyStudent(
    StudentId INT(10) NOT NULL AUTO_INCREMENT ,PRIMARY KEY (StudentId),
    ClassId INT(10) NOT NULL COMMENT '班级ID',
    StudentAge INT(10) NOT NULL COMMENT '年龄',
    StudentGender INT NOT NULL COMMENT '性别',
    StudentName VARCHAR(128) NOT NULL COMMENT '名字'
)

create table MySubject(
    SubjectId INT(10) NOT NULL AUTO_INCREMENT ,PRIMARY KEY (SubjectId),
    SubjectName VARCHAR(128) NOT NULL COMMENT '课程名字',
    SubjectScore INT NOT NULL COMMENT '分数'
)
create table Example(
   Id INT(10) NOT NULL AUTO_INCREMENT ,PRIMARY KEY (Id),
   ClassId INT(10) NOT NULL ,
   StudentId INT(10) NOT NULL ,
   SubjectId INT(10) NOT NULL 

)

drop table MyClass;
drop table MyStudent;
drop table MySubject;
drop table Example;

select * from my_class;
select * from my_student;


insert into my_student(student_age,student_gender,student_name,class_id) values(2,0,"张三",1)