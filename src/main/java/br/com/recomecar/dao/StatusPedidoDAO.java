package br.com.recomecar.dao;

import br.com.recomecar.model.StatusPedido;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StatusPedidoDAO {

    private Connection conexao;

    public StatusPedidoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(StatusPedido statusPedido) {
        int idAleatorio = (int) (Math.random() * 900_000) + 100_000;
        statusPedido.setIdStatus(idAleatorio);

        String sql = "INSERT INTO status_pedido_recomecar (id_status, nome) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, statusPedido.getIdStatus());
            stmt.setString(2, statusPedido.getNome());
            stmt.executeUpdate();
            return "Status do pedido cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar status do pedido: " + e.getMessage();
        }
    }

    public List<StatusPedido> listar() {
        List<StatusPedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM status_pedido_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StatusPedido statusPedido = new StatusPedido();
                statusPedido.setIdStatus(rs.getInt("id_status"));
                statusPedido.setNome(rs.getString("nome"));
                lista.add(statusPedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar status do pedido: " + e.getMessage());
        }
        return lista;
    }

    public StatusPedido buscar(int id) {
        String sql = "SELECT * FROM status_pedido_recomecar WHERE id_status = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                StatusPedido statusPedido = new StatusPedido();
                statusPedido.setIdStatus(rs.getInt("id_status"));
                statusPedido.setNome(rs.getString("nome"));
                return statusPedido;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar status do pedido: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(StatusPedido statusPedido) {
        String sql = "UPDATE status_pedido_recomecar SET nome = ? WHERE id_status = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, statusPedido.getNome());
            stmt.setInt(2, statusPedido.getIdStatus());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Status do pedido atualizado com sucesso!" : "Status do pedido não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar status do pedido: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM status_pedido_recomecar WHERE id_status = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Status do pedido deletado com sucesso!" : "Status do pedido não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar status do pedido: " + e.getMessage();
        }
    }

}
