package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.GeraRecargaMovimentacaoDAO;
import br.com.bilhetefacil.model.GeraRecargaMovimentacao;

import java.sql.SQLException;
import java.util.List;

public class GeraRecargaMovimentacaoService {

    private GeraRecargaMovimentacaoDAO dao;

    public GeraRecargaMovimentacaoService() throws SQLException, ClassNotFoundException {
        this.dao = new GeraRecargaMovimentacaoDAO();
    }

    public String criar(GeraRecargaMovimentacao grm) {
        if (grm.getIdRecarga() <= 0 || grm.getIdMovimentacao() <= 0) {
            return "Erro: IDs inválidos.";
        }
        return dao.inserir(grm);
    }

    public List<GeraRecargaMovimentacao> listar() {
        return dao.listar();
    }

    public String deletar(int idRecarga, int idMovimentacao) {
        return dao.deletar(idRecarga, idMovimentacao);
    }
}
