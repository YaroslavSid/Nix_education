package ua.com.alevel;


public class AppProperties {

    @Keys("name")
    public String userName;
    @Keys("password")
    public String userPassword;
    @Keys("number")
    public String userNumber;

    @Override
    public String toString() {
        return "AppProperties{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNumber='" + userNumber + '\'' +
                '}';
    }
}
