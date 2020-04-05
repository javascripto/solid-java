package com.solid.calculadoraDeSalario;

public class CalculadoraDeSalario {

    public double calcula(Funcionario funcionario) {
        return funcionario.calcularSalario();
    }

    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();
        funcionario.setCargo(Cargo.DESENVOLVEDOR);
        funcionario.setSalarioBase(7200);

        CalculadoraDeSalario calculadoraDeSalario = new CalculadoraDeSalario();

        System.out.println(calculadoraDeSalario.calcula(funcionario));
    }
}
