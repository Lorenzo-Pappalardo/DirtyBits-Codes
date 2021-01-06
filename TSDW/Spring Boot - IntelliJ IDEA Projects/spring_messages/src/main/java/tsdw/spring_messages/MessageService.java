package tsdw.spring_messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message msg) {
        return messageRepository.save(msg);
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }
}
