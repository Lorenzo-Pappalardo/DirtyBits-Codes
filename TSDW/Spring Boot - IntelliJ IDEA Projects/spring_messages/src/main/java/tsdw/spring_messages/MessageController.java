package tsdw.spring_messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping("/messages/send")
    public ResponseEntity<Message> sendMessage(@Valid @RequestBody Message msg) throws URISyntaxException {
        if (msg.getId() != null)
            return ResponseEntity.badRequest().build();
        Message newMsg = messageService.saveMessage(msg);
        return ResponseEntity.created(new URI("api/messages/" + newMsg.getId())).body(newMsg);
    }
}
