package com.jachs.jpa.entity.otn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
@Entity
@Table(name="SoftWare")
public class SoftWare {
    @Id
    private String SoftWareId;
    
    @Column(name = "SoftWareName")
    private String SoftWareName;
    
    @Column(name = "ComputersId")
    private String ComputersId;
    
    @ManyToOne
    @JoinColumn(name = "ComputersId",insertable = false,updatable = false,referencedColumnName = "ComputerId")
    private Computer Computer;
}
