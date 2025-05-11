package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.Notificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {

    private Connection conexao;

    public NotificacaoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(Notificacao n) {
        String sql = "INSERT INTO notificacao (id_usuario, mensagem, tipo, visualizado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, n.getIdUsuario());
            stmt.setString(2, n.getMensagem());
            stmt.setString(3, n.getTipo());
            stmt.setString(4, String.valueOf(n.getVisualizado()));
            stmt.executeUpdate();
            return "Notificação cadastrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar notificação: " + e.getMessage();
        }
    }

    public List<Notificacao> listar() {
        List<Notificacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM notificacao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Notificacao n = new Notificacao();
                n.setIdNotificacao(rs.getInt("id_notificacao"));
                n.setIdUsuario(rs.getInt("id_usuario"));
                n.setMensagem(rs.getString("mensagem"));
                n.setTipo(rs.getString("tipo"));
                n.setVisualizado(rs.getString("visualizado").charAt(0));
                lista.add(n);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar notificações: " + e.getMessage());
        }

        return lista;
    }

    public Notificacao buscar(int id) {
        String sql = "SELECT * FROM notificacao WHERE id_notificacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Notificacao(
                        rs.getInt("id_notificacao"),
                        rs.getInt("id_usuario"),
                        rs.getString("mensagem"),
                        rs.getString("tipo"),
                        rs.getString("visualizado").charAt(0)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar notificação: " + e.getMessage());
        }

        return null;
    }

    public String atualizar(Notificacao n) {
        String sql = "UPDATE notificacao SET id_usuario = ?, mensagem = ?, tipo = ?, visualizado = ? WHERE id_notificacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, n.getIdUsuario());
            stmt.setString(2, n.getMensagem());
            stmt.setString(3, n.getTipo());
            stmt.setString(4, String.valueOf(n.getVisualizado()));
            stmt.setInt(5, n.getIdNotificacao());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Notificação atualizada com sucesso!" : "Notificação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar notificação: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM notificacao WHERE id_notificacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Notificação deletada com sucesso!" : "Notificação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar notificação: " + e.getMessage();
        }
    }
}
