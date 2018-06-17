package danihwsr.tournee;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String eMail;


    public User(String firstName, String lastName, String nickName, String eMail) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNickName(nickName);
        this.setEmail(eMail);
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

    public void setEmail(String input) {
        this.eMail = input;
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

    public String geteMail() {
        return this.eMail;
    }
}
