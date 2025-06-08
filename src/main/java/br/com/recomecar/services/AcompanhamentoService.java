package br.com.recomecar.services;

import br.com.recomecar.dao.AcompanhamentoDAO;
import br.com.recomecar.model.Acompanhamento;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;

import java.sql.SQLException;
import java.util.List;

public class AcompanhamentoService {

    private AcompanhamentoDAO acompanhamentoDAO;

    public AcompanhamentoService() throws SQLException, ClassNotFoundException {
        this.acompanhamentoDAO = new AcompanhamentoDAO();
    }

    public String cadastrarAcompanhamento(Acompanhamento acompanhamento) {
        if (acompanhamento.getId() == 0) {
            throw new ValidacaoException("O ID do acompanhamento é obrigatório.");
        }
        if (acompanhamento.getStatusAcompanhamentoId() == 0) {
            throw new ValidacaoException("O ID do status de acompanhamento é obrigatório.");
        }
        if (acompanhamento.getMatchId() == 0) {
            throw new ValidacaoException("O ID do match é obrigatório.");
        }
        return acompanhamentoDAO.inserir(acompanhamento);
    }

    public List<Acompanhamento> listarAcompanhamentos() {
        return acompanhamentoDAO.listar();
    }

    public Acompanhamento buscarAcompanhamento(int id) {
        Acompanhamento acompanhamento = acompanhamentoDAO.buscar(id);
        if (acompanhamento == null) {
            throw new RegistroNaoEncontradoException("Acompanhamento não encontrado com id: " + id);
        }
        return acompanhamento;
    }

    public String atualizarAcompanhamento(Acompanhamento acompanhamento) {
        if (acompanhamento.getId() == 0) {
            throw new ValidacaoException("O ID do acompanhamento é obrigatório.");
        }
        return acompanhamentoDAO.atualizar(acompanhamento);
    }

    public String deletarAcompanhamento(int id) {
        return acompanhamentoDAO.deletar(id);
    }
}
