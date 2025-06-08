package br.com.recomecar.dao;

import br.com.recomecar.model.Match;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    private Connection conexao;

    public MatchDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(Match match) {
        String sql = "INSERT INTO matches_recomecar (id_match, dt_match, oferta_ajuda_id, pedido_ajuda_id, status_match_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, match.getId());
            stmt.setDate(2, match.getDataMatch());
            stmt.setInt(3, match.getOfertaAjudaId());
            stmt.setInt(4, match.getPedidoAjudaId());
            stmt.setInt(5, match.getStatusMatchId());
            stmt.executeUpdate();
            return "Match cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar match: " + e.getMessage();
        }
    }

    public List<Match> listar() {
        List<Match> lista = new ArrayList<>();
        String sql = "SELECT * FROM matches_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id_match"));
                match.setDataMatch(rs.getDate("dt_match"));
                match.setOfertaAjudaId(rs.getInt("oferta_ajuda_id"));
                match.setPedidoAjudaId(rs.getInt("pedido_ajuda_id"));
                match.setStatusMatchId(rs.getInt("status_match_id"));
                lista.add(match);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar matches: " + e.getMessage());
        }
        return lista;
    }

    public Match buscar(int id) {
        String sql = "SELECT * FROM matches_recomecar WHERE id_match = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id_match"));
                match.setDataMatch(rs.getDate("dt_match"));
                match.setOfertaAjudaId(rs.getInt("oferta_ajuda_id"));
                match.setPedidoAjudaId(rs.getInt("pedido_ajuda_id"));
                match.setStatusMatchId(rs.getInt("status_match_id"));
                return match;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar match: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(Match match) {
        String sql = "UPDATE matches_recomecar SET dt_match = ?, oferta_ajuda_id = ?, pedido_ajuda_id = ?, status_match_id = ? WHERE id_match = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, match.getDataMatch());
            stmt.setInt(2, match.getOfertaAjudaId());
            stmt.setInt(3, match.getPedidoAjudaId());
            stmt.setInt(4, match.getStatusMatchId());
            stmt.setInt(5, match.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Match atualizado com sucesso!" : "Match não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar match: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM matches_recomecar WHERE id_match = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Match deletado com sucesso!" : "Match não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar match: " + e.getMessage();
        }
    }
}
