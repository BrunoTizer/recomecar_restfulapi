package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.RecargaDAO;
import br.com.bilhetefacil.model.Recarga;

import java.sql.SQLException;
import java.util.List;

public class RecargaService {

    private RecargaDAO dao;

    public RecargaService() throws SQLException, ClassNotFoundException {
        this.dao = new RecargaDAO();
    }

    public String cadastrar(Recarga r) {
        if (r.getIdUsuario() <= 0) return "Erro: ID do usuário é obrigatório.";
        if (r.getValor() <= 0) return "Erro: valor deve ser maior que zero.";
        if (r.getDataRecarga() == null) return "Erro: data da recarga é obrigatória.";
        return dao.inserir(r);
    }

    public List<Recarga> listar() {
        return dao.listar();
    }

    public Recarga buscar(int id) {
        return dao.buscar(id);
    }

    public String atualizar(Recarga r) {
        if (r.getIdRecarga() <= 0) return "Erro: ID da recarga é obrigatório.";
        return dao.atualizar(r);
    }

    public String deletar(int id) {
        return dao.deletar(id);
    }
}
