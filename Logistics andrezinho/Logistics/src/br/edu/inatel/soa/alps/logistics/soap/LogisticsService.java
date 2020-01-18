package br.edu.inatel.soa.alps.logistics.soap;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.inatel.soa.alps.logistics.dao.DeliveryDAO;
import br.edu.inatel.soa.alps.logistics.model.Delivery;

public class LogisticsService {
	
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
	
	public Delivery getDeliveryById(Long deliveryId) {
		return deliveryDAO.getById(deliveryId);
	}
	
	public Delivery getDeliveryByOrderId(Long orderId) {
		return deliveryDAO.getByOrderId(orderId);
	}
	
	public String insertDelivery(Long orderId, Long clientId) {
		Delivery delivery = new Delivery();
		delivery.setOrderId(orderId);
		delivery.setClientId(clientId);
		delivery.setStatus("despachado");
		Long deliveryId = deliveryDAO.add(delivery);
		return "Delivery Created Sucessyfully. ID: " + deliveryId;
	}
	
	public String makeDelivery(Long deliveryId, String receiverName, String receiverCpf, Boolean isClientReceiver, Calendar deliveryMadeTime, Double deliveryMadeLatitude, Double deliveryMadeLongitude) {
		Delivery delivery = deliveryDAO.getById(deliveryId);
		if(delivery == null) {
			return "Could not find Delivery with ID = " + deliveryId + ".";
		}
		
		delivery.setStatus("entregue");
		delivery.setReceiverName(receiverName);
		delivery.setReceiverCpf(receiverCpf);
		delivery.setIsClientReceiver(isClientReceiver);
		delivery.setDeliveryMadeTime(deliveryMadeTime);
		delivery.setDeliveryMadeLatitude(deliveryMadeLatitude);
		delivery.setDeliveryMadeLongitude(deliveryMadeLongitude);
		deliveryDAO.update(delivery);
		return "Delivery " + deliveryId + " Made Sucessyfully.";
	}
	
	public String deleteDelivery(Long deliveryId) {
		Delivery delivery = deliveryDAO.getById(deliveryId);
		if(delivery == null) {
			return "Could not find Delivery with ID = " + deliveryId + ".";
		}
		
		deliveryDAO.delete(delivery);
		return "Delivery " + deliveryId + " Deleted.";
	}
}
