package com.iflytek.demo.dto;

import java.io.Serializable;

public class SemAuxiliaryItem implements Serializable {

	private Long auxiItemId = null;

	private String auxiItemName;

	private String auxiItemExtra;

	private Long auxiId;

	public Long getAuxiItemId() {
		return auxiItemId;
	}

	public void setAuxiItemId(Long auxiItemId) {
		this.auxiItemId = auxiItemId;
	}

	public String getAuxiItemName() {
		return auxiItemName;
	}

	public void setAuxiItemName(String auxiItemName) {
		this.auxiItemName = auxiItemName;
	}

	public String getAuxiItemExtra() {
		return auxiItemExtra;
	}

	public void setAuxiItemExtra(String auxiItemExtra) {
		this.auxiItemExtra = auxiItemExtra;
	}

	public Long getAuxiId() {
		return auxiId;
	}

	public void setAuxiId(Long auxiId) {
		this.auxiId = auxiId;
	}

}
