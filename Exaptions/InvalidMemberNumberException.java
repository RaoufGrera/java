package Exaptions;

public class InvalidMemberNumberException extends Exception {
    public InvalidMemberNumberException(int number) {
        super("Invaled member number:" + number);
    }
}
