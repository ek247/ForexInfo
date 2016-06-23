package com.forexbackend;

import org.springframework.data.repository.CrudRepository;

/**
 * Repo for messages as I don't have an email address to send emails from
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {}
