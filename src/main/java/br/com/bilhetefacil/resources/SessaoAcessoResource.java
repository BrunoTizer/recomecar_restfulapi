package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.SessaoAcesso;
import br.com.bilhetefacil.services.SessaoAcessoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/sessoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessaoAcessoResource {

    private SessaoAcessoService service;

    public SessaoAcessoResource() throws SQLException, ClassNotFoundException {
        this.service = new SessaoAcessoService();
    }

    @POST
    public Response criar(SessaoAcesso s) {
        String msg = service.cadastrar(s);
        if (msg.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }

    @GET
    public Response listar() {
        List<SessaoAcesso> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        SessaoAcesso s = service.buscar(id);
        if (s != null) {
            return Response.ok(s).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Sessão não encontrada.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}
