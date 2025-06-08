package br.com.recomecar.dao;

import br.com.recomecar.model.OfertaAjuda;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfertaAjudaDAO {

    private Connection conexao;

    public OfertaAjudaDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(OfertaAjuda oferta) {
        String sql = "INSERT INTO ofertas_ajuda_recomecar (id_oferta_ajuda, descricao, dt_oferta, usuario_id, status_pedido_id, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, oferta.getId());
            stmt.setString(2, oferta.getDescricao());
            stmt.setDate(3, oferta.getDataOferta());
            stmt.setInt(4, oferta.getUsuarioId());
            stmt.setInt(5, oferta.getStatusPedidoId());
            stmt.setInt(6, oferta.getCategoriaId());
            stmt.executeUpdate();
            return "Oferta de ajuda cadastrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar oferta de ajuda: " + e.getMessage();
        }
    }

    public List<OfertaAjuda> listar() {
        List<OfertaAjuda> lista = new ArrayList<>();
        String sql = "SELECT * FROM ofertas_ajuda_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                OfertaAjuda oferta = new OfertaAjuda();
                oferta.setId(rs.getInt("id_oferta_ajuda"));
                oferta.setDescricao(rs.getString("descricao"));
                oferta.setDataOferta(rs.getDate("dt_oferta"));
                oferta.setUsuarioId(rs.getInt("usuario_id"));
                oferta.setStatusPedidoId(rs.getInt("status_pedido_id"));
                oferta.setCategoriaId(rs.getInt("categoria_id"));
                lista.add(oferta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar ofertas de ajuda: " + e.getMessage());
        }
        return lista;
    }

    public OfertaAjuda buscar(int id) {
        String sql = "SELECT * FROM ofertas_ajuda_recomecar WHERE id_oferta_ajuda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                OfertaAjuda oferta = new OfertaAjuda();
                oferta.setId(rs.getInt("id_oferta_ajuda"));
                oferta.setDescricao(rs.getString("descricao"));
                oferta.setDataOferta(rs.getDate("dt_oferta"));
                oferta.setUsuarioId(rs.getInt("usuario_id"));
                oferta.setStatusPedidoId(rs.getInt("status_pedido_id"));
                oferta.setCategoriaId(rs.getInt("categoria_id"));
                return oferta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar oferta de ajuda: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(OfertaAjuda oferta) {
        String sql = "UPDATE ofertas_ajuda_recomecar SET descricao = ?, dt_oferta = ?, usuario_id = ?, status_pedido_id = ?, categoria_id = ? WHERE id_oferta_ajuda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, oferta.getDescricao());
            stmt.setDate(2, oferta.getDataOferta());
            stmt.setInt(3, oferta.getUsuarioId());
            stmt.setInt(4, oferta.getStatusPedidoId());
            stmt.setInt(5, oferta.getCategoriaId());
            stmt.setInt(6, oferta.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Oferta de ajuda atualizada com sucesso!" : "Oferta de ajuda não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar oferta de ajuda: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM ofertas_ajuda_recomecar WHERE id_oferta_ajuda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Oferta de ajuda deletada com sucesso!" : "Oferta de ajuda não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar oferta de ajuda: " + e.getMessage();
        }
    }
}
