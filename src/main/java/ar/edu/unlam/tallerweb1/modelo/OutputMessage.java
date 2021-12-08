package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class OutputMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "emisor_id")
    private String from;
    private String text;
    private String time;
    @Column(name = "receptor_id")
    private Long userReceptorId;
    @Column(name = "conversacion_id")
    private Long conversacionId;

    public OutputMessage(String from, String text, String time) {
        this.from = from;
        this.text = text;
        this.time = time;
    }

    public OutputMessage() {

    }

    public String getFrom() {
        return from;
    }

    public OutputMessage setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getText() {
        return text;
    }

    public OutputMessage setText(String text) {
        this.text = text;
        return this;
    }

    public String getTime() {
        return time;
    }

    public OutputMessage setTime(String time) {
        this.time = time;
        return this;
    }

    public Long getUserReceptorId() {
        return userReceptorId;
    }

    public OutputMessage setUserReceptorId(Long userReceptorId) {
        this.userReceptorId = userReceptorId;
        return this;
    }

    public Long getConversacionId() {
        return conversacionId;
    }

    public OutputMessage setConversacionId(Long conversacionId) {
        this.conversacionId = conversacionId;
        return this;
    }
}
