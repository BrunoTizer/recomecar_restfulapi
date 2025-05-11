package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.VerificacaoEmailDAO;
import br.com.bilhetefacil.model.VerificacaoEmail;

import java.sql.SQLException;
import java.util.List;

public class VerificacaoEmailService {

    private VerificacaoEmailDAO dao;

    public VerificacaoEmailService() throws SQLException, ClassNotFoundException {
        this.dao = new VerificacaoEmailDAO();
    }

    public String cadastrar(VerificacaoEmail v) {
        if (v.getIdUsuario() <= 0) return "Erro: ID do usuário é obrigatório.";
        if (v.getCodigo() == null || v.getCodigo().isBlank()) return "Erro: código é obrigatório.";
        if (v.getCriadoEm() == null || v.getExpiraEm() == null) return "Erro: datas obrigatórias.";
        if (!v.getUsado().equalsIgnoreCase("S") && !v.getUsado().equalsIgnoreCase("N")) return "Erro: usado deve ser 'S' ou 'N'.";

        return dao.inserir(v);
    }

    public List<VerificacaoEmail> listar() {
        return dao.listar();
    }

    public VerificacaoEmail buscar(int id) {
        return dao.buscar(id);
    }

    public String atualizar(VerificacaoEmail v) {
        if (v.getIdVerificacao() <= 0) return "Erro: ID da verificação é obrigatório.";
        return dao.atualizar(v);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
