package com.udavinci.simplemessageapp.repository;

import com.udavinci.simplemessageapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}