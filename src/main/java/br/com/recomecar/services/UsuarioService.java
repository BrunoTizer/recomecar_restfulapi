package br.com.recomecar.services;

import br.com.recomecar.bo.UsuarioBO;
import br.com.recomecar.dao.UsuarioDAO;
import br.com.recomecar.model.Usuario;

import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private final UsuarioBO usuarioBO;
    private UsuarioDAO usuarioDAO;

    public UsuarioService() throws SQLException, ClassNotFoundException {
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioBO = new UsuarioBO();
    }

    public String cadastrarUsuario(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome é obrigatório.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new ValidacaoException("Email inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 4) {
            throw new ValidacaoException("A senha deve ter pelo menos 4 caracteres.");
        }
        usuarioBO.validarTipoUsuario(usuario);

        if (usuario.getStatus() == null || usuario.getStatus().isEmpty()) {
            usuario.setStatus("ativo");
        }
        return usuarioDAO.inserir(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    public Usuario buscarUsuario(int id) {
        Usuario usuario = usuarioDAO.buscar(id);
        if (usuario == null) {
            throw new RegistroNaoEncontradoException("Usuário não encontrado com id: " + id);
        }
        return usuario;
    }

    public Usuario login(String email, String senha) {
        if (email == null || senha == null) {
            throw new ValidacaoException("Email e senha são obrigatórios.");
        }
        Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);
        if (usuario == null) {
            throw new RegistroNaoEncontradoException("Usuário ou senha inválidos.");
        }
        return usuario;
    }


    public String atualizarUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() == 0) {
            throw new ValidacaoException("ID do usuário é obrigatório.");
        }
        return usuarioDAO.atualizar(usuario);
    }

    public String deletarUsuario(int id) {
        return usuarioDAO.deletar(id);
    }
}
