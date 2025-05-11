package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class PagamentoTaxa {
    private int idPagamento;
    private int idUsuario; // FK para Usuario
    private double valor;
    private Timestamp dataPagamento;
    private String status;

    public PagamentoTaxa() {
        super();
    }

    public PagamentoTaxa(int idPagamento, int idUsuario, double valor, Timestamp dataPagamento, String status) {
        this.idPagamento = idPagamento;
        this.idUsuario = idUsuario;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagamentoTaxa{" +
                "idPagamento=" + idPagamento +
                ", idUsuario=" + idUsuario +
                ", valor=" + valor +
                ", dataPagamento=" + dataPagamento +
                ", status='" + status + '\'' +
                '}';
    }
}