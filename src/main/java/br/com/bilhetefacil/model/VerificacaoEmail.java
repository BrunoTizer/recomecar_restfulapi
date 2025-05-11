package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class VerificacaoEmail {

    private int idVerificacao;
    private int idUsuario;
    private String codigo;
    private Timestamp criadoEm;
    private Timestamp expiraEm;
    private String usado;

    public VerificacaoEmail() {
    super();
    }

    public VerificacaoEmail(int idVerificacao, int idUsuario, String codigo, Timestamp criadoEm, Timestamp expiraEm, String usado) {
        this.idVerificacao = idVerificacao;
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.criadoEm = criadoEm;
        this.expiraEm = expiraEm;
        this.usado = usado;
    }

    public int getIdVerificacao() {
        return idVerificacao;
    }

    public void setIdVerificacao(int idVerificacao) {
        this.idVerificacao = idVerificacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Timestamp getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(Timestamp expiraEm) {
        this.expiraEm = expiraEm;
    }

    public String getUsado() {
        return usado;
    }

    public void setUsado(String usado) {
        this.usado = usado;
    }

    @Override
    public String toString() {
        return "VerificacaoEmail{" +
                "idVerificacao=" + idVerificacao +
                ", idUsuario=" + idUsuario +
                ", codigo='" + codigo + '\'' +
                ", criadoEm=" + criadoEm +
                ", expiraEm=" + expiraEm +
                ", usado='" + usado + '\'' +
                '}';
    }
}