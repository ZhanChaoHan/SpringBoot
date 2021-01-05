package com.jachs.websocket.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/***
 * 
 * @author zhanchaohan
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Play implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String ids;

    private String gameid;

    private String playuser;

    private String timeconsuming;

    private Status statustype;

    private String mess;

}