package br.edu.inatel.soa.alps.logistics.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeliveryItem {

	private Long productId = null;
	private String code = null;
	private String name = null;
	private String color = null;
	private String model = null;
	private Double quantity = null;
	
//	Hiding Fields tha does not matter to Logistics
//	private String description = null;
//	private Double weight = null;
//	private Double height = null;
//	private Double width = null;
//	private Double lenth = null;
//	private Double diameter = null;
//	private String image = null;
//	private Double price = null;

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

//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Double getWeight() {
//		return weight;
//	}
//
//	public void setWeight(Double weight) {
//		this.weight = weight;
//	}
//
//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

//	public Double getHeight() {
//		return height;
//	}
//
//	public void setHeight(Double height) {
//		this.height = height;
//	}
//
//	public Double getWidth() {
//		return width;
//	}
//
//	public void setWidth(Double width) {
//		this.width = width;
//	}
//
//	public Double getLenth() {
//		return lenth;
//	}
//
//	public void setLenth(Double lenth) {
//		this.lenth = lenth;
//	}
//
//	public Double getDiameter() {
//		return diameter;
//	}
//
//	public void setDiameter(Double diameter) {
//		this.diameter = diameter;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
}
