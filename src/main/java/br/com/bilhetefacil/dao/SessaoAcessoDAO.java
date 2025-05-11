package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.SessaoAcesso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoAcessoDAO {

    private Connection conexao;

    public SessaoAcessoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(SessaoAcesso s) {
        String sql = "INSERT INTO sessaoacesso (id_usuario, tipo_login, data_login) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, s.getIdUsuario());
            stmt.setString(2, s.getTipoLogin());
            stmt.setTimestamp(3, s.getDataLogin());
            stmt.executeUpdate();
            return "Sessão registrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao registrar sessão: " + e.getMessage();
        }
    }

    public List<SessaoAcesso> listar() {
        List<SessaoAcesso> lista = new ArrayList<>();
        String sql = "SELECT * FROM sessaoacesso";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SessaoAcesso s = new SessaoAcesso();
                s.setIdSessao(rs.getInt("id_sessao"));
                s.setIdUsuario(rs.getInt("id_usuario"));
                s.setTipoLogin(rs.getString("tipo_login"));
                s.setDataLogin(rs.getTimestamp("data_login"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar sessões: " + e.getMessage());
        }

        return lista;
    }

    public SessaoAcesso buscar(int id) {
        String sql = "SELECT * FROM sessaoacesso WHERE id_sessao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SessaoAcesso(
                        rs.getInt("id_sessao"),
                        rs.getInt("id_usuario"),
                        rs.getString("tipo_login"),
                        rs.getTimestamp("data_login")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar sessão: " + e.getMessage());
        }

        return null;
    }

    public String deletar(int id) {
        String sql = "DELETE FROM sessaoacesso WHERE id_sessao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Sessão deletada com sucesso!" : "Sessão não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar sessão: " + e.getMessage();
        }
    }
}
