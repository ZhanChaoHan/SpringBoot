package com.jachs.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
@Entity
/**
 *myclass非驼峰命名表名称就是myclass
 *MyClass驼峰命名表名会变成my_class
 */
@Table(name="MyClass")
public class MyClass {
    /*
        @GeneratedValue注解的strategy属性提供四种值：
        –AUTO： 主键由程序控制，是默认选项，不设置即此项。
        –IDENTITY：主键由数据库自动生成，即采用数据库ID自增长的方式，Oracle不支持这种方式。
        –SEQUENCE：通过数据库的序列产生主键，通过@SequenceGenerator 注解指定序列名，mysql不支持这种方式。
        –TABLE：通过特定的数据库表产生主键，使用该策略可以使应用更易于数据库移植。
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ClassId")
    private int ClassId;
    @Column(name = "ClassType", nullable = true, length = 2)
    private int ClassType;
    @Column(name = "ClassName", nullable = true, length = 20)
    private String ClassName;
    
//    @OneToMany(mappedBy="class")
//    private List<MyStudent> MyStudent=new ArrayList<MyStudent>();
    


}
