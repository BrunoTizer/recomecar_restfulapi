package br.com.recomecar.resources;

import br.com.recomecar.model.OfertaAjuda;
import br.com.recomecar.services.OfertaAjudaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/ofertas-ajuda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OfertaAjudaResource {

    private OfertaAjudaService ofertaAjudaService;

    public OfertaAjudaResource() throws SQLException, ClassNotFoundException {
        this.ofertaAjudaService = new OfertaAjudaService();
    }

    @POST
    public Response criarOfertaAjuda(OfertaAjuda oferta) {
        try {
            String msg = ofertaAjudaService.cadastrarOfertaAjuda(oferta);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar oferta de ajuda: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarOfertasAjuda() {
        try {
            List<OfertaAjuda> lista = ofertaAjudaService.listarOfertasAjuda();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar ofertas de ajuda: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarOfertaAjuda(@PathParam("id") int id) {
        try {
            OfertaAjuda oferta = ofertaAjudaService.buscarOfertaAjuda(id);
            if (oferta != null) {
                return Response.ok(oferta).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Oferta de ajuda não encontrada.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar oferta de ajuda: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarOfertaAjuda(@PathParam("id") int id, OfertaAjuda oferta) {
        try {
            oferta.setId(id);
            String msg = ofertaAjudaService.atualizarOfertaAjuda(oferta);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar oferta de ajuda: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarOfertaAjuda(@PathParam("id") int id) {
        try {
            String msg = ofertaAjudaService.deletarOfertaAjuda(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar oferta de ajuda: " + e.getMessage()).build();
        }
    }
}
