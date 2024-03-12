package com.example.demo.modelos;

public class Email {
    private String remetente;
    private String destinatario;
    private String assunto;
    private String corpo;

    public Email(String remetente, String destinatario, String assunto, String corpo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.corpo = corpo;
    }
}
