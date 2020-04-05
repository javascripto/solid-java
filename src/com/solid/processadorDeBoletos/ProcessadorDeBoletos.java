package com.solid.processadorDeBoletos;

import java.util.List;
import java.util.ArrayList;

public class ProcessadorDeBoletos {
    public void processar(List<Boleto> boletos, Fatura fatura) {
        double total = 0;

        for (Boleto boleto: boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValor(), MeioPagamento.BOLETO);
            fatura.getPagamentos().add(pagamento);
            total += boleto.getValor();
        }

        if (fatura.getValor() <= total) {
            fatura.setPago(true);
        }
    }

    public static void main(String[] args) {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto());
        boletos.add(new Boleto());
        boletos.add(new Boleto());
        Fatura fatura = new Fatura();

        ProcessadorDeBoletos processador = new ProcessadorDeBoletos();
//        processador.processar(boletos, fatura);
    }
}
