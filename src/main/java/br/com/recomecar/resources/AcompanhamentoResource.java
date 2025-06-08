package br.com.recomecar.resources;

import br.com.recomecar.model.Acompanhamento;
import br.com.recomecar.services.AcompanhamentoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/acompanhamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcompanhamentoResource {

    private AcompanhamentoService acompanhamentoService;

    public AcompanhamentoResource() throws SQLException, ClassNotFoundException {
        this.acompanhamentoService = new AcompanhamentoService();
    }

    @POST
    public Response criarAcompanhamento(Acompanhamento acompanhamento) {
        try {
            String msg = acompanhamentoService.cadastrarAcompanhamento(acompanhamento);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar acompanhamento: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarAcompanhamentos() {
        try {
            List<Acompanhamento> lista = acompanhamentoService.listarAcompanhamentos();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar acompanhamentos: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarAcompanhamento(@PathParam("id") int id) {
        try {
            Acompanhamento acompanhamento = acompanhamentoService.buscarAcompanhamento(id);
            if (acompanhamento != null) {
                return Response.ok(acompanhamento).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Acompanhamento não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar acompanhamento: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarAcompanhamento(@PathParam("id") int id, Acompanhamento acompanhamento) {
        try {
            acompanhamento.setId(id);
            String msg = acompanhamentoService.atualizarAcompanhamento(acompanhamento);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar acompanhamento: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAcompanhamento(@PathParam("id") int id) {
        try {
            String msg = acompanhamentoService.deletarAcompanhamento(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar acompanhamento: " + e.getMessage()).build();
        }
    }
}
