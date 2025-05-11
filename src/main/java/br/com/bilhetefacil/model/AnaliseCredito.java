package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class AnaliseCredito {

    private int idCredito;
    private int idUsuario;
    private double valor;
    private String status;
    private Timestamp criadoEm;
    private Timestamp expiraEm;

    public AnaliseCredito() {
        super();
    }

    public AnaliseCredito(int idCredito, int idUsuario, double valor, String status, Timestamp criadoEm, Timestamp expiraEm) {
        this.idCredito = idCredito;
        this.idUsuario = idUsuario;
        this.valor = valor;
        this.status = status;
        this.criadoEm = criadoEm;
        this.expiraEm = expiraEm;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Timestamp getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(Timestamp expiraEm) {
        this.expiraEm = expiraEm;
    }

    @Override
    public String toString() {
        return "AnaliseCredito{" +
                "idCredito=" + idCredito +
                ", idUsuario=" + idUsuario +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                ", criadoEm=" + criadoEm +
                ", expiraEm=" + expiraEm +
                '}';
    }
}