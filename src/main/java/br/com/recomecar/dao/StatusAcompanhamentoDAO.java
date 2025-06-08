package br.com.recomecar.dao;

import br.com.recomecar.model.StatusAcompanhamento;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusAcompanhamentoDAO {

    private Connection conexao;

    public StatusAcompanhamentoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(StatusAcompanhamento status) {
        int idAleatorio = (int) (Math.random() * 900_000) + 100_000;
        status.setIdStatusAcompanhamento(idAleatorio);

        String sql = "INSERT INTO status_acompanhamento_recomecar (id_status_acompanhamento, nome) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, status.getIdStatusAcompanhamento());
            stmt.setString(2, status.getNome());
            stmt.executeUpdate();
            return "Status de acompanhamento cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar status de acompanhamento: " + e.getMessage();
        }
    }

    public List<StatusAcompanhamento> listar() {
        List<StatusAcompanhamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM status_acompanhamento_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StatusAcompanhamento status = new StatusAcompanhamento();
                status.setIdStatusAcompanhamento(rs.getInt("id_status_acompanhamento"));
                status.setNome(rs.getString("nome"));
                lista.add(status);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar status de acompanhamento: " + e.getMessage());
        }
        return lista;
    }

    public StatusAcompanhamento buscar(int id) {
        String sql = "SELECT * FROM status_acompanhamento_recomecar WHERE id_status_acompanhamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                StatusAcompanhamento status = new StatusAcompanhamento();
                status.setIdStatusAcompanhamento(rs.getInt("id_status_acompanhamento"));
                status.setNome(rs.getString("nome"));
                return status;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar status de acompanhamento: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(StatusAcompanhamento status) {
        String sql = "UPDATE status_acompanhamento_recomecar SET nome = ? WHERE id_status_acompanhamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, status.getNome());
            stmt.setInt(2, status.getIdStatusAcompanhamento());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Status de acompanhamento atualizado com sucesso!" : "Status de acompanhamento não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar status de acompanhamento: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM status_acompanhamento_recomecar WHERE id_status_acompanhamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Status de acompanhamento deletado com sucesso!" : "Status de acompanhamento não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar status de acompanhamento: " + e.getMessage();
        }
    }
}
