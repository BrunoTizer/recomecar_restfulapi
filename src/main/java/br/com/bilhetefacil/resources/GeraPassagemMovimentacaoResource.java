package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.GeraPassagemMovimentacao;
import br.com.bilhetefacil.services.GeraPassagemMovimentacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/passagens-movimentacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeraPassagemMovimentacaoResource {

    private GeraPassagemMovimentacaoService service;

    public GeraPassagemMovimentacaoResource() throws SQLException, ClassNotFoundException {
        this.service = new GeraPassagemMovimentacaoService();
    }

    @POST
    public Response criar(GeraPassagemMovimentacao gpm) {
        String resultado = service.criar(gpm);
        if (resultado.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.status(Response.Status.CREATED).entity(resultado).build();
    }

    @GET
    public Response listar() {
        List<GeraPassagemMovimentacao> lista = service.listar();
        return Response.ok(lista).build();
    }

    @DELETE
    @Path("/{idPassagem}/{idMovimentacao}")
    public Response deletar(@PathParam("idPassagem") int idPassagem,
                            @PathParam("idMovimentacao") int idMovimentacao) {
        String msg = service.deletar(idPassagem, idMovimentacao);
        return Response.ok(msg).build();
    }
}
