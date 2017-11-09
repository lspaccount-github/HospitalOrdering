package com.cn.ssm.json1;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String name;
	private List<BanJi>  banJis = new ArrayList<BanJi>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BanJi> getBanJis() {
		return banJis;
	}
	public void setBanJis(List<BanJi> banJis) {
		this.banJis = banJis;
	}
}
