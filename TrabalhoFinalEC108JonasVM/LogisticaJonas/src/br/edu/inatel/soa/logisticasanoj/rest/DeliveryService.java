package br.edu.inatel.soa.logisticasanoj.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.inatel.soa.logisticasanoj.dao.DeliveryDAO;
import br.edu.inatel.soa.logisticasanoj.model.Delivery;

public class DeliveryService {
	private DeliveryDAO deliveryDAO = new DeliveryDAO();

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Delivery> list() {
		return deliveryDAO.list();
	}
	
	@GET
	@Path("toDeliver")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Delivery> getToDeliverList() {
		return deliveryDAO.getToDeliverList();
	}
	
	@GET
	@Path("{deliveryID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getDeliveryById(@PathParam("deliveryId") Long deliveryID) {
		Delivery delivery = deliveryDAO.getById(deliveryID);
		if(delivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Impossible to find the delivery with ID = " + deliveryID).build();			
		}
		return Response.ok(delivery).build();
	}
	
	@GET
	@Path("byOrderID/{orderID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getDeliveryByOrderId(@PathParam("orderId") Long orderID) {
		Delivery delivery = deliveryDAO.getByOrderId(orderID);
		if(delivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Impossible to find the delivery with ID = " + orderID).build();			
		}
		return Response.ok(delivery).build();
	}
	
	@PUT
	@Path("performDelivery/{deliveryID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delivery(@PathParam("deliveryId") Long deliveryID, Delivery delivery) {
		Delivery databaseDelivery = deliveryDAO.getById(deliveryID);
		if(databaseDelivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Impossible to find the delivery with ID = " + deliveryID).build();			
		}
		
		databaseDelivery.setStatus("entregue");
		databaseDelivery.setrName(delivery.getrName());
		databaseDelivery.setrCPF(delivery.getrCPF());
		databaseDelivery.setIsClient(delivery.getIsClient());
		databaseDelivery.setDeliveredTime(delivery.getDeliveredTime());
		databaseDelivery.setDeliveredLatitude(delivery.getDeliveredLatitude());
		databaseDelivery.setDeliveredLongitude(delivery.getDeliveredLongitude());
		
		deliveryDAO.update(databaseDelivery);
		
		return Response.ok("Delivery successfully created. New deliveryID: " + deliveryID).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Delivery delivery) {
		delivery.setStatus("despachado");
		Long deliveryID = deliveryDAO.add(delivery);
		return Response.status(HttpServletResponse.SC_CREATED).entity(("Delivery successfully created. New deliveryID: " + deliveryID)).build();
	}
	
	@DELETE
	@Path("{deliveryID}")
	public Response delete(@PathParam("deliveryId") Long deliveryID) {
		Delivery delivery = deliveryDAO.getById(deliveryID);
		if(delivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Impossible to find the delivery with ID = " + deliveryID).build();			
		}
		
		deliveryDAO.delete(delivery);
		return Response.ok("Delivery with id = " + deliveryID + " was deleted.").build();
	}
}
