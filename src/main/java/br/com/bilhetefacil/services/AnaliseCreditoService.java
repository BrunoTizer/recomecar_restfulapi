package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.AnaliseCreditoDAO;
import br.com.bilhetefacil.model.AnaliseCredito;
import br.com.bilhetefacil.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class AnaliseCreditoService {
    private AnaliseCreditoDAO analiseCreditoDAO;

    public AnaliseCreditoService() throws SQLException, ClassNotFoundException {
        this.analiseCreditoDAO = new AnaliseCreditoDAO();
    }

    public String cadastrarCredito(AnaliseCredito credito) {
        if (credito.getIdUsuario() <= 0) {
            return "Erro: o ID do usuário é obrigatório.";
        }

        if (credito.getValor() <= 0) {
            return "Erro: valor deve ser maior que zero.";
        }

        if (credito.getStatus() == null || credito.getStatus().trim().isEmpty()) {
            return "Erro: status é obrigatório.";
        }

        if (credito.getCriadoEm() == null) {
            return "Erro: data de criação é obrigatória.";
        }

        if (credito.getExpiraEm() == null) {
            return "Erro: data de expiração é obrigatória.";
        }
        return analiseCreditoDAO.inserir(credito);
    }

    public List<AnaliseCredito> listarCredito() {
        return analiseCreditoDAO.listar();
    }

    public AnaliseCredito buscarCredito(int id) {
        return analiseCreditoDAO.buscar(id);
    }

    public String atualizarCredito(AnaliseCredito credito) {
        if (credito.getIdCredito() == 0) {
            return "Erro: ID da análise é obrigatório.";
        }

        return analiseCreditoDAO.atualizar(credito);
    }

    public String deletarCredito(int id) {
        return analiseCreditoDAO.deletar(id);
    }
}
