package com.zhi.model;

public class TreeLines {
	private Integer id;
	private String text;
	private Integer parent_id;
	private String state;
	private String iconCls;
	public TreeLines() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TreeLines(Integer id, String text, Integer parent_id, String state) {
		super();
		this.id = id;
		this.text = text;
		this.parent_id = parent_id;
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
}
