package com.solid.processadorDeBoletos;

import java.util.List;
import java.util.ArrayList;

public class ProcessadorDeBoletos {

    public void processar(List<Boleto> boletos, Fatura fatura) {
        for (Boleto boleto: boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValor(), MeioPagamento.BOLETO);
            fatura.adicionarPagamento(pagamento);
        }
    }

    public static void main(String[] args) {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto(123));
        boletos.add(new Boleto(456));
        boletos.add(new Boleto(789));

        Fatura fatura = new Fatura("Fulano", 1234.5);

        ProcessadorDeBoletos processador = new ProcessadorDeBoletos();
        processador.processar(boletos, fatura);

        System.out.println(fatura.isPago());
    }
}
