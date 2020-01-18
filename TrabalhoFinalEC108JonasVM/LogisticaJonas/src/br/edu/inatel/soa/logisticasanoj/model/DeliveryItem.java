package br.edu.inatel.soa.logisticasanoj.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeliveryItem {
	private Long productId = null;
	private String code = null;
	private String name = null;
	private String color = null;
	private String model = null;
	private Double quantity = null;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
