package br.com.bilhetefacil;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return """
        Projeto: Bilhete Fácil - Sistema de Recarga e Controle de Passagens
        
        Este sistema foi desenvolvido como parte da disciplina de Domain Driven Design com Java.
        Ele oferece uma API RESTful completa com operações CRUD para gerenciar:
        - Usuários
        - Recargas
        - Passagens utilizadas
        - Sessões de acesso
        - Notificações
        - Pagamentos
        - Movimentações financeiras
        - Verificações de e-mail
        - Associações entre entidades via tabelas relacionais
        
        Backend desenvolvido em Java com Quarkus, seguindo boas práticas de separação de camadas:
        Model | DAO | Service | Resource

        Alunos:
        - Bruno Tizer (RM: 559999)
        - Gabriel Dos Santos Souza (RM: 560812)
        - Thomas Henrique Baute (RM: 560649)
        """;
    }
}
