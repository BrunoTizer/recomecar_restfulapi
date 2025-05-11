package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.Notificacao;
import br.com.bilhetefacil.services.NotificacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/notificacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificacaoResource {

    private NotificacaoService service;

    public NotificacaoResource() throws SQLException, ClassNotFoundException {
        this.service = new NotificacaoService();
    }

    @POST
    public Response criar(Notificacao n) {
        String msg = service.cadastrar(n);
        if (msg.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }

    @GET
    public Response listar() {
        List<Notificacao> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        Notificacao n = service.buscar(id);
        if (n != null) {
            return Response.ok(n).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Notificação não encontrada.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Notificacao n) {
        n.setIdNotificacao(id);
        String msg = service.atualizar(n);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}
