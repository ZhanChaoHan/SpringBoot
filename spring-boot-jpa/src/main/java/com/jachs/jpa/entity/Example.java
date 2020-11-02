package com.jachs.jpa.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 得分表
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="Example")
public class Example {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	@OneToOne(optional = false)
	@JoinColumn(name="MyStudentId",referencedColumnName="StudentId")
    private MyStudent MyStudent;
	
	@OneToOne(optional = false)
	@JoinColumn(name="MySubjectId",referencedColumnName="SubjectId")
    private MySubject MySubject;
    
    
    @Column(name = "Score", nullable = true, length = 3)
    private int Score;
}
