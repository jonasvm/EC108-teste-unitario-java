package br.edu.inatel.soa.alps.sales.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemPedido {

	public Long ItemPedidoId = null;
	public Long ProdutoId = null;
	public Long PedidoId = null;
	private Double quantidade = null;
	private Produto produto = null;
	
	public Double getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Long getProdutoId() {
		return ProdutoId;
	}

	public void setProdutoId(Long produtoId) {
		ProdutoId = produtoId;
	}

	public Long getItemPedidoId() {
		return ItemPedidoId;
	}

	public void setItemPedidoId(Long itemPedidoId) {
		ItemPedidoId = itemPedidoId;
	}

	public Long getPedidoId() {
		return PedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		PedidoId = pedidoId;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
