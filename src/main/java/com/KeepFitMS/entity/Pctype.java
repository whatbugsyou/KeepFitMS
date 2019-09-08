package com.KeepFitMS.entity;

import java.io.Serializable;

/**
 * 商品子类型
 * @author suyin
 *
 */
public class Pctype implements Serializable{
	private static final long serialVersionUID = 3625758683745220023L;
		private Integer pctype_id;//子类型id
		private String pctype_name;//子类型名
		private Ptype ptype;//所属的父类型
		public Integer getPctype_id() {
			return pctype_id;
		}
		public void setPctype_id(Integer pctype_id) {
			this.pctype_id = pctype_id;
		}
		public String getPctype_name() {
			return pctype_name;
		}
		public void setPctype_name(String pctype_name) {
			this.pctype_name = pctype_name;
		}
		public Ptype getPtype() {
			return ptype;
		}
		public void setPtype(Ptype ptype) {
			this.ptype = ptype;
		}
		@Override
		public String toString() {
			return "Pctype [pctype_id=" + pctype_id + ", pctype_name=" + pctype_name + ", ptype=" + ptype + "]";
		}
	
		
		
		
}
