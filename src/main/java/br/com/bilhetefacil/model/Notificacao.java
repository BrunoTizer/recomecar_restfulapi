package br.com.bilhetefacil.model;

public class Notificacao {
    private int idNotificacao;
    private int idUsuario;
    private String mensagem;
    private String tipo;
    private char visualizado;

    public Notificacao() {
        super();
    }

    public Notificacao(int idNotificacao, int idUsuario, String mensagem, String tipo, char visualizado) {
        this.idNotificacao = idNotificacao;
        this.idUsuario = idUsuario;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.visualizado = visualizado;
    }

    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public char getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(char visualizado) {
        this.visualizado = visualizado;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "idNotificacao=" + idNotificacao +
                ", idUsuario=" + idUsuario +
                ", mensagem='" + mensagem + '\'' +
                ", tipo='" + tipo + '\'' +
                ", visualizado=" + visualizado +
                '}';
    }
}
