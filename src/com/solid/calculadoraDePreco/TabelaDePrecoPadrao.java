package com.solid.calculadoraDePreco;

public class TabelaDePrecoPadrao implements TabelaDePreco {
    public double descontPara(double valor) {
        if (valor > 1000) return 0.05;
        if (valor > 5000) return 0.03;
        return 0;
    }
}
