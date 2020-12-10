package com.jachs.jpa.entity.nto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="VideoGame")
public class VideoGame {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long GameId;
    
    @Column(name = "GameName")
    private String GameName;
    @Column(name = "GamePrice")
    private Integer GamePrice;
    
    @ManyToOne(targetEntity = Player.class,optional = true)
    private Player Player;
}
