/**
 * Order.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.edu.inatel.soa.alps.logistics.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Delivery  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Long id = null;
	private Long orderId = null;
	private Long clientId = null;
	private String receiverName = null;
	private String receiverCpf = null;
	private Boolean isClientReceiver = null;
	private Calendar deliveryMadeTime = null;
	private Double deliveryMadeLatitude = null;
	private Double deliveryMadeLongitude = null;
	private String status = null;

	private Integer orderNumber = null;
	private Calendar orderDeliveryDate = null;
	private DeliveryItem[] deliveryItem = null;
	
	private String clientCPF = null;
	private String clientName = null;
	private String clientAddress = null;
	private String clientCity = null;
	private String clientState = null;
	private String clientCountry = null;
	private String clientZip = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverCpf() {
		return receiverCpf;
	}

	public void setReceiverCpf(String receiverCpf) {
		this.receiverCpf = receiverCpf;
	}

	public Boolean getIsClientReceiver() {
		return isClientReceiver;
	}

	public void setIsClientReceiver(Boolean isClientReceiver) {
		this.isClientReceiver = isClientReceiver;
	}

	public Calendar getDeliveryMadeTime() {
		return deliveryMadeTime;
	}

	public void setDeliveryMadeTime(Calendar deliveryMadeTime) {
		this.deliveryMadeTime = deliveryMadeTime;
	}

	public java.lang.Double getDeliveryMadeLatitude() {
		return deliveryMadeLatitude;
	}

	public void setDeliveryMadeLatitude(java.lang.Double deliveryMadeLatitude) {
		this.deliveryMadeLatitude = deliveryMadeLatitude;
	}

	public java.lang.Double getDeliveryMadeLongitude() {
		return deliveryMadeLongitude;
	}

	public void setDeliveryMadeLongitude(java.lang.Double deliveryMadeLongitude) {
		this.deliveryMadeLongitude = deliveryMadeLongitude;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Calendar getOrderDeliveryDate() {
		return orderDeliveryDate;
	}

	public void setOrderDeliveryDate(Calendar orderDeliveryDate) {
		this.orderDeliveryDate = orderDeliveryDate;
	}

	public String getClientCPF() {
		return clientCPF;
	}

	public void setClientCPF(String clientCPF) {
		this.clientCPF = clientCPF;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientCity() {
		return clientCity;
	}

	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}

	public String getClientState() {
		return clientState;
	}

	public void setClientState(String clientState) {
		this.clientState = clientState;
	}

	public String getClientCountry() {
		return clientCountry;
	}

	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}

	public String getClientZip() {
		return clientZip;
	}

	public void setClientZip(String clientZip) {
		this.clientZip = clientZip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DeliveryItem[] getDeliveryItem() {
		return deliveryItem;
	}

	public void setDeliveryItem(DeliveryItem[] deliveryItem) {
		this.deliveryItem = deliveryItem;
	}
}
