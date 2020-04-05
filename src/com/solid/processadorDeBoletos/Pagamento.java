package com.solid.processadorDeBoletos;

public class Pagamento {
    private final double valor;
    private final MeioPagamento meioPagamento;

    public Pagamento(double valor, MeioPagamento meioPagamento) {
        this.valor = valor;
        this.meioPagamento = meioPagamento;
    }

    public double getValor() {
        return valor;
    }
}
