package br.com.recomecar.resources;

import br.com.recomecar.model.Categoria;
import br.com.recomecar.services.CategoriaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    private CategoriaService categoriaService;

    public CategoriaResource() throws SQLException, ClassNotFoundException {
        this.categoriaService = new CategoriaService();
    }

    @POST
    public Response criarCategoria(Categoria categoria) {
        try {
            String msg = categoriaService.cadastrarCategoria(categoria);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar categoria: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarCategorias() {
        try {
            List<Categoria> categorias = categoriaService.listarCategorias();
            return Response.ok(categorias).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar categorias: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarCategoria(@PathParam("id") int id) {
        try {
            Categoria categoria = categoriaService.buscarCategoria(id);
            if (categoria != null) {
                return Response.ok(categoria).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Categoria não encontrada.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar categoria: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCategoria(@PathParam("id") int id, Categoria categoria) {
        try {
            categoria.setIdCategoria(id);
            String msg = categoriaService.atualizarCategoria(categoria);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar categoria: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCategoria(@PathParam("id") int id) {
        try {
            String msg = categoriaService.deletarCategoria(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar categoria: " + e.getMessage()).build();
        }
    }
}
