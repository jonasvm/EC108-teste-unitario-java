package br.edu.inatel.soa.logisticasanoj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import br.edu.inatel.soa.logisticasanoj.crm.CRMCustomer;
import br.edu.inatel.soa.logisticasanoj.model.Delivery;
import br.edu.inatel.soa.logisticasanoj.model.DeliveryItem;
import br.edu.inatel.soa.logisticasanoj.sales.ItemPedido;
import br.edu.inatel.soa.logisticasanoj.sales.SalesOrder;
import br.edu.inatel.soa.logisticasanoj.util.ConnectionFactory;

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
		
		String sql = "SELECT id, orderID, clientID, rName, rCPF, isClient, deliveredTime, deliveredLatitude, deliveredLongitude, status FROM delivery ORDER BY id";
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
		
		String sql = "SELECT id, orderID, clientID, rName, rCPF, isClient, deliveredTime, deliveredLatitude, deliveredLongitude, status FROM delivery WHERE status = 'despachado' ORDER BY id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
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
		
		String sql = "SELECT id, orderID, clientID, rName, rCPF, isClient, deliveredTime, deliveredLatitude, deliveredLongitude, status FROM delivery WHERE id = ? ORDER BY id";
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
	
	public Delivery getByOrderId(Long orderID) {
		Delivery delivery = null;
		
		String sql = "SELECT id, orderID, clientID, rName, rCPF, isClient, deliveredTime, deliveredLatitude, deliveredLongitude, status FROM delivery WHERE orderID = ? ORDER BY id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, orderID);
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
			WebResource webResource = client.resource("http://siecolacrm.azurewebsites.net/api/customer/" + order.getClientID());
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
			WebResource webResource = client.resource("http://andresilva.azurewebsites.net/api/pedido/" + order.getOrderID());
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
		order.setOrderID(rs.getLong(2));
		order.setClientID(rs.getLong(3));
		order.setrName(rs.getString(4));
		order.setrCPF(rs.getString(5));
		order.setIsClient((Boolean) rs.getObject(6));
		
		Timestamp horaEntrega = rs.getTimestamp(7);
		if(horaEntrega != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(horaEntrega);
			order.setDeliveredTime(calendar);
		}
		
		Object object = rs.getObject(8);
		order.setDeliveredLatitude(object == null ? null : Double.parseDouble(object.toString()));
		object = rs.getObject(9);
		order.setDeliveredLongitude(object == null ? null : Double.parseDouble(object.toString()));
		order.setStatus(rs.getString(10));
		
		return order;
	}

	public void update(Delivery delivery) {
		String sql = "UPDATE delivery SET orderID=?, clientID=?, rName=?, rCPF=?, isClient=?, deliveredTime=?, deliveredLatitude=?, deliveredLongitude=?, status=?  WHERE id=?;";
		
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
		String sql = "INSERT INTO delivery (orderID, clientID, rName, rCPF, isClient, deliveredTime, deliveredLatitude, deliveredLongitude, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
		ps.setLong(1, delivery.getOrderID());
		ps.setLong(2, delivery.getClientID());
		ps.setString(3, delivery.getrName());
		ps.setString(4, delivery.getrCPF());
		ps.setObject(5, delivery.getIsClient() == null ? null : delivery.getIsClient());
		ps.setObject(6, delivery.getDeliveredTime() == null ? null : new Timestamp(delivery.getDeliveredTime().getTimeInMillis()));
		ps.setObject(7, delivery.getDeliveredLatitude());
		ps.setObject(8, delivery.getDeliveredLongitude());
		ps.setString(9, delivery.getStatus());
	}
}
