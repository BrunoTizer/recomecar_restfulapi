package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class Recarga {
    private int idRecarga;
    private int idUsuario;
    private double valor;
    private Timestamp dataRecarga;


    public Recarga() {
        super();
    }

    public Recarga(int idRecarga, int idUsuario, double valor, Timestamp dataRecarga) {
        this.idRecarga = idRecarga;
        this.idUsuario = idUsuario;
        this.valor = valor;
        this.dataRecarga = dataRecarga;
    }

    public int getIdRecarga() {
        return idRecarga;
    }

    public void setIdRecarga(int idRecarga) {
        this.idRecarga = idRecarga;
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

    public Timestamp getDataRecarga() {
        return dataRecarga;
    }

    public void setDataRecarga(Timestamp dataRecarga) {
        this.dataRecarga = dataRecarga;
    }

    @Override
    public String toString() {
        return "Recarga{" +
                "idRecarga=" + idRecarga +
                ", idUsuario=" + idUsuario +
                ", valor=" + valor +
                ", dataRecarga=" + dataRecarga +
                '}';
    }
}
