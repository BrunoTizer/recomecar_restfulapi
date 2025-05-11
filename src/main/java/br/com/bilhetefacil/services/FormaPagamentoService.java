package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.FormaPagamentoDAO;
import br.com.bilhetefacil.model.FormaPagamento;

import java.sql.SQLException;
import java.util.List;

public class FormaPagamentoService {

    private FormaPagamentoDAO formaPagamentoDAO;

    public FormaPagamentoService() throws SQLException, ClassNotFoundException {
        this.formaPagamentoDAO = new FormaPagamentoDAO();
    }

    public String cadastrar(FormaPagamento forma) {
        if (forma.getDescricao() == null || forma.getDescricao().trim().isEmpty()) {
            return "Erro: descrição é obrigatória.";
        }

        return formaPagamentoDAO.inserir(forma);
    }

    public List<FormaPagamento> listar() {
        return formaPagamentoDAO.listar();
    }

    public FormaPagamento buscar(int id) {
        return formaPagamentoDAO.buscar(id);
    }

    public String atualizar(FormaPagamento forma) {
        if (forma.getIdFormaPagamento() == 0) {
            return "Erro: ID da forma de pagamento é obrigatório.";
        }

        return formaPagamentoDAO.atualizar(forma);
    }

    public String deletar(int id) {
        return formaPagamentoDAO.deletar(id);
    }
}
