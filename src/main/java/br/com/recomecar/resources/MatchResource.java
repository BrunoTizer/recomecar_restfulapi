package br.com.recomecar.resources;

import br.com.recomecar.model.Match;
import br.com.recomecar.services.MatchService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/matches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatchResource {

    private MatchService matchService;

    public MatchResource() throws SQLException, ClassNotFoundException {
        this.matchService = new MatchService();
    }

    @POST
    public Response criarMatch(Match match) {
        try {
            String msg = matchService.cadastrarMatch(match);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar match: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarMatches() {
        try {
            List<Match> lista = matchService.listarMatches();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar matches: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarMatch(@PathParam("id") int id) {
        try {
            Match match = matchService.buscarMatch(id);
            if (match != null) {
                return Response.ok(match).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Match não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar match: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarMatch(@PathParam("id") int id, Match match) {
        try {
            match.setId(id);
            String msg = matchService.atualizarMatch(match);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar match: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarMatch(@PathParam("id") int id) {
        try {
            String msg = matchService.deletarMatch(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar match: " + e.getMessage()).build();
        }
    }
}
