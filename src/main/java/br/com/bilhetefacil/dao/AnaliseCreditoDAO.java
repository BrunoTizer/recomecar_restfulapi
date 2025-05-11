package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.AnaliseCredito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnaliseCreditoDAO {

    private Connection conexao;

    public AnaliseCreditoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(AnaliseCredito credito) {
        String sql = "INSERT INTO analisecredito (id_usuario, valor, status, criado_em, expira_em) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, credito.getIdUsuario());
            stmt.setDouble(2, credito.getValor());
            stmt.setString(3, credito.getStatus());
            stmt.setTimestamp(4, credito.getCriadoEm());
            stmt.setTimestamp(5, credito.getExpiraEm());

            stmt.executeUpdate();
            return "Análise de crédito cadastrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar análise de crédito: " + e.getMessage();
        }
    }

    public List<AnaliseCredito> listar() {
        List<AnaliseCredito> lista = new ArrayList<>();
        String sql = "SELECT * FROM analisecredito";

        try(PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AnaliseCredito credito = new AnaliseCredito();
                credito.setIdCredito(rs.getInt("id_credito"));
                credito.setIdUsuario(rs.getInt("id_usuario"));
                credito.setValor(rs.getDouble("valor"));
                credito.setStatus(rs.getString("status"));
                credito.setCriadoEm(rs.getTimestamp("criado_em"));
                credito.setExpiraEm(rs.getTimestamp("expira_em"));
                lista.add(credito);
            }

    }catch(SQLException e) {
            System.out.println("Erro ao listar análises: " + e.getMessage());

        }
        return  lista;
    }

    public AnaliseCredito buscar(int id) {
        String sql = "SELECT * FROM analisecredito WHERE id_credito = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                AnaliseCredito credito = new AnaliseCredito();
                credito.setIdCredito(rs.getInt("id_credito"));
                credito.setIdUsuario(rs.getInt("id_usuario"));
                credito.setValor(rs.getDouble("valor"));
                credito.setStatus(rs.getString("status"));
                credito.setCriadoEm(rs.getTimestamp("criado_em"));
                credito.setExpiraEm(rs.getTimestamp("expira_em"));
                return credito;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar análise: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(AnaliseCredito credito) {
        String sql = "UPDATE analisecredito SET id_usuario = ?, valor = ?, status = ?, criado_em = ?, expira_em = ? " +
                "WHERE id_credito = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, credito.getIdUsuario());
            stmt.setDouble(2, credito.getValor());
            stmt.setString(3, credito.getStatus());
            stmt.setTimestamp(4, credito.getCriadoEm());
            stmt.setTimestamp(5, credito.getExpiraEm());
            stmt.setInt(6, credito.getIdCredito());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Análise atualizada com sucesso!" : "Análise não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar análise: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM analisecredito WHERE id_credito = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Análise deletada com sucesso!" : "Análise não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar análise: " + e.getMessage();
        }
    }

}
