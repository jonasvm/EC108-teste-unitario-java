package br.edu.inatel.soa.alps.crm.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CRMCustomer {

	public Integer CustomerId = null;
	private String cpf = null;
	private String name = null;
	private String address = null;
	private String city = null;
	private String state = null;
	private String country = null;
	private String zip = null;
	private String email = null;
	private String mobile = null;

	public Integer getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Integer CustomerId) {
		this.CustomerId = CustomerId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
