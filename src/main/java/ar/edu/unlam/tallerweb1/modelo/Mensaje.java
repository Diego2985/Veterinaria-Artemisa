package ar.edu.unlam.tallerweb1.modelo;

public class Mensaje {

    private String from;
    private String text;

    public String getFrom() {
        return from;
    }

    public Mensaje setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getText() {
        return text;
    }

    public Mensaje setText(String text) {
        this.text = text;
        return this;
    }
}