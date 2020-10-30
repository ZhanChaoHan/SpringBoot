package com.jachs.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 学生表
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
     * 级联关系类型：
		CascadeType.REFRESH：级联刷新，当多个用户同时作操作一个实体，为了用户取到的数据是实时的，在用实体中的数据之前就可以调用一下refresh()方法
		CascadeType.REMOVE：级联删除，当调用remove()方法删除Order实体时会先级联删除OrderItem的相关数据
		CascadeType.MERGE：级联更新，当调用了Merge()方法，如果Order中的数据改变了会相应的更新OrderItem中的数据
		CascadeType.ALL：包含以上所有级联属性
		CascadeType.PERSIST：级联保存，当调用了Persist() 方法，会级联保存相应的数据
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
