package com.iflytek.demo.dto;

import java.util.List;

@SuppressWarnings("serial")
public class SemEsse{

	private Long esseId;

	private Boolean esseSys;

	private String esseName;

	private String esseKey;
	
	/**
	 * 组合实体表达式
	 */
	private String esseExpress;
	

	private Integer esseType;

	private String esseExample;

	private String esseDesc;

	private List<SemEsseItem> esseItemList;

	public Long getEsseId() {
		return esseId;
	}

	public void setEsseId(Long esseId) {
		this.esseId = esseId;
	}

	public Boolean getEsseSys() {
		return esseSys;
	}

	public void setEsseSys(Boolean esseSys) {
		this.esseSys = esseSys;
	}

	public String getEsseName() {
		return esseName;
	}

	public void setEsseName(String esseName) {
		this.esseName = esseName;
	}

	public String getEsseKey() {
		return esseKey;
	}

	public void setEsseKey(String esseKey) {
//		this.esseKey = StringUtils.remove(esseKey, '.');
		this.esseKey = esseKey;
	}

	public Integer getEsseType() {
		return esseType;
	}

	public void setEsseType(Integer esseType) {
		this.esseType = esseType;
	}

	public String getEsseExample() {
		return esseExample;
	}

	public void setEsseExample(String esseExample) {
		this.esseExample = esseExample;
	}

	public String getEsseDesc() {
		return esseDesc;
	}

	public void setEsseDesc(String esseDesc) {
		this.esseDesc = esseDesc;
	}

	public List<SemEsseItem> getEsseItemList() {
		return esseItemList;
	}

	public void setEsseItemList(List<SemEsseItem> esseItemList) {
		this.esseItemList = esseItemList;
	}

	public void mergeEsseId() {
		List<SemEsseItem> esseItemList = this.getEsseItemList();
		if (null != esseItemList) {
			for (SemEsseItem semEsseItem : esseItemList) {
				semEsseItem.setEsseId(getEsseId());
			}
		}
	}

	public String getEsseExpress() {
		return esseExpress;
	}

	public void setEsseExpress(String esseExpress) {
		this.esseExpress = esseExpress;
	}
	
	
}
