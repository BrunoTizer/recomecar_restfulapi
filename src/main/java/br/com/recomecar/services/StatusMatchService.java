package br.com.recomecar.services;

import br.com.recomecar.dao.StatusMatchDAO;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;
import br.com.recomecar.model.StatusMatch;

import java.sql.SQLException;
import java.util.List;

public class StatusMatchService {

    private StatusMatchDAO statusMatchDAO;

    public StatusMatchService() throws SQLException, ClassNotFoundException {
        this.statusMatchDAO = new StatusMatchDAO();
    }

    public String cadastrarStatusMatch(StatusMatch statusMatch) {

        if (statusMatch.getNome() == null || statusMatch.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome do status do match é obrigatório.");
        }
        return statusMatchDAO.inserir(statusMatch);
    }

    public List<StatusMatch> listarStatusMatch() {
        return statusMatchDAO.listar();
    }

    public StatusMatch buscarStatusMatch(int id) {
        StatusMatch statusMatch = statusMatchDAO.buscar(id);
        if (statusMatch == null) {
            throw new RegistroNaoEncontradoException("Status do match não encontrado com id: " + id);
        }
        return statusMatch;
    }

    public String atualizarStatusMatch(StatusMatch statusMatch) {
        if (statusMatch.getIdStatusMatch() == 0) {
            throw new ValidacaoException("O ID do status do match é obrigatório.");
        }
        return statusMatchDAO.atualizar(statusMatch);
    }

    public String deletarStatusMatch(int id) {
        return statusMatchDAO.deletar(id);
    }
}
