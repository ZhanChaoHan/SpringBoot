package com.jachs.jpa.entity.ntn;

import java.util.Date;

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
@Table(name="Personal")
public class Personal {
    @Id
    private String IDNumber;
    
    @Column(name = "TravelTime")
    private Date TravelTime;
    @Column(name = "Fare")
    private Integer Fare;
    
}
