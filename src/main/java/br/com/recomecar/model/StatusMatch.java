package br.com.recomecar.model;

public class StatusMatch {

    private int idStatusMatch;
    private String nome;

    public StatusMatch() {
        super();
    }

    public StatusMatch(int idStatusMatch, String nome) {
        this.idStatusMatch = idStatusMatch;
        this.nome = nome;
    }

    public int getIdStatusMatch() {
        return idStatusMatch;
    }

    public void setIdStatusMatch(int idStatusMatch) {
        this.idStatusMatch = idStatusMatch;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatusMatch{" +
                "idStatusMatch=" + idStatusMatch +
                ", nome='" + nome + '\'' +
                '}';
    }
}
