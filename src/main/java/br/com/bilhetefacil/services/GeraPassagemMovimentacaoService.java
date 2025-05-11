package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.GeraPassagemMovimentacaoDAO;
import br.com.bilhetefacil.model.GeraPassagemMovimentacao;

import java.sql.SQLException;
import java.util.List;

public class GeraPassagemMovimentacaoService {

    private GeraPassagemMovimentacaoDAO dao;

    public GeraPassagemMovimentacaoService() throws SQLException, ClassNotFoundException {
        this.dao = new GeraPassagemMovimentacaoDAO();
    }

    public String criar(GeraPassagemMovimentacao gpm) {
        if (gpm.getIdPassagem() <= 0 || gpm.getIdMovimentacao() <= 0) {
            return "Erro: IDs inválidos.";
        }
        return dao.inserir(gpm);
    }

    public List<GeraPassagemMovimentacao> listar() {
        return dao.listar();
    }

    public String deletar(int idPassagem, int idMovimentacao) {
        return dao.deletar(idPassagem, idMovimentacao);
    }
}
