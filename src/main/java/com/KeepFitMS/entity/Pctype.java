package com.KeepFitMS.entity;
/**
 * 商品子类型
 * @author suyin
 *
 */
public class Pctype {
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
