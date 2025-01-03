package Classes;

import Exaptions.InvalidMemberNumberException;
import Exaptions.MembershipFullException;

public class Membership3 extends MemberShip2 {
    private WaitingList waitList;

    public Membership3(int size) {

        super(size);
        waitList = new WaitingList();
    }

    public void addMember(String name, String address) {
        try {
            super.addMember(name, address);
        } catch (MembershipFullException e) {

            waitList.add(name, address);
        }
    }

    public void removeMember(int number) throws InvalidMemberNumberException {
        super.removeMember(number); // throws an exception if unsuccessful
        String[] wlFirst = waitList.getFirst(); // does not run if exception
        // thrown by line above
        if (wlFirst != null) {
            addMember(wlFirst[0], wlFirst[1]);
        }
    }

    public String toString() {
        return super.toString() + "/n" + waitList.toString();

    }
}
