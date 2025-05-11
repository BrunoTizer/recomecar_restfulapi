package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.GeraPagamentoMovimentacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeraPagamentoMovimentacaoDAO {

    private Connection conexao;

    public GeraPagamentoMovimentacaoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(GeraPagamentoMovimentacao gpm) {
        String sql = "INSERT INTO geraPagamentomovimentacao (id_pagamento, id_movimentacao) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, gpm.getIdPagamento());
            stmt.setInt(2, gpm.getIdMovimentacao());
            stmt.executeUpdate();
            return "Associação criada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao associar pagamento à movimentação: " + e.getMessage();
        }
    }

    public List<GeraPagamentoMovimentacao> listar() {
        List<GeraPagamentoMovimentacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM geraPagamentomovimentacao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GeraPagamentoMovimentacao gpm = new GeraPagamentoMovimentacao();
                gpm.setIdPagamento(rs.getInt("id_pagamento"));
                gpm.setIdMovimentacao(rs.getInt("id_movimentacao"));
                lista.add(gpm);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar associações: " + e.getMessage());
        }

        return lista;
    }

    public String deletar(int idPagamento, int idMovimentacao) {
        String sql = "DELETE FROM geraPagamentomovimentacao WHERE id_pagamento = ? AND id_movimentacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPagamento);
            stmt.setInt(2, idMovimentacao);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Associação removida com sucesso!" : "Associação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao remover associação: " + e.getMessage();
        }
    }
}
