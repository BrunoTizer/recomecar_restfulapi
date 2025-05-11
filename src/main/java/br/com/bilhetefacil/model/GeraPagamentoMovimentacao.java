package br.com.bilhetefacil.model;

public class GeraPagamentoMovimentacao {

    private int idPagamento;
    private int idMovimentacao;

    public GeraPagamentoMovimentacao() {
        super();
    }

    public GeraPagamentoMovimentacao(int idPagamento, int idMovimentacao) {
        this.idPagamento = idPagamento;
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    @Override
    public String toString() {
        return "GeraPagamentoMovimentacao{" +
                "idPagamento=" + idPagamento +
                ", idMovimentacao=" + idMovimentacao +
                '}';
    }
}
