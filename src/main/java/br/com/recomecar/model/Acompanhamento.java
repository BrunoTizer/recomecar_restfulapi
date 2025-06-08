package br.com.recomecar.model;

import java.sql.Date;

public class Acompanhamento {

    private int id;
    private Date dataStatus;
    private String observacao;
    private int statusAcompanhamentoId;
    private int matchId;

    public Acompanhamento() {
        super();
    }

    public Acompanhamento(int id, Date dataStatus, String observacao, int statusAcompanhamentoId, int matchId) {
        this.id = id;
        this.dataStatus = dataStatus;
        this.observacao = observacao;
        this.statusAcompanhamentoId = statusAcompanhamentoId;
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getStatusAcompanhamentoId() {
        return statusAcompanhamentoId;
    }

    public void setStatusAcompanhamentoId(int statusAcompanhamentoId) {
        this.statusAcompanhamentoId = statusAcompanhamentoId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "Acompanhamento{" +
                "id=" + id +
                ", dataStatus=" + dataStatus +
                ", observacao='" + observacao + '\'' +
                ", statusAcompanhamentoId=" + statusAcompanhamentoId +
                ", matchId=" + matchId +
                '}';
    }
}