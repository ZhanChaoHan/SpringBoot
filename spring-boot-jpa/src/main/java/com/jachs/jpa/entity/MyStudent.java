package com.jachs.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author zhanchaohan
 * 
 */
@Entity
@Table(name="MyStudent")
public class MyStudent {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private int StudentId;
    /***
     * FetchType.LAZY:延迟获取数据
     * FetchType.EAGER:立即获取数据
     * 
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = MyClass.class)
    @JoinColumn(name = "classid", columnDefinition = "INT NOT NUL")
    private MyClass MyClass;
    
    
    @Column(name = "StudentAge", nullable = true, length = 3)
    private int StudentAge;
    @Column(name = "StudentGender", nullable = true, length = 1)
    private int StudentGender;
    @Column(name = "StudentName", nullable = true, length = 5)
    private String StudentName;
    
    public MyStudent () {
        super ();
    }

    public MyStudent ( int studentId, com.jachs.jpa.entity.MyClass myClass, int studentAge, int studentGender,
            String studentName ) {
        super ();
        StudentId = studentId;
        MyClass = myClass;
        StudentAge = studentAge;
        StudentGender = studentGender;
        StudentName = studentName;
    }

    public int getStudentId () {
        return StudentId;
    }

    public void setStudentId ( int studentId ) {
        StudentId = studentId;
    }

    public MyClass getMyClass () {
        return MyClass;
    }

    public void setMyClass ( MyClass myClass ) {
        MyClass = myClass;
    }

    public int getStudentAge () {
        return StudentAge;
    }

    public void setStudentAge ( int studentAge ) {
        StudentAge = studentAge;
    }

    public int getStudentGender () {
        return StudentGender;
    }

    public void setStudentGender ( int studentGender ) {
        StudentGender = studentGender;
    }

    public String getStudentName () {
        return StudentName;
    }

    public void setStudentName ( String studentName ) {
        StudentName = studentName;
    }

    
}
