package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.MovimentacaoFinanceira;
import br.com.bilhetefacil.services.MovimentacaoFinanceiraService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/movimentacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimentacaoFinanceiraResource {

    private MovimentacaoFinanceiraService service;

    public MovimentacaoFinanceiraResource() throws SQLException, ClassNotFoundException {
        this.service = new MovimentacaoFinanceiraService();
    }

    @POST
    public Response criar(MovimentacaoFinanceira m) {
        String msg = service.cadastrar(m);
        if (msg.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }

    @GET
    public Response listar() {
        List<MovimentacaoFinanceira> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        MovimentacaoFinanceira m = service.buscar(id);
        if (m != null) {
            return Response.ok(m).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Movimentação não encontrada.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, MovimentacaoFinanceira m) {
        m.setIdMovimentacao(id);
        String msg = service.atualizar(m);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}
