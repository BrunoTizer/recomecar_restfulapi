package br.com.recomecar.dao;

import br.com.recomecar.model.Categoria;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public String inserir(Categoria categoria) {
        int idAleatorio = (int) (Math.random() * 900_000) + 100_000;
        categoria.setIdCategoria(idAleatorio);

        String sql = "INSERT INTO categorias_recomecar (id_categoria, nome, descricao) VALUES (?, ?, ?)";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setString(2, categoria.getNome());
            stmt.setString(3, categoria.getDescricao());
            stmt.executeUpdate();
            return "Categoria cadastrada com sucesso!";
        } catch (SQLException | ClassNotFoundException e) {
            return "Erro ao cadastrar categoria: " + e.getMessage();
        }
    }

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias_recomecar";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                lista.add(categoria);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        return lista;
    }

    public Categoria buscar(int id) {
        String sql = "SELECT * FROM categorias_recomecar WHERE id_categoria = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                return categoria;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar categoria: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(Categoria categoria) {
        String sql = "UPDATE categorias_recomecar SET nome = ?, descricao = ? WHERE id_categoria = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getIdCategoria());
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Categoria atualizada com sucesso!" : "Categoria não encontrada.";
        } catch (SQLException | ClassNotFoundException e) {
            return "Erro ao atualizar categoria: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM categorias_recomecar WHERE id_categoria = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Categoria deletada com sucesso!" : "Categoria não encontrada.";
        } catch (SQLException | ClassNotFoundException e) {
            return "Erro ao deletar categoria: " + e.getMessage();
        }
    }
}
