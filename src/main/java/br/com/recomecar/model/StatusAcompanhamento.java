package br.com.recomecar.model;

public class StatusAcompanhamento {
    private int idStatusAcompanhamento;
    private String nome;

    public StatusAcompanhamento() {
        super();
    }

    public StatusAcompanhamento(int idStatusAcompanhamento, String nome) {
        this.idStatusAcompanhamento = idStatusAcompanhamento;
        this.nome = nome;
    }

    public int getIdStatusAcompanhamento() {
        return idStatusAcompanhamento;
    }

    public void setIdStatusAcompanhamento(int idStatusAcompanhamento) {
        this.idStatusAcompanhamento = idStatusAcompanhamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatusAcompanhamento{" +
                "idStatusAcompanhamento=" + idStatusAcompanhamento +
                ", nome='" + nome + '\'' +
                '}';
    }
}
