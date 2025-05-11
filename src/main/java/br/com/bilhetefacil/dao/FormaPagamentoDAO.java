package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.model.FormaPagamento;
import br.com.bilhetefacil.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO {

    private Connection conexao;

    public FormaPagamentoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(FormaPagamento forma) {
        String sql = "INSERT INTO formapagamento (descricao) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, forma.getDescricao());
            stmt.executeUpdate();
            return "Forma de pagamento cadastrada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar forma de pagamento: " + e.getMessage();
        }
    }

    public List<FormaPagamento> listar() {
        List<FormaPagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM formapagamento";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FormaPagamento forma = new FormaPagamento();
                forma.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
                forma.setDescricao(rs.getString("descricao"));
                lista.add(forma);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar formas: " + e.getMessage());
        }

        return lista;
    }

    public FormaPagamento buscar(int id) {
        String sql = "SELECT * FROM formapagamento WHERE id_forma_pagamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FormaPagamento(
                        rs.getInt("id_forma_pagamento"),
                        rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar forma: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(FormaPagamento forma) {
        String sql = "UPDATE formapagamento SET descricao = ? WHERE id_forma_pagamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, forma.getDescricao());
            stmt.setInt(2, forma.getIdFormaPagamento());
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Forma atualizada com sucesso!" : "Forma não encontrada.";
        } catch (SQLException e) {
            return "Erro ao atualizar forma: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM formapagamento WHERE id_forma_pagamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Forma deletada com sucesso!" : "Forma não encontrada.";
        } catch (SQLException e) {
            return "Erro ao deletar forma: " + e.getMessage();
        }
    }
}
