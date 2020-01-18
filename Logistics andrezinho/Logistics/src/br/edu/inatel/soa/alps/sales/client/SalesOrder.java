package br.edu.inatel.soa.alps.sales.client;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SalesOrder {

	public Integer PedidoId = null;
	public Integer UsuarioId = null;
	private String status = null;
    private BigDecimal pesoTotal = null;
    private BigDecimal precoTotal = null;
    private Calendar dataPedido = null;
    private Calendar dataEntrega = null;
    public ItemPedido[] ItemPedido = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Calendar getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Calendar getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Calendar dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getPedidoId() {
		return PedidoId;
	}

	public void setPedidoId(Integer PedidoId) {
		this.PedidoId = PedidoId;
	}

	public Integer getUsuarioId() {
		return UsuarioId;
	}

	public void setUsuarioId(Integer UsuarioId) {
		this.UsuarioId = UsuarioId;
	}

	public ItemPedido[] getItemPedido() {
		return ItemPedido;
	}

	public void setItemPedido(ItemPedido[] itemPedido) {
		ItemPedido = itemPedido;
	}
	
	
}


