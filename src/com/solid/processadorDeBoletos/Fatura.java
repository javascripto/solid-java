package com.solid.processadorDeBoletos;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Fatura {
    private boolean pago;
    private double valor;
    private String cliente;
    private List<Pagamento> pagamentos = new ArrayList<>();

    public Fatura(String cliente, double valor) {
        this.valor = valor;
        this.cliente = cliente;
    }

    public boolean isPago() {
        return pago;
    }
    public List<Pagamento> getPagamentos() {
        return Collections.unmodifiableList(pagamentos);
    }
    public double getValor() {
        return valor;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
        if (valorTotaldosPagamentos() >= valor)
            pago = true;
    }

    private double valorTotaldosPagamentos() {
        return pagamentos.stream()
            .map(Pagamento::getValor)
            .reduce(0.0, Double::sum);
    }
}
