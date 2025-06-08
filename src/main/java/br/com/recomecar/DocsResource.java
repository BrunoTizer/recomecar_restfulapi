package br.com.recomecar;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/docs")
public class DocsResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String docs() {
        return """
        Projeto: Recomeçar+ — Reconstruindo vidas, conectando solidariedade

        --------------------------------------------------------------
        Descrição do Sistema:
        --------------------------------------------------------------
        O Recomeçar+ é uma plataforma digital criada para conectar vítimas de desastres naturais a voluntários e doadores, de maneira simples, rápida e gratuita.
        
        Funcionalidades:
        - Cadastro ágil de vítimas e suas necessidades (alimentos, abrigo, higiene, medicamentos, roupas, etc.)
        - Algoritmo de prioridade automática para destacar pedidos urgentes
        - Acompanhamento em tempo real e notificações de atualização de status
        - Voluntários podem oferecer diferentes formas de ajuda (doações, hospedagem, transporte, serviços de saúde)
        - Correspondência inteligente entre ofertas e pedidos para maximizar o impacto social
        - Histórico de contribuições, painel pessoal de voluntário e certificados digitais
        - (Futuro) Alertas via SMS e monitoramento por drones de áreas críticas

        --------------------------------------------------------------
        Arquitetura da Solução (DDD e Camadas)
        --------------------------------------------------------------
        • Model    — Entidades Java (Usuário, Categoria, PedidoAjuda, OfertaAjuda, Match, Acompanhamento, StatusPedido, StatusMatch, StatusAcompanhamento)
        • DAO      — Camada de acesso a dados (CRUD para cada entidade)
        • Service  — Validação, regra de negócio, orquestração e exceções customizadas
        • BO       — Business Object: encapsula regras específicas, exemplo: validação de prioridade, tipo de usuário, filtragem de descrição
        • Resource — API RESTful (Jakarta REST/JAX-RS) com endpoints para cada entidade
        • Banco de Dados Oracle — modelo relacional, tabelas normalizadas, FKs com ON DELETE CASCADE

        --------------------------------------------------------------
        Principais Endpoints REST
        --------------------------------------------------------------
        - /usuarios                [GET, POST, PUT, DELETE, POST /login]
        - /categorias              [GET, POST, PUT, DELETE]
        - /status-pedido           [GET, POST, PUT, DELETE]
        - /pedidos-ajuda           [GET, POST, PUT, DELETE]
        - /status-acompanhamento   [GET, POST, PUT, DELETE]
        - /ofertas-ajuda           [GET, POST, PUT, DELETE]
        - /status-match            [GET, POST, PUT, DELETE]
        - /matches                 [GET, POST, PUT, DELETE]
        - /acompanhamentos         [GET, POST, PUT, DELETE]

        --------------------------------------------------------------
        Boas práticas implementadas:
        --------------------------------------------------------------
        - Organização em camadas (Model, DAO, Service, BO, Resource)
        - DDL com FKs ON DELETE CASCADE (garantindo integridade e facilidade em operações de exclusão)
        - Exceções customizadas para validação e registro não encontrado
        - Código limpo, comentado e orientado a objetos

        --------------------------------------------------------------
        Equipe de Desenvolvimento:
        --------------------------------------------------------------
        - Bruno Tizer (RM: 559999)
        - Gabriel Dos Santos Souza (RM: 560812)
        - Thomas Henrique Baute (RM: 560649)

        # Recomeçar+ — Porque todo recomeço merece uma ajuda rápida, segura e humana.

        Saiba mais:
        "Quando um desastre natural atinge uma comunidade, cada minuto conta. O Recomeçar+ é a ponte digital entre quem precisa de ajuda urgente e quem quer estender a mão — tudo em uma plataforma simples, rápida e gratuita."
        """;
    }
}
