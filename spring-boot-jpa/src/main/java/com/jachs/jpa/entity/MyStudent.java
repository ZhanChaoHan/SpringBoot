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

import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = MyClass.class,optional=true)
    @JoinColumn(name = "classid")
    private MyClass MyClass;
    
    
    @Column(name = "StudentAge", nullable = true, length = 3)
    private int StudentAge;
    @Column(name = "StudentGender", nullable = true, length = 1)
    private int StudentGender;
    @Column(name = "StudentName", nullable = true, length = 5)
    private String StudentName;
    

    
}
