package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.NotificacaoDAO;
import br.com.bilhetefacil.model.Notificacao;

import java.sql.SQLException;
import java.util.List;

public class NotificacaoService {

    private NotificacaoDAO dao;

    public NotificacaoService() throws SQLException, ClassNotFoundException {
        this.dao = new NotificacaoDAO();
    }

    public String cadastrar(Notificacao n) {
        if (n.getIdUsuario() <= 0) return "Erro: ID do usuário é obrigatório.";
        if (n.getMensagem() == null || n.getMensagem().isEmpty()) return "Erro: mensagem obrigatória.";
        if (n.getTipo() == null || n.getTipo().isEmpty()) return "Erro: tipo obrigatório.";
        if (n.getVisualizado() != 'S' && n.getVisualizado() != 'N') return "Erro: visualizado deve ser 'S' ou 'N'.";

        return dao.inserir(n);
    }

    public List<Notificacao> listar() {
        return dao.listar();
    }

    public Notificacao buscar(int id) {
        return dao.buscar(id);
    }

    public String atualizar(Notificacao n) {
        if (n.getIdNotificacao() <= 0) return "Erro: ID da notificação é obrigatório.";
        return dao.atualizar(n);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
