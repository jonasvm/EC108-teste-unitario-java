package br.edu.inatel.soa.logisticasanoj.model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

//serializar um objeto significa transforma-lo em uma 
//stream de bytes que pode gravada ou transmitida de alguma 
//maneira. A serialização deve garantir que a stream possa ser 
//convertida novamente no objeto que a gerou, ou seja, deserializada. 
//Usa-se serialização quando se quer gravar objetos em arquivos, trasmiti-los 
//via sockets, rmi and so on. Vc marca os objetos de uma classe para 
//serialização um implementando a interface java.io.Serializable ou através 
//de java.io.Externalizable a qual é uma subinterface de Serializable. Vale 
//tambem uma lida sobre o modificador transient.
// http://javafree.uol.com.br/topic-4398-Serializable.html

@XmlRootElement
public class Delivery implements java.io.Serializable {
	
	//tem que adicionar no caso de uma classe serializable
	private static final long serialVersionUID = 1L;

	private Long id = null;
	private Long orderID = null;
	private Long clientID = null;
	private String rName = null;
	private String rCPF = null;
	private Boolean isClient = null;
	private Calendar deliveredTime = null;
	private Double deliveredLatitude = null;
	private Double deliveredLongitude = null;
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
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public Long getClientID() {
		return clientID;
	}
	public void setClientID(Long clientID) {
		this.clientID = clientID;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrCPF() {
		return rCPF;
	}
	public void setrCPF(String rCPF) {
		this.rCPF = rCPF;
	}
	public Boolean getIsClient() {
		return isClient;
	}
	public void setIsClient(Boolean isClient) {
		this.isClient = isClient;
	}
	public Calendar getDeliveredTime() {
		return deliveredTime;
	}
	public void setDeliveredTime(Calendar deliveredTime) {
		this.deliveredTime = deliveredTime;
	}
	public Double getDeliveredLatitude() {
		return deliveredLatitude;
	}
	public void setDeliveredLatitude(Double deliveredLatitude) {
		this.deliveredLatitude = deliveredLatitude;
	}
	public Double getDeliveredLongitude() {
		return deliveredLongitude;
	}
	public void setDeliveredLongitude(Double deliveredLongitude) {
		this.deliveredLongitude = deliveredLongitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public DeliveryItem[] getDeliveryItem() {
		return deliveryItem;
	}
	public void setDeliveryItem(DeliveryItem[] deliveryItem) {
		this.deliveryItem = deliveryItem;
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
		
}
