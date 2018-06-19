package danihwsr.tournee;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String mail;

    public User(){}

    public User(String firstName, String lastName, String nickName, String mail) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNickName(nickName);
        this.setMail(mail);
    }

    public void setId(String input) {
        this.id = input;
    }

    public void setFirstName(String input) {
        this.firstName = input;
    }

    public void setLastName(String input) {
        this.lastName = input;
    }

    public void setNickName(String input) {
        this.nickName = input;
    }

    public void setMail(String input) {
        this.mail = input;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getMail() {
        return this.mail;
    }

    @Override
    public String toString() {
        String msg = String.format("ID: %s\nFirstName: %s\nLastName: %s\nNickName: %s\nEmail: %s\n",
                this.getId(),
                this.getFirstName(),
                this.getLastName(),
                this.getNickName(),
                this.getMail()
        );

        return msg;
    }
}
