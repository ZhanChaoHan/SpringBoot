package com.jachs.jpa.entity.oto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * OneToOne单项关联一个学生一个卡
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="Student")
public class Student {
    @Id
    private String StudentId;
    @Column(name = "StudentName")
    private String StudentName;
    @Column(name = "StudentAge")
    private int StudentAge;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sCart")
    private StudentCart StudentCart;
}
