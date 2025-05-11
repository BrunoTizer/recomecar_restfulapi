package br.com.bilhetefacil.main;

import br.com.bilhetefacil.model.Usuario;
import br.com.bilhetefacil.services.UsuarioService;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TesteInserirUsuario {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Usuario objUsuario = new Usuario();

        objUsuario.setNome(texto("Informe o nome do usuário"));
        objUsuario.setEmail(texto("Informe o email do usuário"));
        objUsuario.setSenha(texto("Informe a senha do usuário"));
        objUsuario.setFaceIdHash(texto("Informe o hash do Face ID (opcional)"));
        objUsuario.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        objUsuario.setStatusPagamentoTaxa(texto("Status de pagamento da taxa (S/N)"));

        UsuarioService service = new UsuarioService();
        String resultado = service.cadastrarUsuario(objUsuario);

        System.out.println(resultado);

    }
}
