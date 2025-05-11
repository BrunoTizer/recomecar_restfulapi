package br.com.bilhetefacil.main;

import br.com.bilhetefacil.model.Usuario;
import br.com.bilhetefacil.services.UsuarioService;

import java.sql.SQLException;
import java.util.List;

public class TesteListarUsuarios {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UsuarioService service = new UsuarioService();

        List<Usuario> lista = service.listarUsuarios();

        for (Usuario u : lista) {
            System.out.println("ID: " + u.getIdUsuario());
            System.out.println("Nome: " + u.getNome());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Cadastro: " + u.getDataCadastro());
            System.out.println("Status Pagamento: " + u.getStatusPagamentoTaxa());
            System.out.println("-------------------------");
        }
    }
}
