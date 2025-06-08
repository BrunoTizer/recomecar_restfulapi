package br.com.recomecar.dao;

import br.com.recomecar.model.Usuario;
import br.com.recomecar.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public String inserir(Usuario usuario) {
        int idAleatorio = (int) (Math.random() * 9_000_000) + 1_000_000;
        usuario.setIdUsuario(idAleatorio);

        String sql = "INSERT INTO usuarios_recomecar (id_usuario, nome, email, telefone, senha, tipo, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getTipo());
            stmt.setString(7, usuario.getStatus());
            stmt.executeUpdate();
            return "Usuário cadastrado com sucesso!";
        } catch (SQLException | ClassNotFoundException e) {
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios_recomecar";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setStatus(rs.getString("status"));
                lista.add(usuario);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return lista;
    }

    public Usuario buscar(int id) {
        String sql = "SELECT * FROM usuarios_recomecar WHERE id_usuario = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setStatus(rs.getString("status"));
                return usuario;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public Usuario buscarPorEmailESenha(String email, String senha) {
        String sql = "SELECT * FROM usuarios_recomecar WHERE email = ? AND senha = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setStatus(rs.getString("status"));
                return usuario;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios_recomecar SET nome = ?, email = ?, telefone = ?, senha = ?, tipo = ?, status = ? WHERE id_usuario = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTipo());
            stmt.setString(6, usuario.getStatus());
            stmt.setInt(7, usuario.getIdUsuario());

            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário atualizado com sucesso!" : "Usuário não encontrado.";
        } catch (SQLException | ClassNotFoundException e) {
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM usuarios_recomecar WHERE id_usuario = ?";
        try (
                Connection conexao = new ConexaoFactory().conexao();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário deletado com sucesso!" : "Usuário não encontrado.";
        } catch (SQLException | ClassNotFoundException e) {
            return "Erro ao deletar usuário: " + e.getMessage();
        }
    }
}
