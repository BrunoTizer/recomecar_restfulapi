package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.MovimentacaoFinanceiraDAO;
import br.com.bilhetefacil.model.MovimentacaoFinanceira;

import java.sql.SQLException;
import java.util.List;

public class MovimentacaoFinanceiraService {

    private MovimentacaoFinanceiraDAO dao;

    public MovimentacaoFinanceiraService() throws SQLException, ClassNotFoundException {
        this.dao = new MovimentacaoFinanceiraDAO();
    }

    public String cadastrar(MovimentacaoFinanceira m) {
        if (m.getValor() <= 0) return "Erro: o valor deve ser maior que zero.";
        if (m.getData() == null) return "Erro: data é obrigatória.";
        if (m.getIdFormaPagamento() <= 0) return "Erro: forma de pagamento inválida.";

        return dao.inserir(m);
    }

    public List<MovimentacaoFinanceira> listar() {
        return dao.listar();
    }

    public MovimentacaoFinanceira buscar(int id) {
        return dao.buscar(id);
    }

    public String atualizar(MovimentacaoFinanceira m) {
        if (m.getIdMovimentacao() <= 0) return "Erro: ID da movimentação é obrigatório.";
        return dao.atualizar(m);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
