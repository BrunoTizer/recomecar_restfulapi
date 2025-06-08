package br.com.recomecar.model;

import java.sql.Date;

public class Match {

    private int id;
    private Date dataMatch;
    private int ofertaAjudaId;
    private int pedidoAjudaId;
    private int statusMatchId;

    public Match() {
        super();
    }

    public Match(int id, Date dataMatch, int ofertaAjudaId, int pedidoAjudaId, int statusMatchId) {
        this.id = id;
        this.dataMatch = dataMatch;
        this.ofertaAjudaId = ofertaAjudaId;
        this.pedidoAjudaId = pedidoAjudaId;
        this.statusMatchId = statusMatchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataMatch() {
        return dataMatch;
    }

    public void setDataMatch(Date dataMatch) {
        this.dataMatch = dataMatch;
    }

    public int getOfertaAjudaId() {
        return ofertaAjudaId;
    }

    public void setOfertaAjudaId(int ofertaAjudaId) {
        this.ofertaAjudaId = ofertaAjudaId;
    }

    public int getPedidoAjudaId() {
        return pedidoAjudaId;
    }

    public void setPedidoAjudaId(int pedidoAjudaId) {
        this.pedidoAjudaId = pedidoAjudaId;
    }

    public int getStatusMatchId() {
        return statusMatchId;
    }

    public void setStatusMatchId(int statusMatchId) {
        this.statusMatchId = statusMatchId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", dataMatch=" + dataMatch +
                ", ofertaAjudaId=" + ofertaAjudaId +
                ", pedidoAjudaId=" + pedidoAjudaId +
                ", statusMatchId=" + statusMatchId +
                '}';
    }
}