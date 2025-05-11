package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.GeraPagamentoMovimentacaoDAO;
import br.com.bilhetefacil.model.GeraPagamentoMovimentacao;

import java.sql.SQLException;
import java.util.List;

public class GeraPagamentoMovimentacaoService {

    private GeraPagamentoMovimentacaoDAO dao;

    public GeraPagamentoMovimentacaoService() throws SQLException, ClassNotFoundException {
        this.dao = new GeraPagamentoMovimentacaoDAO();
    }

    public String criar(GeraPagamentoMovimentacao gpm) {
        if (gpm.getIdPagamento() <= 0 || gpm.getIdMovimentacao() <= 0) {
            return "Erro: IDs inválidos.";
        }
        return dao.inserir(gpm);
    }

    public List<GeraPagamentoMovimentacao> listar() {
        return dao.listar();
    }

    public String deletar(int idPagamento, int idMovimentacao) {
        return dao.deletar(idPagamento, idMovimentacao);
    }
}
