package br.com.recomecar.services;

import br.com.recomecar.bo.PedidoAjudaBO;
import br.com.recomecar.dao.PedidoAjudaDAO;
import br.com.recomecar.model.PedidoAjuda;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;

import java.sql.SQLException;
import java.util.List;

public class PedidoAjudaService {

    private PedidoAjudaDAO pedidoAjudaDAO;
    private PedidoAjudaBO pedidoAjudaBO = new PedidoAjudaBO();

    public PedidoAjudaService() throws SQLException, ClassNotFoundException {
        this.pedidoAjudaDAO = new PedidoAjudaDAO();
    }

    public String cadastrarPedidoAjuda(PedidoAjuda pedido) {
        if (pedido.getId() == 0) {
            throw new ValidacaoException("O ID do pedido de ajuda é obrigatório.");
        }
        if (pedido.getDescricao() == null || pedido.getDescricao().trim().isEmpty()) {
            throw new ValidacaoException("A descrição é obrigatória.");
        }
        if (pedido.getUsuarioId() == 0) {
            throw new ValidacaoException("O ID do usuário é obrigatório.");
        }
        if (pedido.getCategoriaId() == 0) {
            throw new ValidacaoException("O ID da categoria é obrigatório.");
        }
        if (pedido.getStatusPedidoId() == 0) {
            throw new ValidacaoException("O ID do status do pedido é obrigatório.");
        }
        pedidoAjudaBO.validarPrioridade(pedido);

        return pedidoAjudaDAO.inserir(pedido);
    }

    public List<PedidoAjuda> listarPedidosAjuda() {
        return pedidoAjudaDAO.listar();
    }

    public PedidoAjuda buscarPedidoAjuda(int id) {
        PedidoAjuda pedido = pedidoAjudaDAO.buscar(id);
        if (pedido == null) {
            throw new RegistroNaoEncontradoException("Pedido de ajuda não encontrado com id: " + id);
        }
        return pedido;
    }

    public String atualizarPedidoAjuda(PedidoAjuda pedido) {
        if (pedido.getId() == 0) {
            throw new ValidacaoException("O ID do pedido de ajuda é obrigatório.");
        }
        return pedidoAjudaDAO.atualizar(pedido);
    }

    public String deletarPedidoAjuda(int id) {
        return pedidoAjudaDAO.deletar(id);
    }
}
