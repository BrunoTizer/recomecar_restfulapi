package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.VerificacaoEmail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VerificacaoEmailDAO {

    private Connection conexao;

    public VerificacaoEmailDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(VerificacaoEmail v) {
        String sql = "INSERT INTO verificacaoemail (id_usuario, codigo, criado_em, expira_em, usado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, v.getIdUsuario());
            stmt.setString(2, v.getCodigo());
            stmt.setTimestamp(3, v.getCriadoEm());
            stmt.setTimestamp(4, v.getExpiraEm());
            stmt.setString(5, v.getUsado());
            stmt.executeUpdate();
            return "Verificação criada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao criar verificação: " + e.getMessage();
        }
    }

    public List<VerificacaoEmail> listar() {
        List<VerificacaoEmail> lista = new ArrayList<>();
        String sql = "SELECT * FROM verificacaoemail";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VerificacaoEmail v = new VerificacaoEmail();
                v.setIdVerificacao(rs.getInt("id_verificacao"));
                v.setIdUsuario(rs.getInt("id_usuario"));
                v.setCodigo(rs.getString("codigo"));
                v.setCriadoEm(rs.getTimestamp("criado_em"));
                v.setExpiraEm(rs.getTimestamp("expira_em"));
                v.setUsado(rs.getString("usado"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar verificações: " + e.getMessage());
        }

        return lista;
    }

    public VerificacaoEmail buscar(int id) {
        String sql = "SELECT * FROM verificacaoemail WHERE id_verificacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new VerificacaoEmail(
                        rs.getInt("id_verificacao"),
                        rs.getInt("id_usuario"),
                        rs.getString("codigo"),
                        rs.getTimestamp("criado_em"),
                        rs.getTimestamp("expira_em"),
                        rs.getString("usado")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar verificação: " + e.getMessage());
        }

        return null;
    }

    public String atualizar(VerificacaoEmail v) {
        String sql = "UPDATE verificacaoemail SET id_usuario = ?, codigo = ?, criado_em = ?, expira_em = ?, usado = ? WHERE id_verificacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, v.getIdUsuario());
            stmt.setString(2, v.getCodigo());
            stmt.setTimestamp(3, v.getCriadoEm());
            stmt.setTimestamp(4, v.getExpiraEm());
            stmt.setString(5, v.getUsado());
            stmt.setInt(6, v.getIdVerificacao());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Verificação atualizada com sucesso!" : "Verificação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar verificação: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM verificacaoemail WHERE id_verificacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Verificação deletada com sucesso!" : "Verificação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar verificação: " + e.getMessage();
        }
    }
}
