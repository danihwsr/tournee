package danihwsr.tournee.web;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    // @RequiredArgsConstructor: no args constructor needed for this field, hence not final
    // arg values are passed to a constructor in the order they are declared in the class
    private @Id String id;
    //@NonNull
    //@NotNull(message = "Please provide a first name address.")
    private String firstname;
    //@NonNull
    //@NotNull(message = "Please provide a last name address.")
    private String lastname;
    //@NonNull
    //@NotNull(message = "Please provide a nick name address.")
    private String nickname;
    //@Email(message = "The email address you provided is invalid.")
    //@NotNull(message = "Please provide an email address.")
    //@NonNull
    private String mail;
    //@NonNull
    //@NotNull()
    //@Size(min = 13, max = 125, message = "Please provide an age beetween 13 and 125.")
    private int age;

//    public User(){}

//    public User(String firstname, String lastname, String nickname, String mail, int age) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.nickname = nickname;
//        this.mail = mail;
//        this.age = age;

//        this.setFirstname(firstname);
//        this.setLastname(lastname);
//        this.setNickname(nickname);
//        this.setMail(mail);
//        this.setAge(age);
//   }

//

}
