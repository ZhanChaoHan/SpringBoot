package com.jachs.jpa.entity.oto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="StudentCard")
public class StudentCard {
    @Id
    private String CardId;
    @Column(name = "CardType")
    private String CardType;
    @Column(name = "CardMonery")
    private Long CardMonery;
    
}
