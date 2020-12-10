package com.jachs.jpa.entity.otn;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author zhanchaohan
 * 一对多的一
 */
@Data
@Entity
@Table(name="Computer")
public class Computer {
    @Id
    private String ComputerId;
    
    @Column(name = "ComputerName")
    private String ComputerName;
    @Column(name = "ComputerPrice")
    private Long ComputerPrice;
    
    @OneToMany(targetEntity = SoftWare.class,mappedBy = "CId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<SoftWare> SoftWares;
}
