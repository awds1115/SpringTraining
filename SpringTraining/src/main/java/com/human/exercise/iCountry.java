package com.human.exercise;

public class iCountry {
	private int country_id;
	private String country_iso_code;
	private String country_name;
	private String country_region;
	public iCountry() {
	}
	public iCountry(int country_id, String country_iso_code, String country_name, String country_region) {
		this.country_id = country_id;
		this.country_iso_code = country_iso_code;
		this.country_name = country_name;
		this.country_region = country_region;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_iso_code() {
		return country_iso_code;
	}
	public void setCountry_iso_code(String country_iso_code) {
		this.country_iso_code = country_iso_code;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getCountry_region() {
		return country_region;
	}
	public void setCountry_region(String country_region) {
		this.country_region = country_region;
	}
	
}
