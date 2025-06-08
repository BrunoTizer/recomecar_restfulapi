package br.com.recomecar.bo;

import br.com.recomecar.model.Usuario;
import br.com.recomecar.exceptions.ValidacaoException;

public class UsuarioBO {

    public void validarTipoUsuario(Usuario usuario) {
        String tipo = usuario.getTipo();
        if (tipo == null ||
                (!tipo.equalsIgnoreCase("vitima") && !tipo.equalsIgnoreCase("voluntario"))) {
            throw new ValidacaoException("Tipo de usuário inválido. Deve ser 'vitima' ou 'voluntario'.");
        }
    }
}
