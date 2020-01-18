package br.edu.inatel.soa.alps.logistics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import br.edu.inatel.soa.alps.crm.client.CRMCustomer;
import br.edu.inatel.soa.alps.logistics.model.Delivery;
import br.edu.inatel.soa.alps.logistics.model.DeliveryItem;
import br.edu.inatel.soa.alps.logistics.utils.ConnectionFactory;
import br.edu.inatel.soa.alps.sales.client.ItemPedido;
import br.edu.inatel.soa.alps.sales.client.SalesOrder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class DeliveryDAO {

	private Connection conn;
	
	public DeliveryDAO() {
		conn = ConnectionFactory.getConnection();
	}
	
	public ArrayList<Delivery> list() {
		ArrayList<Delivery> list = new ArrayList<Delivery>();
		
		String sql = "SELECT id, orderId, clientId, receiverName, receiverCpf, isClientReceiver, deliveryMadeTime, deliveryMadeLatitude, deliveryMadeLongitude, status FROM delivery ORDER BY id";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Delivery order = new Delivery();
				loadDatabaseFields(order, rs);
				loadSalesServiceFields(order);
				loadCRMServiceFields(order);
				list.add(order);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return list;
	}
	
	public ArrayList<Delivery> getToDeliverList() {
		ArrayList<Delivery> list = new ArrayList<Delivery>();
		
		String sql = "SELECT id, orderId, clientId, receiverName, receiverCpf, isClientReceiver, deliveryMadeTime, deliveryMadeLatitude, deliveryMadeLongitude, status FROM delivery WHERE status = 'despachado' ORDER BY id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(1, "despachado");
			ResultSet rs = preparedStatement.executeQuery(sql);
			while(rs.next()) {
				Delivery order = new Delivery();
				loadDatabaseFields(order, rs);
				loadSalesServiceFields(order);
				loadCRMServiceFields(order);
				list.add(order);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return list;
	}
	
	public void delete(Delivery delivery) {
		String sql = "DELETE FROM delivery WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, delivery.getId());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Delivery getById(Long id) {
		Delivery delivery = null;
		
		String sql = "SELECT id, orderId, clientId, receiverName, receiverCpf, isClientReceiver, deliveryMadeTime, deliveryMadeLatitude, deliveryMadeLongitude, status FROM delivery WHERE id = ? ORDER BY id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				delivery = new Delivery();
				loadDatabaseFields(delivery, rs);
				loadSalesServiceFields(delivery);
				loadCRMServiceFields(delivery);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return delivery;
	}
	
	public Delivery getByOrderId(Long orderId) {
		Delivery delivery = null;
		
		String sql = "SELECT id, orderId, clientId, receiverName, receiverCpf, isClientReceiver, deliveryMadeTime, deliveryMadeLatitude, deliveryMadeLongitude, status FROM delivery WHERE orderId = ? ORDER BY id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, orderId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				delivery = new Delivery();
				loadDatabaseFields(delivery, rs);
				loadSalesServiceFields(delivery);
				loadCRMServiceFields(delivery);
			}
			rs.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
		return delivery;
	}

	private void loadCRMServiceFields(Delivery order) {
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("Admin", "Admin"));
			WebResource webResource = client.resource("http://siecolacrm.azurewebsites.net/api/customer/" + order.getClientId());
			CRMCustomer crmCustomer = webResource.accept(MediaType.APPLICATION_JSON).get(CRMCustomer.class);
			
			order.setClientAddress(crmCustomer.getAddress());
			order.setClientCity(crmCustomer.getCity());
			order.setClientCountry(crmCustomer.getCountry());
			order.setClientCPF(crmCustomer.getCpf());
			order.setClientName(crmCustomer.getName());
			order.setClientState(crmCustomer.getState());
			order.setClientZip(crmCustomer.getZip());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	private void loadSalesServiceFields(Delivery order) {
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter("Admin", "Admin"));
			WebResource webResource = client.resource("http://andresilva.azurewebsites.net/api/pedido/" + order.getOrderId());
			SalesOrder salesOrder = webResource.accept(MediaType.APPLICATION_JSON).get(SalesOrder.class);
			
			order.setOrderDeliveryDate(salesOrder.getDataEntrega());
			order.setOrderNumber(salesOrder.getPedidoId());
			
			if(salesOrder.getItemPedido() != null) {
				order.setDeliveryItem(new DeliveryItem[salesOrder.getItemPedido().length]);
				
				for(int i=0; i<salesOrder.getItemPedido().length; i++) {
					ItemPedido itemPedido = salesOrder.getItemPedido()[i];
					
					DeliveryItem deliveryItem = new DeliveryItem();
					deliveryItem.setProductId(itemPedido.getProdutoId());
					deliveryItem.setCode(itemPedido.getProduto().getCodigo());
					deliveryItem.setName(itemPedido.getProduto().getNome());
					deliveryItem.setColor(itemPedido.getProduto().getCor());
					deliveryItem.setModel(itemPedido.getProduto().getModelo());
					deliveryItem.setQuantity(itemPedido.getQuantidade());
					order.getDeliveryItem()[i] = deliveryItem;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private Delivery loadDatabaseFields(Delivery order, ResultSet rs) throws SQLException {
		order.setId(rs.getLong(1));
		order.setOrderId(rs.getLong(2));
		order.setClientId(rs.getLong(3));
		order.setReceiverName(rs.getString(4));
		order.setReceiverCpf(rs.getString(5));
		order.setIsClientReceiver((Boolean) rs.getObject(6));
		
		Timestamp horaEntrega = rs.getTimestamp(7);
		if(horaEntrega != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(horaEntrega);
			order.setDeliveryMadeTime(calendar);
		}
		
		Object object = rs.getObject(8);
		order.setDeliveryMadeLatitude(object == null ? null : Double.parseDouble(object.toString()));
		object = rs.getObject(9);
		order.setDeliveryMadeLongitude(object == null ? null : Double.parseDouble(object.toString()));
		order.setStatus(rs.getString(10));
		
		return order;
	}

	public void update(Delivery delivery) {
		String sql = "UPDATE delivery SET orderId=?, clientId=?, receiverName=?, receiverCpf=?, isClientReceiver=?, deliveryMadeTime=?, deliveryMadeLatitude=?, deliveryMadeLongitude=?, status=?  WHERE id=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			loadPreparedStatementParameter(ps, delivery);
			ps.setLong(10, delivery.getId());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Long add(Delivery delivery) {
		String sql = "INSERT INTO delivery (orderId, clientId, receiverName, receiverCpf, isClientReceiver, deliveryMadeTime, deliveryMadeLatitude, deliveryMadeLongitude, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Long deliveryId = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			loadPreparedStatementParameter(ps, delivery);
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				deliveryId = rs.getLong(1);
			}
			rs.clearWarnings();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return deliveryId;
	}
	
	private void loadPreparedStatementParameter(PreparedStatement ps, Delivery delivery) throws SQLException {
		ps.setLong(1, delivery.getOrderId());
		ps.setLong(2, delivery.getClientId());
		ps.setString(3, delivery.getReceiverName());
		ps.setString(4, delivery.getReceiverCpf());
		ps.setObject(5, delivery.getIsClientReceiver() == null ? null : delivery.getIsClientReceiver());
		ps.setObject(6, delivery.getDeliveryMadeTime() == null ? null : new Timestamp(delivery.getDeliveryMadeTime().getTimeInMillis()));
		ps.setObject(7, delivery.getDeliveryMadeLatitude());
		ps.setObject(8, delivery.getDeliveryMadeLongitude());
		ps.setString(9, delivery.getStatus());
	}
}
