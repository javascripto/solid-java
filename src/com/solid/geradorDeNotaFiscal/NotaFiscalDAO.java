package com.solid.geradorDeNotaFiscal;

public class NotaFiscalDAO implements AcaoAposGerarNota {
    public void persistir(NotaFiscal nf) {
        System.out.println("Salvando nf no banco");
    }

    @Override
    public void executar(NotaFiscal nf) {
        persistir(nf);
    }
}
