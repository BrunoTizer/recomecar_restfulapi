package br.com.recomecar.resources;

import br.com.recomecar.model.PedidoAjuda;
import br.com.recomecar.services.PedidoAjudaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/pedidos-ajuda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoAjudaResource {

    private PedidoAjudaService pedidoAjudaService;

    public PedidoAjudaResource() throws SQLException, ClassNotFoundException {
        this.pedidoAjudaService = new PedidoAjudaService();
    }

    @POST
    public Response criarPedidoAjuda(PedidoAjuda pedido) {
        try {
            String msg = pedidoAjudaService.cadastrarPedidoAjuda(pedido);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar pedido de ajuda: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarPedidosAjuda() {
        try {
            List<PedidoAjuda> pedidos = pedidoAjudaService.listarPedidosAjuda();
            return Response.ok(pedidos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pedidos de ajuda: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPedidoAjuda(@PathParam("id") int id) {
        try {
            PedidoAjuda pedido = pedidoAjudaService.buscarPedidoAjuda(id);
            if (pedido != null) {
                return Response.ok(pedido).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Pedido de ajuda não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar pedido de ajuda: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarPedidoAjuda(@PathParam("id") int id, PedidoAjuda pedido) {
        try {
            pedido.setId(id);
            String msg = pedidoAjudaService.atualizarPedidoAjuda(pedido);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pedido de ajuda: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarPedidoAjuda(@PathParam("id") int id) {
        try {
            String msg = pedidoAjudaService.deletarPedidoAjuda(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pedido de ajuda: " + e.getMessage()).build();
        }
    }
}
