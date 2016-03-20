package com.LetsGetDigital.model;

import javax.persistence.Embeddable;

@Embeddable
public class CarType {
	public String getSipp() {
		return sipp;
	}
	public void setSipp(String sipp) {
		this.sipp = sipp;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	private String sipp;
	private String supplier;

}
