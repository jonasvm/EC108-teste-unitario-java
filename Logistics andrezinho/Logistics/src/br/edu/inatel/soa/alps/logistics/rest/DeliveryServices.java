package br.edu.inatel.soa.alps.logistics.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import br.edu.inatel.soa.alps.logistics.dao.DeliveryDAO;
import br.edu.inatel.soa.alps.logistics.model.Delivery;

@Path("/delivery")
public class DeliveryServices {
	
	private DeliveryDAO deliveryDAO = new DeliveryDAO();

	@GET
	@RolesAllowed({"admin", "user"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Delivery> list() {
		return deliveryDAO.list();
	}
	
	@GET
	@Path("toDeliver")
	@RolesAllowed({"admin", "user"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Delivery> getToDeliverList() {
		return deliveryDAO.getToDeliverList();
	}
	
	@GET
	@Path("{deliveryId}")
	@RolesAllowed({"admin", "user"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getDeliveryById(@PathParam("deliveryId") Long deliveryId) {
		Delivery delivery = deliveryDAO.getById(deliveryId);
		if(delivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Could not find Delivery with ID = " + deliveryId).build();			
		}
		return Response.ok(delivery).build();
	}
	
	@GET
	@Path("byOrderId/{orderId}")
	@RolesAllowed({"admin", "user"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getDeliveryByOrderId(@PathParam("orderId") Long orderId) {
		Delivery delivery = deliveryDAO.getByOrderId(orderId);
		if(delivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Could not find Delivery with Order ID = " + orderId).build();			
		}
		return Response.ok(delivery).build();
	}
	
	@PUT
	@RolesAllowed({"admin", "user"})
	@Path("makeDelivery/{deliveryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delivery(@PathParam("deliveryId") Long deliveryId, Delivery delivery) {
		Delivery databaseDelivery = deliveryDAO.getById(deliveryId);
		if(databaseDelivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Could not find Delivery with ID = " + deliveryId).build();			
		}
		
		databaseDelivery.setStatus("entregue");
		databaseDelivery.setReceiverName(delivery.getReceiverName());
		databaseDelivery.setReceiverCpf(delivery.getReceiverCpf());
		databaseDelivery.setIsClientReceiver(delivery.getIsClientReceiver());
		databaseDelivery.setDeliveryMadeTime(delivery.getDeliveryMadeTime());
		databaseDelivery.setDeliveryMadeLatitude(delivery.getDeliveryMadeLatitude());
		databaseDelivery.setDeliveryMadeLongitude(delivery.getDeliveryMadeLongitude());
		
		deliveryDAO.update(databaseDelivery);
		
		return Response.ok("Delivery " + deliveryId + " Made Sucessyfully.").build();
	}
	
	@POST
	@RolesAllowed({"admin", "user"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Delivery delivery) {
		delivery.setStatus("despachado");
		Long deliveryId = deliveryDAO.add(delivery);
		return Response.status(HttpServletResponse.SC_CREATED).entity(("Delivery Created Sucessyfully. ID: " + deliveryId)).build();
	}
	
	@DELETE
	@Path("{deliveryId}")
	@RolesAllowed({"admin", "user"})
	public Response delete(@PathParam("deliveryId") Long deliveryId) {
		Delivery delivery = deliveryDAO.getById(deliveryId);
		if(delivery == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).entity("Could not find Delivery with ID = " + deliveryId).build();			
		}
		
		deliveryDAO.delete(delivery);
		return Response.ok("Delivery " + deliveryId + " Deleted.").build();
	}
	
	
}
