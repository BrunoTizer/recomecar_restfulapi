package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.PagamentoTaxaDAO;
import br.com.bilhetefacil.model.PagamentoTaxa;

import java.sql.SQLException;
import java.util.List;

public class PagamentoTaxaService {

    private PagamentoTaxaDAO dao;

    public PagamentoTaxaService() throws SQLException, ClassNotFoundException {
        this.dao = new PagamentoTaxaDAO();
    }

    public String cadastrar(PagamentoTaxa p) {
        if (p.getIdUsuario() <= 0) return "Erro: ID do usuário é obrigatório.";
        if (p.getValor() <= 0) return "Erro: valor deve ser maior que zero.";
        if (p.getDataPagamento() == null) return "Erro: data de pagamento é obrigatória.";
        if (!p.getStatus().matches("pendente|pago|cancelado")) return "Erro: status inválido.";

        return dao.inserir(p);
    }

    public List<PagamentoTaxa> listar() {
        return dao.listar();
    }

    public PagamentoTaxa buscar(int id) {
        return dao.buscar(id);
    }

    public String atualizar(PagamentoTaxa p) {
        if (p.getIdPagamento() <= 0) return "Erro: ID do pagamento é obrigatório.";
        return dao.atualizar(p);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
