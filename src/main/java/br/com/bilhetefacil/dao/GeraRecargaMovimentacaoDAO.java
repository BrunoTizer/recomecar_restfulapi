package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.GeraRecargaMovimentacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeraRecargaMovimentacaoDAO {

    private Connection conexao;

    public GeraRecargaMovimentacaoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(GeraRecargaMovimentacao grm) {
        String sql = "INSERT INTO geraRecargaMovimentacao (id_recarga, id_movimentacao) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, grm.getIdRecarga());
            stmt.setInt(2, grm.getIdMovimentacao());
            stmt.executeUpdate();
            return "Associação criada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao associar recarga à movimentação: " + e.getMessage();
        }
    }

    public List<GeraRecargaMovimentacao> listar() {
        List<GeraRecargaMovimentacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM geraRecargaMovimentacao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GeraRecargaMovimentacao grm = new GeraRecargaMovimentacao();
                grm.setIdRecarga(rs.getInt("id_recarga"));
                grm.setIdMovimentacao(rs.getInt("id_movimentacao"));
                lista.add(grm);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar associações: " + e.getMessage());
        }

        return lista;
    }

    public String deletar(int idRecarga, int idMovimentacao) {
        String sql = "DELETE FROM geraRecargaMovimentacao WHERE id_recarga = ? AND id_movimentacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idRecarga);
            stmt.setInt(2, idMovimentacao);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Associação removida com sucesso!" : "Associação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao remover associação: " + e.getMessage();
        }
    }
}
