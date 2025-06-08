package br.com.recomecar.bo;

import br.com.recomecar.model.PedidoAjuda;
import br.com.recomecar.exceptions.ValidacaoException;

public class PedidoAjudaBO {

    public void validarPrioridade(PedidoAjuda pedido) {
        int prioridade = pedido.getPrioridade();
        if (prioridade < 1 || prioridade > 3) {
            throw new ValidacaoException("A prioridade do pedido deve ser 1 (alta), 2 (média) ou 3 (baixa).");
        }
    }
}
