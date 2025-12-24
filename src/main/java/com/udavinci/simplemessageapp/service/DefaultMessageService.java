package com.udavinci.simplemessageapp.service;

import com.udavinci.simplemessageapp.model.Message;
import com.udavinci.simplemessageapp.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMessageService implements MessageService {

    private final MessageRepository repository;

    public DefaultMessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message save(String text) {
        Message message = Message.create(text);
        return repository.save(message);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }
}