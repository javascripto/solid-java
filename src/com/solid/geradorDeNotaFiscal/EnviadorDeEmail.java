package com.solid.geradorDeNotaFiscal;

public class EnviadorDeEmail implements AcaoAposGerarNota {
    public void enviarEmail(NotaFiscal nf) {
        System.out.println("Enviando e-mail da nota fiscal " + nf.getId());
    }

    @Override
    public void executar(NotaFiscal nf) {
        this.enviarEmail(nf);
    }
}
