package project.socket;

import java.io.Serializable;
import java.util.ArrayList;




public class OrderVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int orderNum;
	private ArrayList<String> orderList;
	private String whereUse;
	
	public OrderVO(int num, ArrayList<String> list, String whereUse) {
		this.orderNum = num;
		this.orderList = list;
		this.whereUse = whereUse;
	}
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public ArrayList<String> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<String> orderList) {
		this.orderList = orderList;
	}
	public String getWhereUse() {
		return whereUse;
	}
	public void setWhereUse(String whereUse) {
		this.whereUse = whereUse;
	}
	
}
