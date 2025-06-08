package br.com.recomecar.exceptions;

public class BancoDeDadosException extends RuntimeException {
    public BancoDeDadosException(String mensagem) {
        super(mensagem);
    }
}
