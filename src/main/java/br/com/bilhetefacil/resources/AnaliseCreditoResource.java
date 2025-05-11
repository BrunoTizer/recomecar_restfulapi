package br.com.bilhetefacil.resources;


import br.com.bilhetefacil.model.AnaliseCredito;
import br.com.bilhetefacil.services.AnaliseCreditoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/analise-credito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnaliseCreditoResource {

    private AnaliseCreditoService service;

    private AnaliseCreditoResource() throws SQLException, ClassNotFoundException {
        this.service = new AnaliseCreditoService();
    }

    @POST
    public Response criar(AnaliseCredito credito) {
        String resultado = service.cadastrarCredito(credito);
        if (resultado.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.status(Response.Status.CREATED).entity(resultado).build();
    }

    @GET
    public Response listar() {
        List<AnaliseCredito> lista = service.listarCredito();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        AnaliseCredito credito = service.buscarCredito(id);
        if (credito != null) {
            return Response.ok(credito).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Análise não encontrada.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, AnaliseCredito credito) {
        credito.setIdCredito(id);
        String msg = service.atualizarCredito(credito);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletarCredito(id);
        return Response.ok(msg).build();
    }
}
