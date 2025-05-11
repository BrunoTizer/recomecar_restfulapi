package br.com.bilhetefacil.resources;

import br.com.bilhetefacil.model.FormaPagamento;
import br.com.bilhetefacil.services.FormaPagamentoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/formas-pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class FormaPagamentoResource {

    private FormaPagamentoService service;

    public FormaPagamentoResource() throws SQLException, ClassNotFoundException {
        this.service = new FormaPagamentoService();
    }

    @POST
    public Response criar(FormaPagamento forma) {
        String resultado = service.cadastrar(forma);
        if (resultado.startsWith("Erro")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.status(Response.Status.CREATED).entity(resultado).build();
    }

    @GET
    public Response listar() {
        List<FormaPagamento> lista = service.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        FormaPagamento forma = service.buscar(id);
        if (forma != null) {
            return Response.ok(forma).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Forma de pagamento não encontrada.").build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, FormaPagamento forma) {
        forma.setIdFormaPagamento(id);
        String msg = service.atualizar(forma);
        return Response.ok(msg).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        String msg = service.deletar(id);
        return Response.ok(msg).build();
    }
}