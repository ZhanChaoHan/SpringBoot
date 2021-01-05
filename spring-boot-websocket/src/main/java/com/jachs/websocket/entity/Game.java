package com.jachs.websocket.entity;

import java.io.Serializable;
import java.util.Date;

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
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
    private String ids;

    private String p1;

    private String p2;

    private Date starttime;

    private Date endtime;

    private String winner;


}