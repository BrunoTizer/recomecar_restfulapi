package br.com.recomecar.resources;

import br.com.recomecar.model.StatusAcompanhamento;
import br.com.recomecar.services.StatusAcompanhamentoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/status-acompanhamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusAcompanhamentoResource {

    private StatusAcompanhamentoService statusAcompanhamentoService;

    public StatusAcompanhamentoResource() throws SQLException, ClassNotFoundException {
        this.statusAcompanhamentoService = new StatusAcompanhamentoService();
    }

    @POST
    public Response criarStatusAcompanhamento(StatusAcompanhamento status) {
        try {
            String msg = statusAcompanhamentoService.cadastrarStatusAcompanhamento(status);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar status de acompanhamento: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarStatusAcompanhamento() {
        try {
            List<StatusAcompanhamento> lista = statusAcompanhamentoService.listarStatusAcompanhamento();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar status de acompanhamento: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarStatusAcompanhamento(@PathParam("id") int id) {
        try {
            StatusAcompanhamento status = statusAcompanhamentoService.buscarStatusAcompanhamento(id);
            if (status != null) {
                return Response.ok(status).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Status de acompanhamento não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar status de acompanhamento: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarStatusAcompanhamento(@PathParam("id") int id, StatusAcompanhamento status) {
        try {
            status.setIdStatusAcompanhamento(id);
            String msg = statusAcompanhamentoService.atualizarStatusAcompanhamento(status);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar status de acompanhamento: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarStatusAcompanhamento(@PathParam("id") int id) {
        try {
            String msg = statusAcompanhamentoService.deletarStatusAcompanhamento(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar status de acompanhamento: " + e.getMessage()).build();
        }
    }
}
