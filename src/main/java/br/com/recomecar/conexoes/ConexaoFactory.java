package br.com.recomecar.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    // metodo de conexão com o banco de dados
    public Connection conexao() throws ClassNotFoundException, SQLException {

        // Driver oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Retornar conexão
        return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
              "rm559999", "Fiap#2025");
    }
}
