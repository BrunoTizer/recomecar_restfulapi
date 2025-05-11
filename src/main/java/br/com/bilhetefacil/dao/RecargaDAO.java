package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.Recarga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecargaDAO {

    private Connection conexao;

    public RecargaDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(Recarga r) {
        String sql = "INSERT INTO recarga (id_usuario, valor, data_recarga) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdUsuario());
            stmt.setDouble(2, r.getValor());
            stmt.setTimestamp(3, r.getDataRecarga());
            stmt.executeUpdate();
            return "Recarga registrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao registrar recarga: " + e.getMessage();
        }
    }

    public List<Recarga> listar() {
        List<Recarga> lista = new ArrayList<>();
        String sql = "SELECT * FROM recarga";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Recarga r = new Recarga();
                r.setIdRecarga(rs.getInt("id_recarga"));
                r.setIdUsuario(rs.getInt("id_usuario"));
                r.setValor(rs.getDouble("valor"));
                r.setDataRecarga(rs.getTimestamp("data_recarga"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar recargas: " + e.getMessage());
        }

        return lista;
    }

    public Recarga buscar(int id) {
        String sql = "SELECT * FROM recarga WHERE id_recarga = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Recarga(
                        rs.getInt("id_recarga"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("valor"),
                        rs.getTimestamp("data_recarga")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar recarga: " + e.getMessage());
        }

        return null;
    }

    public String atualizar(Recarga r) {
        String sql = "UPDATE recarga SET id_usuario = ?, valor = ?, data_recarga = ? WHERE id_recarga = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdUsuario());
            stmt.setDouble(2, r.getValor());
            stmt.setTimestamp(3, r.getDataRecarga());
            stmt.setInt(4, r.getIdRecarga());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Recarga atualizada com sucesso!" : "Recarga não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar recarga: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM recarga WHERE id_recarga = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Recarga deletada com sucesso!" : "Recarga não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar recarga: " + e.getMessage();
        }
    }
}
