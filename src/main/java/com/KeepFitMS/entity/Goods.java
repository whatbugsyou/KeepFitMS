package com.KeepFitMS.entity;
/**
 * 商品信息
 * @author suyin
 *
 */
public class Goods {
		private Integer goods_id; //商品id
		private String goods_name;//商品名称
		private String goods_desc;//商品描述
		private String goods_img;//商品图片，存放路径
		private Integer goods_price;//商品单价
		private Integer goods_num;//商品数量
		private boolean goods_status;//商品状态
		private Ptype ptype;//商品父类型
		private Pctype pctype;//商品子类型
		public Integer getGoods_id() {
			return goods_id;
		}
		public void setGoods_id(Integer goods_id) {
			this.goods_id = goods_id;
		}
		public String getGoods_name() {
			return goods_name;
		}
		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}
		public String getGoods_desc() {
			return goods_desc;
		}
		public void setGoods_desc(String goods_desc) {
			this.goods_desc = goods_desc;
		}
		public String getGoods_img() {
			return goods_img;
		}
		public void setGoods_img(String goods_img) {
			this.goods_img = goods_img;
		}
		public Integer getGoods_price() {
			return goods_price;
		}
		public void setGoods_price(Integer goods_price) {
			this.goods_price = goods_price;
		}
		public Integer getGoods_num() {
			return goods_num;
		}
		public void setGoods_num(Integer goods_num) {
			this.goods_num = goods_num;
		}
		public boolean isGoods_status() {
			return goods_status;
		}
		public void setGoods_status(boolean goods_status) {
			this.goods_status = goods_status;
		}
		public Ptype getPtype() {
			return ptype;
		}
		public void setPtype(Ptype ptype) {
			this.ptype = ptype;
		}
		public Pctype getPctype() {
			return pctype;
		}
		public void setPctype(Pctype pctype) {
			this.pctype = pctype;
		}
		@Override
		public String toString() {
			return "Goods [goods_id=" + goods_id + ", goods_name=" + goods_name + ", goods_desc=" + goods_desc
					+ ", goods_img=" + goods_img + ", goods_price=" + goods_price + ", goods_num=" + goods_num
					+ ", goods_status=" + goods_status + ", ptype=" + ptype + ", pctype=" + pctype + "]";
		}
		
		
		
		
		
}
