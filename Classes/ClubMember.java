package Classes;

public class ClubMember {

    private String memName, memAddress;
    private int memNumber;

    public static int membershipNumber = 0;

    public ClubMember(String nam, String add) {
        memName = nam;
        memAddress = add;
        memNumber = membershipNumber;
    }

    public String getName() {
        return memName;
    }

    public String getAddress() {
        return memAddress;
    }

    public void changeAddress(String newAddress) {
        memAddress = newAddress;
    }

    public int grtNumber() {
        return memNumber;
    }

    public String toString() {
        return ("ClubMember:Name=" + memName + " Address=" + memAddress + " Number=" + memNumber);
    }
}