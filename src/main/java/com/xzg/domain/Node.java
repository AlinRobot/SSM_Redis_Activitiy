/**
 * 
 */
package com.xzg.domain;

import java.io.Serializable;

/**
 * @author hasee
 * @TIME 2017��2��22��
 * ע��������غ�ʵ������
 */
public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pId;
	private String name;
	private String url;
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	private String target;
	private String open;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private char isflag;
	/** 
     * ��ʾ�ڵ���ӽڵ㼯�� 
     */  
/*    private List<Node> children;
    
    public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public void addChild(Node node){
    	if(this.children==null){  
            children=new ArrayList<Node>();  
            children.add(node);  
        }else{  
            children.add(node);  
        } 
    }*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public char getIsflag() {
		return isflag;
	}
	public void setIsflag(char isflag) {
		this.isflag = isflag;
	}
	
}
