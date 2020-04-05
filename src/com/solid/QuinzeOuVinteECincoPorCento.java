package com.solid;

public class QuinzeOuVinteECincoPorCento implements RegraDeCalculo {
    public double calcular(Funcionario funcionario) {
        if (funcionario.getSalarioBase() > 2000.0) {
            return funcionario.getSalarioBase() * 0.75;
        }
        else {
            return funcionario.getSalarioBase() * 0.85;
        }
    }
}
