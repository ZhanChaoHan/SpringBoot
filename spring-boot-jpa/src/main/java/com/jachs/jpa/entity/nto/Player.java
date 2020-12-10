package com.jachs.jpa.entity.nto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="Player")
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long PlayerId;
    
    @Column(name = "PlayerUserName")
    private String PlayerUserName;
    @Column(name = "Level")
    private Integer Level;
}
