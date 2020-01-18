package br.edu.inatel.soa.logisticasanoj.soap;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.inatel.soa.logisticasanoj.dao.DeliveryDAO;
import br.edu.inatel.soa.logisticasanoj.model.Delivery;

public class LogisticService {
	private DeliveryDAO deliveryDAO = new DeliveryDAO();

	public Delivery[] getDeliveries() {
		Delivery[] result = new Delivery[0];
		ArrayList<Delivery> list = deliveryDAO.list(); 
		return list.toArray(result);
	}
	
	public Delivery[] getDeliveriesToDeliver() {
		Delivery[] result = new Delivery[0];
		ArrayList<Delivery> list = deliveryDAO.getToDeliverList(); 
		return list.toArray(result);
	}
	
	public Delivery getDeliveryById(Long deliveryID) {
		return deliveryDAO.getById(deliveryID);
	}
	
	public Delivery getDeliveryByOrderId(Long orderID) {
		return deliveryDAO.getByOrderId(orderID);
	}
	
	public String insertDelivery(Long orderID, Long clientID) {
		Delivery delivery = new Delivery();
		delivery.setOrderID(orderID);
		delivery.setClientID(clientID);
		delivery.setStatus("despachado");
		Long deliveryId = deliveryDAO.add(delivery);
		return "Delivery successfully created. New deliveryID: " + deliveryId;
	}
	
	public String makeDelivery(Long deliveryID, String rName, String rCPF, Boolean isClient, Calendar deliveredTime, Double deliveredLatitude, Double deliveredLongitude) {
		Delivery delivery = deliveryDAO.getById(deliveryID);
		if(delivery == null) {
			return "Impossible to find the delivery with ID = " + deliveryID + ".";
		}
		
		delivery.setStatus("entregue");
		delivery.setrName(rName);
		delivery.setrCPF(rCPF);
		delivery.setIsClient(isClient);
		delivery.setDeliveredTime(deliveredTime);
		delivery.setDeliveredLatitude(deliveredLatitude);
		delivery.setDeliveredLongitude(deliveredLongitude);
		deliveryDAO.update(delivery);
		return "Delivery " + deliveryID + " complete.";
	}
	
	public String deleteDelivery(Long deliveryID) {
		Delivery delivery = deliveryDAO.getById(deliveryID);
		if(delivery == null) {
			return "Impossible to find the delivery with ID = " + deliveryID + ".";
		}
		
		deliveryDAO.delete(delivery);
		return "Delivery with id = " + deliveryID + " was deleted.";
	}
}
