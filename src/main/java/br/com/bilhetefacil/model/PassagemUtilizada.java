package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class PassagemUtilizada {
    private int idPassagem;
    private int idUsuario;
    private Timestamp dataHora;
    private double valor;
    private String localizacao;

    public PassagemUtilizada() {
        super();
    }

    public PassagemUtilizada(int idPassagem, int idUsuario, Timestamp dataHora, double valor, String localizacao) {
        this.idPassagem = idPassagem;
        this.idUsuario = idUsuario;
        this.dataHora = dataHora;
        this.valor = valor;
        this.localizacao = localizacao;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "PassagemUtilizada{" +
                "idPassagem=" + idPassagem +
                ", idUsuario=" + idUsuario +
                ", dataHora=" + dataHora +
                ", valor=" + valor +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}