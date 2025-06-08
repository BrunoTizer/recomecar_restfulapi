package br.com.recomecar.resources;

import br.com.recomecar.model.StatusMatch;
import br.com.recomecar.services.StatusMatchService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/status-match")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusMatchResource {

    private StatusMatchService statusMatchService;

    public StatusMatchResource() throws SQLException, ClassNotFoundException {
        this.statusMatchService = new StatusMatchService();
    }

    @POST
    public Response criarStatusMatch(StatusMatch statusMatch) {
        try {
            String msg = statusMatchService.cadastrarStatusMatch(statusMatch);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar status do match: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarStatusMatch() {
        try {
            List<StatusMatch> lista = statusMatchService.listarStatusMatch();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar status do match: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarStatusMatch(@PathParam("id") int id) {
        try {
            StatusMatch statusMatch = statusMatchService.buscarStatusMatch(id);
            if (statusMatch != null) {
                return Response.ok(statusMatch).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Status do match não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar status do match: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarStatusMatch(@PathParam("id") int id, StatusMatch statusMatch) {
        try {
            statusMatch.setIdStatusMatch(id);
            String msg = statusMatchService.atualizarStatusMatch(statusMatch);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar status do match: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarStatusMatch(@PathParam("id") int id) {
        try {
            String msg = statusMatchService.deletarStatusMatch(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar status do match: " + e.getMessage()).build();
        }
    }
}
