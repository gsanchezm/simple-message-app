package com.udavinci.simplemessageapp.model;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String text;

    protected Message() {
        // requerido por JPA
    }

    private Message(String text) {
        this.text = text;
    }

    public static Message create(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Message text must not be empty");
        }
        return new Message(text.trim());
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}