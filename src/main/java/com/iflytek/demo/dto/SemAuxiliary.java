package com.iflytek.demo.dto;

import java.util.List;

@SuppressWarnings("serial")
public class SemAuxiliary{
	private Long auxiId;

	private Boolean auxiSys;

	private String auxiName;

	private String auxiKey;

	private String auxiExample;

	private String auxiDesc;

	private List<SemAuxiliaryItem> auxiItemList;

	public List<SemAuxiliaryItem> getAuxiItemList() {
		return auxiItemList;
	}

	public void setAuxiItemList(List<SemAuxiliaryItem> auxiItemList) {
		this.auxiItemList = auxiItemList;
	}

	public Long getAuxiId() {
		return auxiId;
	}

	public void setAuxiId(Long auxiId) {
		this.auxiId = auxiId;
	}

	public Boolean getAuxiSys() {
		return auxiSys;
	}

	public void setAuxiSys(Boolean auxiSys) {
		this.auxiSys = auxiSys;
	}

	public String getAuxiName() {
		return auxiName;
	}

	public void setAuxiName(String auxiName) {
		this.auxiName = auxiName;
	}

	public String getAuxiKey() {
		return auxiKey;
	}

	public void setAuxiKey(String auxiKey) {
//		this.auxiKey = StringUtils.remove(auxiKey, '.');
		this.auxiKey = auxiKey;
	}

	public String getAuxiExample() {
		return auxiExample;
	}

	public void setAuxiExample(String auxiExample) {
		this.auxiExample = auxiExample;
	}

	public String getAuxiDesc() {
		return auxiDesc;
	}

	public void setAuxiDesc(String auxiDesc) {
		this.auxiDesc = auxiDesc;
	}

	public void mergeAuxiliaryId() {
		List<SemAuxiliaryItem> semAuxiliaryItemList = this.getAuxiItemList();
		if (null != semAuxiliaryItemList) {
			for (SemAuxiliaryItem semAuxiliaryItem : semAuxiliaryItemList) {
				semAuxiliaryItem.setAuxiId(getAuxiId());
			}
		}
	}
	
}
