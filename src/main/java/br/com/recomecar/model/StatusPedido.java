package br.com.recomecar.model;

public class StatusPedido {
    private int idStatus;
    private String nome;

    public StatusPedido() {
        super();
    }

    public StatusPedido(int idStatus, String nome) {
        this.idStatus = idStatus;
        this.nome = nome;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatusPedido{" +
                "idStatus=" + idStatus +
                ", nome='" + nome + '\'' +
                '}';
    }
}
