package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String faceIdHash;
    private Timestamp dataCadastro;
    private String statusPagamentoTaxa;


    public Usuario() {
        super();
    }

    public Usuario(int idUsuario, String nome, String email, String senha, String faceIdHash, Timestamp dataCadastro, String statusPagamentoTaxa) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.faceIdHash = faceIdHash;
        this.dataCadastro = dataCadastro;
        this.statusPagamentoTaxa = statusPagamentoTaxa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFaceIdHash() {
        return faceIdHash;
    }

    public void setFaceIdHash(String faceIdHash) {
        this.faceIdHash = faceIdHash;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatusPagamentoTaxa() {
        return statusPagamentoTaxa;
    }

    public void setStatusPagamentoTaxa(String statusPagamentoTaxa) {
        this.statusPagamentoTaxa = statusPagamentoTaxa;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", faceIdHash='" + faceIdHash + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", statusPagamentoTaxa=" + statusPagamentoTaxa +
                '}';
    }
}