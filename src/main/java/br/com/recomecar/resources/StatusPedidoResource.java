package br.com.recomecar.resources;

import br.com.recomecar.model.StatusPedido;
import br.com.recomecar.services.StatusPedidoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/status-pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusPedidoResource {

    private StatusPedidoService statusPedidoService;

    public StatusPedidoResource() throws SQLException, ClassNotFoundException {
        this.statusPedidoService = new StatusPedidoService();
    }

    @POST
    public Response criarStatusPedido(StatusPedido statusPedido) {
        try {
            String msg = statusPedidoService.cadastrarStatusPedido(statusPedido);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar status do pedido: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarStatusPedidos() {
        try {
            List<StatusPedido> lista = statusPedidoService.listarStatusPedidos();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar status dos pedidos: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarStatusPedido(@PathParam("id") int id) {
        try {
            StatusPedido statusPedido = statusPedidoService.buscarStatusPedido(id);
            if (statusPedido != null) {
                return Response.ok(statusPedido).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Status do pedido não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar status do pedido: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarStatusPedido(@PathParam("id") int id, StatusPedido statusPedido) {
        try {
            statusPedido.setIdStatus(id);
            String msg = statusPedidoService.atualizarStatusPedido(statusPedido);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar status do pedido: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarStatusPedido(@PathParam("id") int id) {
        try {
            String msg = statusPedidoService.deletarStatusPedido(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar status do pedido: " + e.getMessage()).build();
        }
    }
}
