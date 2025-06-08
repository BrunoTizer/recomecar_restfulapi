package br.com.recomecar.bo;

import br.com.recomecar.model.OfertaAjuda;
import br.com.recomecar.exceptions.ValidacaoException;

public class OfertaAjudaBO {

    public void validarDescricao(OfertaAjuda oferta) {
        String desc = oferta.getDescricao().toLowerCase();
        if (desc.contains("filha da puta") || desc.contains("tomar no cu")) {
            throw new ValidacaoException("Descrição da oferta contém termos inadequados.");
        }
    }
}
