package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.PassagemUtilizadaDAO;
import br.com.bilhetefacil.model.PassagemUtilizada;

import java.sql.SQLException;
import java.util.List;

public class PassagemUtilizadaService {

    private PassagemUtilizadaDAO dao;

    public PassagemUtilizadaService() throws SQLException, ClassNotFoundException {
        this.dao = new PassagemUtilizadaDAO();
    }

    public String cadastrar(PassagemUtilizada p) {
        if (p.getIdUsuario() <= 0) return "Erro: ID do usuário é obrigatório.";
        if (p.getValor() <= 0) return "Erro: valor deve ser maior que zero.";
        if (p.getDataHora() == null) return "Erro: data/hora é obrigatória.";
        return dao.inserir(p);
    }

    public List<PassagemUtilizada> listar() {
        return dao.listar();
    }

    public PassagemUtilizada buscar(int id) {
        return dao.buscar(id);
    }

    public String atualizar(PassagemUtilizada p) {
        if (p.getIdPassagem() <= 0) return "Erro: ID da passagem é obrigatório.";
        return dao.atualizar(p);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
