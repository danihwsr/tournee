package danihwsr.tournee.web;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "users")
public class User {

    // @RequiredArgsConstructor: no args constructor needed for this field, hence not final
    // arg values are passed to a constructor in the order they are declared in the class
    private @Id String id;
    @NonNull
    @NotNull(message = "Please provide a first name address.")
    private String firstname;
    @NonNull
    @NotNull(message = "Please provide a last name address.")
    private String lastname;
    @NonNull
    @NotNull(message = "Please provide a nick name address.")
    private String nickname;
    @Email(message = "The email address you provided is invalid.")
    @NotNull(message = "Please provide an email address.")
    @NonNull
    private String mail;
    @NonNull
    @NotNull()
    @Size(min = 13, max = 125, message = "Please provide an age beetween 13 and 125.")
    private int age;

//    public User(String firstname, String lastname, String nickname, String mail, int age) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.nickname = nickname;
//        this.mail = mail;
//        this.age = age;

//        this.setFirstName(firstName);
//        this.setLastName(lastName);
//        this.setNickName(nickName);
//        this.setMail(mail);
//    }
//
//    public void setId(String input) {
//        this.id = input;
//    }
//
//    public void setFirstname(String input) {
//        this.firstName = input;
//    }
//
//    public void setLastname(String input) {
//        this.lastName = input;
//    }
//
//    public void setNickname(String input) {
//        this.nickName = input;
//    }
//
//    public void setMail(String input) {
//        this.mail = input;
//    }
//
//    public String getId() {
//        return this.id;
//    }
//
//    public String getFirstname() {
//        return this.firstName;
//    }
//
//    public String getLastname() {
//        return this.lastName;
//    }
//
//    public String getNickname() {
//        return this.nickName;
//    }
//
//    public String getMail() {
//        return this.mail;
//    }
//
//    @Override
//    public String toString() {
//        String msg = String.format("ID: %s\nFirstName: %s\nLastName: %s\nNickName: %s\nEmail: %s\n",
//                this.getId(),
//                this.getFirstname(),
//                this.getLastname(),
//                this.getNickname(),
//                this.getMail()
//        );
//
//        return msg;
//    }
}