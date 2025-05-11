package br.com.bilhetefacil.model;

public class GeraRecargaMovimentacao {

    private int idRecarga;
    private int idMovimentacao;

    public GeraRecargaMovimentacao() {
        super();
    }

    public GeraRecargaMovimentacao(int idRecarga, int idMovimentacao) {
        this.idRecarga = idRecarga;
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdRecarga() {
        return idRecarga;
    }

    public void setIdRecarga(int idRecarga) {
        this.idRecarga = idRecarga;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    @Override
    public String toString() {
        return "GeraRecargaMovimentacao{" +
                "idRecarga=" + idRecarga +
                ", idMovimentacao=" + idMovimentacao +
                '}';
    }
}