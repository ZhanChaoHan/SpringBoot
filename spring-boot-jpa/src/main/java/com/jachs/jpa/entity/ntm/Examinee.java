package com.jachs.jpa.entity.ntm;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="Examinee")
public class Examinee {
    @Id
    private Integer ExamineeId;//考生ID
    @Column(name = "ExamineeName")
    private String ExamineeName;//考生名字
    
    @ManyToMany
    @JoinTable
    private Set<Exam>Exams;
}
