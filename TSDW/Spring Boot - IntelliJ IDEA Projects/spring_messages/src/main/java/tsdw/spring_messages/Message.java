package tsdw.spring_messages;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String fromUser;

    @NotNull
    private Date date;

    @NotBlank
    private String messageBody;

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Message(@NotNull String fromUser, @NotNull Date date, @NotBlank String messageBody) {
        this.fromUser = fromUser;
        this.date = date;
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromUser='" + fromUser + '\'' +
                ", date=" + date +
                '}';
    }
}
