package br.com.recomecar.model;

import java.sql.Date;

public class OfertaAjuda {

    private int id;
    private String descricao;
    private Date dataOferta;
    private int usuarioId;
    private int statusPedidoId;
    private int categoriaId;

    public OfertaAjuda() {
        super();
    }

    public OfertaAjuda(int id, String descricao, Date dataOferta, int usuarioId, int statusPedidoId, int categoriaId) {
        this.id = id;
        this.descricao = descricao;
        this.dataOferta = dataOferta;
        this.usuarioId = usuarioId;
        this.statusPedidoId = statusPedidoId;
        this.categoriaId = categoriaId;
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

    public Date getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(Date dataOferta) {
        this.dataOferta = dataOferta;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getStatusPedidoId() {
        return statusPedidoId;
    }

    public void setStatusPedidoId(int statusPedidoId) {
        this.statusPedidoId = statusPedidoId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        return "OfertaAjuda{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataOferta=" + dataOferta +
                ", usuarioId=" + usuarioId +
                ", statusPedidoId=" + statusPedidoId +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
