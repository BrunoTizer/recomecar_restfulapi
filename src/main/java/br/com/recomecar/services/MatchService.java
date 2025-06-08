package br.com.recomecar.services;

import br.com.recomecar.dao.MatchDAO;
import br.com.recomecar.model.Match;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;

import java.sql.SQLException;
import java.util.List;

public class MatchService {

    private MatchDAO matchDAO;

    public MatchService() throws SQLException, ClassNotFoundException {
        this.matchDAO = new MatchDAO();
    }

    public String cadastrarMatch(Match match) {
        if (match.getId() == 0) {
            throw new ValidacaoException("O ID do match é obrigatório.");
        }
        if (match.getOfertaAjudaId() == 0) {
            throw new ValidacaoException("O ID da oferta de ajuda é obrigatório.");
        }
        if (match.getPedidoAjudaId() == 0) {
            throw new ValidacaoException("O ID do pedido de ajuda é obrigatório.");
        }
        if (match.getStatusMatchId() == 0) {
            throw new ValidacaoException("O ID do status do match é obrigatório.");
        }
        return matchDAO.inserir(match);
    }

    public List<Match> listarMatches() {
        return matchDAO.listar();
    }

    public Match buscarMatch(int id) {
        Match match = matchDAO.buscar(id);
        if (match == null) {
            throw new RegistroNaoEncontradoException("Match não encontrado com id: " + id);
        }
        return match;
    }

    public String atualizarMatch(Match match) {
        if (match.getId() == 0) {
            throw new ValidacaoException("O ID do match é obrigatório.");
        }
        return matchDAO.atualizar(match);
    }

    public String deletarMatch(int id) {
        return matchDAO.deletar(id);
    }
}
