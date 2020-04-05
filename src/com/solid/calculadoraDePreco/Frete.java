package com.solid.calculadoraDePreco;

public class Frete implements ServiceDeEntrega {
    public double para(String cidade) {
        if ("SAO PAULO".equals(cidade.toUpperCase())) {
            return 15;
        }
        return 30;
    }
}
