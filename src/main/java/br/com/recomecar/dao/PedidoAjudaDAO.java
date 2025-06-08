package br.com.recomecar.dao;

import br.com.recomecar.model.PedidoAjuda;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoAjudaDAO {

    private Connection conexao;

    public PedidoAjudaDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(PedidoAjuda pedido) {
        int idAleatorio = (int) (Math.random() * 9_000_000) + 1_000_000;
        pedido.setId(idAleatorio);

        String sql = "INSERT INTO pedidos_ajuda_recomecar (id_pedido_ajuda, descricao, prioridade, dt_pedido, usuario_id, categoria_id, status_pedido_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId());
            stmt.setString(2, pedido.getDescricao());
            stmt.setInt(3, pedido.getPrioridade());
            stmt.setDate(4, pedido.getDataPedido());
            stmt.setInt(5, pedido.getUsuarioId());
            stmt.setInt(6, pedido.getCategoriaId());
            stmt.setInt(7, pedido.getStatusPedidoId());
            stmt.executeUpdate();
            return "Pedido de ajuda cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar pedido de ajuda: " + e.getMessage();
        }
    }


    public List<PedidoAjuda> listar() {
        List<PedidoAjuda> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_ajuda_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PedidoAjuda pedido = new PedidoAjuda();
                pedido.setId(rs.getInt("id_pedido_ajuda"));
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setPrioridade(rs.getInt("prioridade"));
                pedido.setDataPedido(rs.getDate("dt_pedido"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedido.setCategoriaId(rs.getInt("categoria_id"));
                pedido.setStatusPedidoId(rs.getInt("status_pedido_id"));
                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos de ajuda: " + e.getMessage());
        }
        return lista;
    }

    public PedidoAjuda buscar(int id) {
        String sql = "SELECT * FROM pedidos_ajuda_recomecar WHERE id_pedido_ajuda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PedidoAjuda pedido = new PedidoAjuda();
                pedido.setId(rs.getInt("id_pedido_ajuda"));
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setPrioridade(rs.getInt("prioridade"));
                pedido.setDataPedido(rs.getDate("dt_pedido"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedido.setCategoriaId(rs.getInt("categoria_id"));
                pedido.setStatusPedidoId(rs.getInt("status_pedido_id"));
                return pedido;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pedido de ajuda: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(PedidoAjuda pedido) {
        String sql = "UPDATE pedidos_ajuda_recomecar SET descricao = ?, prioridade = ?, dt_pedido = ?, usuario_id = ?, categoria_id = ?, status_pedido_id = ? WHERE id_pedido_ajuda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pedido.getDescricao());
            stmt.setInt(2, pedido.getPrioridade());
            stmt.setDate(3, pedido.getDataPedido());
            stmt.setInt(4, pedido.getUsuarioId());
            stmt.setInt(5, pedido.getCategoriaId());
            stmt.setInt(6, pedido.getStatusPedidoId());
            stmt.setInt(7, pedido.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Pedido de ajuda atualizado com sucesso!" : "Pedido de ajuda não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar pedido de ajuda: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM pedidos_ajuda_recomecar WHERE id_pedido_ajuda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Pedido de ajuda deletado com sucesso!" : "Pedido de ajuda não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar pedido de ajuda: " + e.getMessage();
        }
    }
}
