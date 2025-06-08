package br.com.recomecar.dao;

import br.com.recomecar.model.StatusMatch;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusMatchDAO {

    private Connection conexao;

    public StatusMatchDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(StatusMatch statusMatch) {
        int idAleatorio = (int) (Math.random() * 900_000) + 100_000;
        statusMatch.setIdStatusMatch(idAleatorio);

        String sql = "INSERT INTO status_match_recomecar (id_status_match, nome) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, statusMatch.getIdStatusMatch());
            stmt.setString(2, statusMatch.getNome());
            stmt.executeUpdate();
            return "Status do match cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar status do match: " + e.getMessage();
        }
    }


    public List<StatusMatch> listar() {
        List<StatusMatch> lista = new ArrayList<>();
        String sql = "SELECT * FROM status_match_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StatusMatch statusMatch = new StatusMatch();
                statusMatch.setIdStatusMatch(rs.getInt("id_status_match"));
                statusMatch.setNome(rs.getString("nome"));
                lista.add(statusMatch);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar status do match: " + e.getMessage());
        }
        return lista;
    }

    public StatusMatch buscar(int id) {
        String sql = "SELECT * FROM status_match_recomecar WHERE id_status_match = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                StatusMatch statusMatch = new StatusMatch();
                statusMatch.setIdStatusMatch(rs.getInt("id_status_match"));
                statusMatch.setNome(rs.getString("nome"));
                return statusMatch;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar status do match: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(StatusMatch statusMatch) {
        String sql = "UPDATE status_match_recomecar SET nome = ? WHERE id_status_match = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, statusMatch.getNome());
            stmt.setInt(2, statusMatch.getIdStatusMatch());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Status do match atualizado com sucesso!" : "Status do match não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar status do match: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM status_match_recomecar WHERE id_status_match = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Status do match deletado com sucesso!" : "Status do match não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar status do match: " + e.getMessage();
        }
    }
}
