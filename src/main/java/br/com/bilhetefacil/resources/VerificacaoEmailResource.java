package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.VerificacaoEmail;
import br.com.bilhetefacil.services.VerificacaoEmailService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/verificacoes-email")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VerificacaoEmailResource {

    private VerificacaoEmailService service;

    public VerificacaoEmailResource() throws SQLException, ClassNotFoundException {
        this.service = new VerificacaoEmailService();
    }

    @POST
    public Response criar(VerificacaoEmail v) {
        String msg = service.cadastrar(v);
        if (msg.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }

    @GET
    public Response listar() {
        List<VerificacaoEmail> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        VerificacaoEmail v = service.buscar(id);
        if (v != null) {
            return Response.ok(v).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Verificação não encontrada.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, VerificacaoEmail v) {
        v.setIdVerificacao(id);
        String msg = service.atualizar(v);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}
