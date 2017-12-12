package com.capitalone.squadron.ayalytics.beans;

public class Report1FetchedPOJO {

	/*
	 * This POJO is used to store fetched info from squadron DB to Table 2
	 */
	private Integer id;
	private String name;
	private String phone;
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Report1FetchedPOJO() {
	}

	@Override
	public String toString() {
		return "Report1FetchedPOJO [id=" + id + ", name=" + name + ", phone="
				+ phone + ", type=" + type + "]";
	}

	public Report1FetchedPOJO(Integer id, String name, String phone, Integer type) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.type = type;
	}

}
