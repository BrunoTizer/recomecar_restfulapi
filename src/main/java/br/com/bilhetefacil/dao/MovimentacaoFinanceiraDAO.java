package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.MovimentacaoFinanceira;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoFinanceiraDAO {

    private Connection conexao;

    public MovimentacaoFinanceiraDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(MovimentacaoFinanceira m) {
        String sql = "INSERT INTO movimentacaofinanceira (valor, descricao, data, id_forma_pagamento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, m.getValor());
            stmt.setString(2, m.getDescricao());
            stmt.setTimestamp(3, m.getData());
            stmt.setInt(4, m.getIdFormaPagamento());

            stmt.executeUpdate();
            return "Movimentação financeira cadastrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar movimentação: " + e.getMessage();
        }
    }

    public List<MovimentacaoFinanceira> listar() {
        List<MovimentacaoFinanceira> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimentacaofinanceira";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MovimentacaoFinanceira m = new MovimentacaoFinanceira();
                m.setIdMovimentacao(rs.getInt("id_movimentacao"));
                m.setValor(rs.getDouble("valor"));
                m.setDescricao(rs.getString("descricao"));
                m.setData(rs.getTimestamp("data"));
                m.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar movimentações: " + e.getMessage());
        }

        return lista;
    }

    public MovimentacaoFinanceira buscar(int id) {
        String sql = "SELECT * FROM movimentacaofinanceira WHERE id_movimentacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MovimentacaoFinanceira(
                        rs.getInt("id_movimentacao"),
                        rs.getDouble("valor"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data"),
                        rs.getInt("id_forma_pagamento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar movimentação: " + e.getMessage());
        }

        return null;
    }

    public String atualizar(MovimentacaoFinanceira m) {
        String sql = "UPDATE movimentacaofinanceira SET valor = ?, descricao = ?, data = ?, id_forma_pagamento = ? WHERE id_movimentacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, m.getValor());
            stmt.setString(2, m.getDescricao());
            stmt.setTimestamp(3, m.getData());
            stmt.setInt(4, m.getIdFormaPagamento());
            stmt.setInt(5, m.getIdMovimentacao());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Movimentação atualizada com sucesso!" : "Movimentação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar movimentação: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM movimentacaofinanceira WHERE id_movimentacao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Movimentação deletada com sucesso!" : "Movimentação não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar movimentação: " + e.getMessage();
        }
    }
}
