package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.GeraPagamentoMovimentacao;
import br.com.bilhetefacil.services.GeraPagamentoMovimentacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/pagamentos-movimentacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeraPagamentoMovimentacaoResource {

    private GeraPagamentoMovimentacaoService service;

    public GeraPagamentoMovimentacaoResource() throws SQLException, ClassNotFoundException {
        this.service = new GeraPagamentoMovimentacaoService();
    }

    @POST
    public Response criar(GeraPagamentoMovimentacao gpm) {
        String resultado = service.criar(gpm);
        if (resultado.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.status(Response.Status.CREATED).entity(resultado).build();
    }

    @GET
    public Response listar() {
        List<GeraPagamentoMovimentacao> lista = service.listar();
        return Response.ok(lista).build();
    }

    @DELETE
    @Path("/{idPagamento}/{idMovimentacao}")
    public Response deletar(@PathParam("idPagamento") int idPagamento,
                            @PathParam("idMovimentacao") int idMovimentacao) {
        String msg = service.deletar(idPagamento, idMovimentacao);
        return Response.ok(msg).build();
    }
}
