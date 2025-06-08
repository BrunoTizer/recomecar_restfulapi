package br.com.recomecar.services;

import br.com.recomecar.dao.StatusAcompanhamentoDAO;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;
import br.com.recomecar.model.StatusAcompanhamento;

import java.sql.SQLException;
import java.util.List;

public class StatusAcompanhamentoService {

    private StatusAcompanhamentoDAO statusAcompanhamentoDAO;

    public StatusAcompanhamentoService() throws SQLException, ClassNotFoundException {
        this.statusAcompanhamentoDAO = new StatusAcompanhamentoDAO();
    }

    public String cadastrarStatusAcompanhamento(StatusAcompanhamento status) {

        if (status.getNome() == null || status.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome do status é obrigatório.");
        }
        return statusAcompanhamentoDAO.inserir(status);
    }

    public List<StatusAcompanhamento> listarStatusAcompanhamento() {
        return statusAcompanhamentoDAO.listar();
    }

    public StatusAcompanhamento buscarStatusAcompanhamento(int id) {
        StatusAcompanhamento status = statusAcompanhamentoDAO.buscar(id);
        if (status == null) {
            throw new RegistroNaoEncontradoException("Status de acompanhamento não encontrado com id: " + id);
        }
        return status;
    }

    public String atualizarStatusAcompanhamento(StatusAcompanhamento status) {
        if (status.getIdStatusAcompanhamento() == 0) {
            throw new ValidacaoException("O ID do status de acompanhamento é obrigatório.");
        }
        return statusAcompanhamentoDAO.atualizar(status);
    }

    public String deletarStatusAcompanhamento(int id) {
        return statusAcompanhamentoDAO.deletar(id);
    }
}
