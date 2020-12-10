package com.jachs.jpa.entity.ntn;

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
@Table(name="Bus")
public class Bus {
    @Id
    private String CarNo;
    
    
}
