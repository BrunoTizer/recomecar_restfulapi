package br.com.recomecar.model;

public class Categoria {


    private int idCategoria;
    private String nome;
    private String descricao;

    public Categoria() {
        super();
    }

    public Categoria(int idCategoria, String nome, String descricao) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
