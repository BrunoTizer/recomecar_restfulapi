package br.com.recomecar.resources;

import br.com.recomecar.exceptions.RegistroNaoEncontradoException;
import br.com.recomecar.exceptions.ValidacaoException;
import br.com.recomecar.model.Usuario;
import br.com.recomecar.services.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private UsuarioService usuarioService;

    public UsuarioResource() throws SQLException, ClassNotFoundException {
        this.usuarioService = new UsuarioService();
    }

    @POST
    public Response criarUsuario(Usuario usuario) {
        try {
            String msg = usuarioService.cadastrarUsuario(usuario);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.status(Response.Status.CREATED).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar usuário: " + e.getMessage()).build();
        }
    }

    @GET
    public Response listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            return Response.ok(usuarios).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar usuários: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarUsuario(@PathParam("id") int id) {
        try {
            Usuario usuario = usuarioService.buscarUsuario(id);
            if (usuario != null) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar usuário: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario login) {
        try {
            Usuario usuario = usuarioService.login(login.getEmail(), login.getSenha());
            return Response.ok(usuario).build();
        } catch (ValidacaoException | RegistroNaoEncontradoException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no login: " + e.getMessage()).build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        try {
            usuario.setIdUsuario(id);
            String msg = usuarioService.atualizarUsuario(usuario);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar usuário: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarUsuario(@PathParam("id") int id) {
        try {
            String msg = usuarioService.deletarUsuario(id);
            if (msg.startsWith("Erro")) {
                return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
            }
            return Response.ok(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar usuário: " + e.getMessage()).build();
        }
    }
}
