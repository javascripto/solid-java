package com.solid.geradorDeNotaFiscal;

public class EnviadorDeEmail {
    public void enviarEmail(NotaFiscal nf) {
        System.out.println("Enviando e-mail da nota fiscal " + nf.getId());
    }
}
