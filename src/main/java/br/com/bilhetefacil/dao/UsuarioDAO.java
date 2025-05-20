package br.com.bilhetefacil.dao;

import br.com.bilhetefacil.model.Usuario;
import br.com.bilhetefacil.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }


    public String inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, face_id_hash, data_cadastro, status_pagamento_taxa) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getFaceIdHash());
            stmt.setTimestamp(5, usuario.getDataCadastro());
            stmt.setString(6, usuario.getStatusPagamentoTaxa() );

            stmt.executeUpdate();
            return "Usuário cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFaceIdHash(rs.getString("face_id_hash"));
                usuario.setDataCadastro(rs.getTimestamp("data_cadastro"));
                usuario.setStatusPagamentoTaxa(rs.getString("status_pagamento_taxa"));
                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }

        return lista;
    }

    public Usuario buscar(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFaceIdHash(rs.getString("face_id_hash"));
                usuario.setDataCadastro(rs.getTimestamp("data_cadastro"));
                usuario.setStatusPagamentoTaxa(rs.getString("status_pagamento_taxa"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public String atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, face_id_hash = ?, status_pagamento_taxa = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getFaceIdHash());
            stmt.setString(5, usuario.getStatusPagamentoTaxa());
            stmt.setInt(6, usuario.getIdUsuario());

            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário atualizado com sucesso!" : "Usuário não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public String atualizarFaceId(int idUsuario, String faceIdHash) {
        String sql = "UPDATE usuario SET face_id_hash = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, faceIdHash);
            stmt.setInt(2, idUsuario);

            int linhas = stmt.executeUpdate();
            return (linhas > 0) ? "Face ID atualizado com sucesso!" : "Usuário não encontrado.";
        } catch (SQLException e) {
            return "Erro ao atualizar Face ID: " + e.getMessage();
        }
    }



    public String deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário deletado com sucesso!" : "Usuário não encontrado.";
        } catch (SQLException e) {
            return "Erro ao deletar usuário: " + e.getMessage();
        }
    }


}
