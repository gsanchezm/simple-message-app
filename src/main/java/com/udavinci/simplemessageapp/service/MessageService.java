package com.udavinci.simplemessageapp.service;

import com.udavinci.simplemessageapp.model.Message;

import java.util.List;

public interface MessageService {

    Message save(String text);

    long count();

    List<Message> findAll();
}