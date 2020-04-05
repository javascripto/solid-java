package com.solid.ProcessadorDeInvestimentos;

import java.util.List;
import java.util.ArrayList;

public class ProcessadorDeInvestimentos {

    public static void main(String[] args) {
        for (ContaComum conta: contasDoBanco()) {
            conta.render();
            System.out.println("Novo Saldo: " + conta.getSaldo());
        }
    }

    private static List<ContaComum> contasDoBanco() {
        List<ContaComum> contas = new ArrayList<>();
        contas.add(new ContaComum(300));
        contas.add(new ContaComum(400));
        contas.add(new ContaComum(500));
        contas.add(new ContaComum());
        return contas;
    }
}
