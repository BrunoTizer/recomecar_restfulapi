package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class MovimentacaoFinanceira {
    private int idMovimentacao;
    private double valor;
    private String descricao;
    private Timestamp data;
    private int idFormaPagamento;

    public MovimentacaoFinanceira() {
        super();
    }

    public MovimentacaoFinanceira(int idMovimentacao, double valor, String descricao, Timestamp data, int idFormaPagamento) {
        this.idMovimentacao = idMovimentacao;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.idFormaPagamento = idFormaPagamento;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    @Override
    public String toString() {
        return "MovimentacaoFinanceira{" +
                "idMovimentacao=" + idMovimentacao +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", idFormaPagamento=" + idFormaPagamento +
                '}';
    }
}