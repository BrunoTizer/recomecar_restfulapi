package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.PagamentoTaxa;
import br.com.bilhetefacil.services.PagamentoTaxaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoTaxaResource {

    private PagamentoTaxaService service;

    public PagamentoTaxaResource() throws SQLException, ClassNotFoundException {
        this.service = new PagamentoTaxaService();
    }

    @POST
    public Response criar(PagamentoTaxa p) {
        String msg = service.cadastrar(p);
        if (msg.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }

    @GET
    public Response listar() {
        List<PagamentoTaxa> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        PagamentoTaxa p = service.buscar(id);
        if (p != null) {
            return Response.ok(p).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Pagamento não encontrado.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, PagamentoTaxa p) {
        p.setIdPagamento(id);
        String msg = service.atualizar(p);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}
