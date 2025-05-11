package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.conexoes.ConexaoFactory;
import br.com.bilhetefacil.model.PagamentoTaxa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoTaxaDAO {

    private Connection conexao;

    public PagamentoTaxaDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public String inserir(PagamentoTaxa p) {
        String sql = "INSERT INTO pagamentotaxa (id_usuario, valor, data_pagamento, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdUsuario());
            stmt.setDouble(2, p.getValor());
            stmt.setTimestamp(3, p.getDataPagamento());
            stmt.setString(4, p.getStatus());
            stmt.executeUpdate();
            return "Pagamento cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar pagamento: " + e.getMessage();
        }
    }

    public List<PagamentoTaxa> listar() {
        List<PagamentoTaxa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagamentotaxa";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PagamentoTaxa p = new PagamentoTaxa();
                p.setIdPagamento(rs.getInt("id_pagamento"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setValor(rs.getDouble("valor"));
                p.setDataPagamento(rs.getTimestamp("data_pagamento"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }

        return lista;
    }

    public PagamentoTaxa buscar(int id) {
        String sql = "SELECT * FROM pagamentotaxa WHERE id_pagamento = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PagamentoTaxa(
                        rs.getInt("id_pagamento"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("valor"),
                        rs.getTimestamp("data_pagamento"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pagamento: " + e.getMessage());
        }

        return null;
    }

    public String atualizar(PagamentoTaxa p) {
        String sql = "UPDATE pagamentotaxa SET id_usuario = ?, valor = ?, data_pagamento = ?, status = ? WHERE id_pagamento = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdUsuario());
            stmt.setDouble(2, p.getValor());
            stmt.setTimestamp(3, p.getDataPagamento());
            stmt.setString(4, p.getStatus());
            stmt.setInt(5, p.getIdPagamento());

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Pagamento atualizado com sucesso!" : "Pagamento não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar pagamento: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM pagamentotaxa WHERE id_pagamento = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Pagamento deletado com sucesso!" : "Pagamento não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar pagamento: " + e.getMessage();
        }
    }
}
