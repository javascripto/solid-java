package com.solid;

import static com.solid.Cargo.*;

public class CalculadoraDeSalario {

    public double calcula(Funcionario funcionario) {
        if(DESENVOLVEDOR.equals(funcionario.getCargo())) {
            return new DezOuQuinzePorCento().calcular(funcionario);
        }

        if(DBA.equals(funcionario.getCargo()) || TESTER.equals(funcionario.getCargo())) {
            return new QuinzeOuVinteECincoPorCento().calcular(funcionario);
        }

        throw new RuntimeException("funcionario invalido");
    }
}
