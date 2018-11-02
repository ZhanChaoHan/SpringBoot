package com.sinosoft.normal.po;

import java.io.Serializable;

public class RelationShip implements Serializable {
		private String id;
		private String comCode;
		private String userCode;
	    private String validStatus;
	    private String reverse1;
	    private String reverse2;
	    private String reverse3;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getComCode() {
			return comCode;
		}
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}
		public String getUserCode() {
			return userCode;
		}
		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}
		public String getValidStatus() {
			return validStatus;
		}
		public void setValidStatus(String validStatus) {
			this.validStatus = validStatus;
		}
		public String getReverse1() {
			return reverse1;
		}
		public void setReverse1(String reverse1) {
			this.reverse1 = reverse1;
		}
		public String getReverse2() {
			return reverse2;
		}
		public void setReverse2(String reverse2) {
			this.reverse2 = reverse2;
		}
		public String getReverse3() {
			return reverse3;
		}
		public void setReverse3(String reverse3) {
			this.reverse3 = reverse3;
		}
	    
}
