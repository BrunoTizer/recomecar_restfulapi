package br.com.recomecar.dao;

import br.com.recomecar.model.Acompanhamento;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcompanhamentoDAO {

    private Connection conexao;

    public AcompanhamentoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(Acompanhamento acompanhamento) {
        int idAleatorio = (int) (Math.random() * 9_000_000) + 1_000_000;
        acompanhamento.setId(idAleatorio);

        String sql = "INSERT INTO acompanhamentos_recomecar (id_acompanhamento, dt_status, observacao, status_acompanhamento_id, match_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, acompanhamento.getId());
            stmt.setDate(2, acompanhamento.getDataStatus());
            stmt.setString(3, acompanhamento.getObservacao());
            stmt.setInt(4, acompanhamento.getStatusAcompanhamentoId());
            stmt.setInt(5, acompanhamento.getMatchId());
            stmt.executeUpdate();
            return "Acompanhamento cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar acompanhamento: " + e.getMessage();
        }
    }


    public List<Acompanhamento> listar() {
        List<Acompanhamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM acompanhamentos_recomecar";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Acompanhamento acompanhamento = new Acompanhamento();
                acompanhamento.setId(rs.getInt("id_acompanhamento"));
                acompanhamento.setDataStatus(rs.getDate("dt_status"));
                acompanhamento.setObservacao(rs.getString("observacao"));
                acompanhamento.setStatusAcompanhamentoId(rs.getInt("status_acompanhamento_id"));
                acompanhamento.setMatchId(rs.getInt("match_id"));
                lista.add(acompanhamento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar acompanhamentos: " + e.getMessage());
        }
        return lista;
    }

    public Acompanhamento buscar(int id) {
        String sql = "SELECT * FROM acompanhamentos_recomecar WHERE id_acompanhamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Acompanhamento acompanhamento = new Acompanhamento();
                acompanhamento.setId(rs.getInt("id_acompanhamento"));
                acompanhamento.setDataStatus(rs.getDate("dt_status"));
                acompanhamento.setObservacao(rs.getString("observacao"));
                acompanhamento.setStatusAcompanhamentoId(rs.getInt("status_acompanhamento_id"));
                acompanhamento.setMatchId(rs.getInt("match_id"));
                return acompanhamento;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar acompanhamento: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(Acompanhamento acompanhamento) {
        String sql = "UPDATE acompanhamentos_recomecar SET dt_status = ?, observacao = ?, status_acompanhamento_id = ?, match_id = ? WHERE id_acompanhamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, acompanhamento.getDataStatus());
            stmt.setString(2, acompanhamento.getObservacao());
            stmt.setInt(3, acompanhamento.getStatusAcompanhamentoId());
            stmt.setInt(4, acompanhamento.getMatchId());
            stmt.setInt(5, acompanhamento.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Acompanhamento atualizado com sucesso!" : "Acompanhamento não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar acompanhamento: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM acompanhamentos_recomecar WHERE id_acompanhamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Acompanhamento deletado com sucesso!" : "Acompanhamento não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar acompanhamento: " + e.getMessage();
        }
    }
}
