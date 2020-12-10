package com.jachs.jpa.entity.otn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author zhanchaohan
 * 一对多的多
 */
@Data
@Entity
@Table(name="SoftWare")
public class SoftWare {
    @Id
    private String SoftWareId;
    
    @Column(name = "CId")
    private String CId;
    @Column(name = "SoftWareName")
    private String SoftWareName;
    
}
