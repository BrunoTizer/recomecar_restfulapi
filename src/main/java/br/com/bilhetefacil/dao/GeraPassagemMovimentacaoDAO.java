package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.GeraPassagemMovimentacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeraPassagemMovimentacaoDAO {

    private Connection conexao;

    public GeraPassagemMovimentacaoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(GeraPassagemMovimentacao gpm) {
        String sql = "INSERT INTO geraPassagemMovimentacao (id_passagem, id_movimentacao) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, gpm.getIdPassagem());
            stmt.setInt(2, gpm.getIdMovimentacao());
            stmt.executeUpdate();
            return "Associação criada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao associar passagem à movimentação: " + e.getMessage();
        }
    }

    public List<GeraPassagemMovimentacao> listar() {
        List<GeraPassagemMovimentacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM geraPassagemMovimentacao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GeraPassagemMovimentacao gpm = new GeraPassagemMovimentacao();
                gpm.setIdPassagem(rs.getInt("id_passagem"));
                gpm.setIdMovimentacao(rs.getInt("id_movimentacao"));
                lista.add(gpm);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar associações: " + e.getMessage());
        }

        return lista;
    }

    public String deletar(int idPassagem, int idMovimentacao) {
        String sql = "DELETE FROM geraPassagemMovimentacao WHERE id_passagem = ? AND id_movimentacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPassagem);
            stmt.setInt(2, idMovimentacao);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Associação removida com sucesso!" : "Associação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao remover associação: " + e.getMessage();
        }
    }
}
