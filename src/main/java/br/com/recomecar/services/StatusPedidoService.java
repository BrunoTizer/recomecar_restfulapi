package br.com.recomecar.services;

import br.com.recomecar.dao.StatusPedidoDAO;
import br.com.recomecar.exceptions.ValidacaoException;
import br.com.recomecar.model.StatusPedido;

import br.com.recomecar.exceptions.RegistroNaoEncontradoException;

import java.sql.SQLException;
import java.util.List;

public class StatusPedidoService {

    private StatusPedidoDAO statusPedidoDAO;

    public StatusPedidoService() throws SQLException, ClassNotFoundException {
        this.statusPedidoDAO = new StatusPedidoDAO();
    }

    public String cadastrarStatusPedido(StatusPedido statusPedido) {

        if (statusPedido.getNome() == null || statusPedido.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome do status é obrigatório.");
        }
        return statusPedidoDAO.inserir(statusPedido);
    }


    public List<StatusPedido> listarStatusPedidos() {
        return statusPedidoDAO.listar();
    }

    public StatusPedido buscarStatusPedido(int id) {
        StatusPedido status = statusPedidoDAO.buscar(id);
        if (status == null) {
            throw new RegistroNaoEncontradoException("Status do pedido não encontrado com id: " + id);
        }
        return status;
    }

    public String atualizarStatusPedido(StatusPedido statusPedido) {
        if (statusPedido.getIdStatus() == 0) {
            throw new ValidacaoException("ID do status é obrigatório.");
        }
        return statusPedidoDAO.atualizar(statusPedido);
    }

    public String deletarStatusPedido(int id) {
        return statusPedidoDAO.deletar(id);
    }
}
