package br.com.recomecar.services;

import br.com.recomecar.dao.CategoriaDAO;
import br.com.recomecar.model.Categoria;
import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;

import java.sql.SQLException;
import java.util.List;

public class CategoriaService {

    private CategoriaDAO categoriaDAO;

    public CategoriaService() throws SQLException, ClassNotFoundException {
        this.categoriaDAO = new CategoriaDAO();
    }

    public String cadastrarCategoria(Categoria categoria) {
        if (categoria.getIdCategoria() == 0) {
            throw new ValidacaoException("O ID da categoria é obrigatório.");
        }
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O nome da categoria é obrigatório.");
        }
        return categoriaDAO.inserir(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar();
    }

    public Categoria buscarCategoria(int id) {
        Categoria categoria = categoriaDAO.buscar(id);
        if (categoria == null) {
            throw new RegistroNaoEncontradoException("Categoria não encontrada com id: " + id);
        }
        return categoria;
    }

    public String atualizarCategoria(Categoria categoria) {
        if (categoria.getIdCategoria() == 0) {
            throw new ValidacaoException("ID da categoria é obrigatório.");
        }
        return categoriaDAO.atualizar(categoria);
    }

    public String deletarCategoria(int id) {
        return categoriaDAO.deletar(id);
    }
}
