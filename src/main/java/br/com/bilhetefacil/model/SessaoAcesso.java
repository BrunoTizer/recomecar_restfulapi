package br.com.bilhetefacil.model;

import java.sql.Timestamp;

public class SessaoAcesso {

    private int idSessao;
    private int idUsuario;
    private String tipoLogin;
    private Timestamp dataLogin;

    public SessaoAcesso() {
        super();
    }

    public SessaoAcesso(int idSessao, int idUsuario, String tipoLogin, Timestamp dataLogin) {
        this.idSessao = idSessao;
        this.idUsuario = idUsuario;
        this.tipoLogin = tipoLogin;
        this.dataLogin = dataLogin;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(String tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    public Timestamp getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(Timestamp dataLogin) {
        this.dataLogin = dataLogin;
    }

    @Override
    public String toString() {
        return "SessaoAcesso{" +
                "idSessao=" + idSessao +
                ", idUsuario=" + idUsuario +
                ", tipoLogin='" + tipoLogin + '\'' +
                ", dataLogin=" + dataLogin +
                '}';
    }
}
