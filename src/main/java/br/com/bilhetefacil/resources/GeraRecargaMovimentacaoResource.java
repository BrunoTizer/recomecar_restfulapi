package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.GeraRecargaMovimentacao;
import br.com.bilhetefacil.services.GeraRecargaMovimentacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/recargas-movimentacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeraRecargaMovimentacaoResource {

    private GeraRecargaMovimentacaoService service;

    public GeraRecargaMovimentacaoResource() throws SQLException, ClassNotFoundException {
        this.service = new GeraRecargaMovimentacaoService();
    }

    @POST
    public Response criar(GeraRecargaMovimentacao grm) {
        String resultado = service.criar(grm);
        if (resultado.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.status(Response.Status.CREATED).entity(resultado).build();
    }

    @GET
    public Response listar() {
        List<GeraRecargaMovimentacao> lista = service.listar();
        return Response.ok(lista).build();
    }

    @DELETE
    @Path("/{idRecarga}/{idMovimentacao}")
    public Response deletar(@PathParam("idRecarga") int idRecarga,
                            @PathParam("idMovimentacao") int idMovimentacao) {
        String msg = service.deletar(idRecarga, idMovimentacao);
        return Response.ok(msg).build();
    }
}
