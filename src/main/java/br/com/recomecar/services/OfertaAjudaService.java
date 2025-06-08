package br.com.recomecar.services;

import br.com.recomecar.bo.OfertaAjudaBO;
import br.com.recomecar.dao.OfertaAjudaDAO;
import br.com.recomecar.model.OfertaAjuda;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;

import java.sql.SQLException;
import java.util.List;

public class OfertaAjudaService {

    private OfertaAjudaDAO ofertaAjudaDAO;
    private OfertaAjudaBO ofertaAjudaBO = new OfertaAjudaBO();

    public OfertaAjudaService() throws SQLException, ClassNotFoundException {
        this.ofertaAjudaDAO = new OfertaAjudaDAO();
    }

    public String cadastrarOfertaAjuda(OfertaAjuda oferta) {

        if (oferta.getDescricao() == null || oferta.getDescricao().trim().isEmpty()) {
            throw new ValidacaoException("A descrição é obrigatória.");
        }
        if (oferta.getUsuarioId() == 0) {
            throw new ValidacaoException("O ID do usuário é obrigatório.");
        }
        if (oferta.getStatusPedidoId() == 0) {
            throw new ValidacaoException("O ID do status do pedido é obrigatório.");
        }
        if (oferta.getCategoriaId() == 0) {
            throw new ValidacaoException("O ID da categoria é obrigatório.");
        }
        ofertaAjudaBO.validarDescricao(oferta);
        return ofertaAjudaDAO.inserir(oferta);
    }

    public List<OfertaAjuda> listarOfertasAjuda() {
        return ofertaAjudaDAO.listar();
    }

    public OfertaAjuda buscarOfertaAjuda(int id) {
        OfertaAjuda oferta = ofertaAjudaDAO.buscar(id);
        if (oferta == null) {
            throw new RegistroNaoEncontradoException("Oferta de ajuda não encontrada com id: " + id);
        }
        return oferta;
    }

    public String atualizarOfertaAjuda(OfertaAjuda oferta) {
        if (oferta.getId() == 0) {
            throw new ValidacaoException("O ID da oferta é obrigatório.");
        }
        ofertaAjudaBO.validarDescricao(oferta);
        return ofertaAjudaDAO.atualizar(oferta);
    }

    public String deletarOfertaAjuda(int id) {
        return ofertaAjudaDAO.deletar(id);
    }
}
