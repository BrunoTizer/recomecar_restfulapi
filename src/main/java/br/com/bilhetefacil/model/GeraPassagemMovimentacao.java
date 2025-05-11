package br.com.bilhetefacil.model;

public class GeraPassagemMovimentacao {

    private int idPassagem;
    private int idMovimentacao;

    public GeraPassagemMovimentacao() {
        super();
    }

    public GeraPassagemMovimentacao(int idPassagem, int idMovimentacao) {
        this.idPassagem = idPassagem;
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    @Override
    public String toString() {
        return "GeraPassagemMovimentacao{" +
                "idPassagem=" + idPassagem +
                ", idMovimentacao=" + idMovimentacao +
                '}';
    }
}
