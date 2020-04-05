package com.solid.calculadoraDeSalario;

public class DezOuQuinzePorCento implements RegraDeCalculo {
    public double calcular(Funcionario funcionario) {
        if (funcionario.getSalarioBase() > 3000.0) {
            return funcionario.getSalarioBase() * 0.8;
        }
        else {
            return funcionario.getSalarioBase() * 0.9;
        }
    }
}
