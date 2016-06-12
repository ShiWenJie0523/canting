package com.util;

public class MenuUtil {
	private String head;
	
	private StringBuffer menu = new StringBuffer();
	
	public MenuUtil(String head) {
		this.head = head;
	}

	private String head(){
		StringBuffer sb = new StringBuffer();
		sb.append("<li>");
		sb.append("<a>"+this.head+"</a>");
		sb.append("<ul>");
		return sb.toString();
	}
	
	private String foot(){
		StringBuffer sb = new StringBuffer();
		sb.append("</ul>");
		sb.append("</li>");
		return sb.toString();
	}
	
	public void createMenu(String url,String target,String rel,String name){
		this.menu.append("<li><a href=\""+url+"\" target=\""+target+"\" rel=\""+rel+"\">"+name+"</a></li>");
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(head());
		sb.append(this.menu.toString());
		sb.append(foot());
		return sb.toString();
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public StringBuffer getMenu() {
		return menu;
	}

	public void setMenu(StringBuffer menu) {
		this.menu = menu;
	}
	
	
}
