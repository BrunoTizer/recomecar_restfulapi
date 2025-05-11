package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.PassagemUtilizada;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassagemUtilizadaDAO {

    private Connection conexao;

    public PassagemUtilizadaDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(PassagemUtilizada p) {
        String sql = "INSERT INTO passagemutilizada (id_usuario, data_hora, valor, localizacao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdUsuario());
            stmt.setTimestamp(2, p.getDataHora());
            stmt.setDouble(3, p.getValor());
            stmt.setString(4, p.getLocalizacao());
            stmt.executeUpdate();
            return "Passagem registrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao registrar passagem: " + e.getMessage();
        }
    }

    public List<PassagemUtilizada> listar() {
        List<PassagemUtilizada> lista = new ArrayList<>();
        String sql = "SELECT * FROM passagemutilizada";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PassagemUtilizada p = new PassagemUtilizada();
                p.setIdPassagem(rs.getInt("id_passagem"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setDataHora(rs.getTimestamp("data_hora"));
                p.setValor(rs.getDouble("valor"));
                p.setLocalizacao(rs.getString("localizacao"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar passagens: " + e.getMessage());
        }

        return lista;
    }

    public PassagemUtilizada buscar(int id) {
        String sql = "SELECT * FROM passagemutilizada WHERE id_passagem = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PassagemUtilizada(
                        rs.getInt("id_passagem"),
                        rs.getInt("id_usuario"),
                        rs.getTimestamp("data_hora"),
                        rs.getDouble("valor"),
                        rs.getString("localizacao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar passagem: " + e.getMessage());
        }

        return null;
    }

    public String atualizar(PassagemUtilizada p) {
        String sql = "UPDATE passagemutilizada SET id_usuario = ?, data_hora = ?, valor = ?, localizacao = ? WHERE id_passagem = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdUsuario());
            stmt.setTimestamp(2, p.getDataHora());
            stmt.setDouble(3, p.getValor());
            stmt.setString(4, p.getLocalizacao());
            stmt.setInt(5, p.getIdPassagem());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Passagem atualizada com sucesso!" : "Passagem não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar passagem: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM passagemutilizada WHERE id_passagem = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Passagem deletada com sucesso!" : "Passagem não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar passagem: " + e.getMessage();
        }
    }
}
