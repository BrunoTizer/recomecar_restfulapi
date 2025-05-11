package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.Recarga;
import br.com.bilhetefacil.services.RecargaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/recargas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecargaResource {

    private RecargaService service;

    public RecargaResource() throws SQLException, ClassNotFoundException {
        this.service = new RecargaService();
    }

    @POST
    public Response criar(Recarga r) {
        String msg = service.cadastrar(r);
        if (msg.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }

    @GET
    public Response listar() {
        List<Recarga> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        Recarga r = service.buscar(id);
        if (r != null) {
            return Response.ok(r).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Recarga não encontrada.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Recarga r) {
        r.setIdRecarga(id);
        String msg = service.atualizar(r);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}
