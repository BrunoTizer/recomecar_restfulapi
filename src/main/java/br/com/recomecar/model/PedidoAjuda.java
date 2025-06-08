package br.com.recomecar.model;

import java.sql.Date;

public class PedidoAjuda {

    private int id;
    private String descricao;
    private int prioridade;
    private Date dataPedido;
    private int usuarioId;
    private int categoriaId;
    private int statusPedidoId;

    public PedidoAjuda() {
        super();
    }

    public PedidoAjuda(int id, String descricao, int prioridade, Date dataPedido, int usuarioId, int categoriaId, int statusPedidoId) {
        this.id = id;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataPedido = dataPedido;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.statusPedidoId = statusPedidoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getStatusPedidoId() {
        return statusPedidoId;
    }

    public void setStatusPedidoId(int statusPedidoId) {
        this.statusPedidoId = statusPedidoId;
    }

    @Override
    public String toString() {
        return "PedidoAjuda{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", prioridade=" + prioridade +
                ", dataPedido=" + dataPedido +
                ", usuarioId=" + usuarioId +
                ", categoriaId=" + categoriaId +
                ", statusPedidoId=" + statusPedidoId +
                '}';
    }
}
