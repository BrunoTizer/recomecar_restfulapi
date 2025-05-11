package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.SessaoAcessoDAO;
import br.com.bilhetefacil.model.SessaoAcesso;

import java.sql.SQLException;
import java.util.List;

public class SessaoAcessoService {

    private SessaoAcessoDAO dao;

    public SessaoAcessoService() throws SQLException, ClassNotFoundException {
        this.dao = new SessaoAcessoDAO();
    }

    public String cadastrar(SessaoAcesso s) {
        if (s.getIdUsuario() <= 0) return "Erro: ID do usuário é obrigatório.";
        if (s.getTipoLogin() == null || s.getTipoLogin().isBlank()) return "Erro: tipo de login obrigatório.";
        if (s.getDataLogin() == null) return "Erro: data de login obrigatória.";
        return dao.inserir(s);
    }

    public List<SessaoAcesso> listar() {
        return dao.listar();
    }

    public SessaoAcesso buscar(int id) {
        return dao.buscar(id);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
