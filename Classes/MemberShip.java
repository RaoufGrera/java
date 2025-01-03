package Classes;

public class MemberShip {
    private ClubMember[] memberShip;
    private int numMembers;

    public MemberShip(int maxMembers) {
        numMembers = 0;
        memberShip = new ClubMember[maxMembers];
    }

    public int getMaxNumberMembers() {
        return memberShip.length;
    }

    public int getNumbMembers() {
        return numMembers;
    }

    public Boolean addMember(String name, String address) {
        if (numMembers < memberShip.length) {
            ClubMember newOne = new ClubMember(name, address);
            memberShip[newOne.grtNumber()] = newOne;
            ++numMembers;
            return true;
        } else
            return false;

    }

    public ClubMember getMember(int memberNumber) {
        if (memberNumber >= 0 && memberNumber < numMembers) {
            return memberShip[memberNumber];
        } else
            return null;
    }

    public ClubMember[] getMember(String name) {
        int count = 0;
        for (int i = 0; i < numMembers; i++) {
            if (memberShip[i].getName().equals(name)) {
                ++count;
            }
        }

        var retArr = new ClubMember[count];

        count = 0;

        for (int i = 0; i < numMembers; i++) {
            if (memberShip[i].getName().equals(name)) {
                retArr[count++] = memberShip[i];
            }
        }
        return retArr;

    }

    public String toString() {
        StringBuffer sb = new StringBuffer("Membership: number of members=" + getNumbMembers()
                + " max number of members=" + getMaxNumberMembers());

        String eol = System.getProperty("line.seperator");

        for (int i = 0; i < numMembers; i++) {
            sb.append(eol);
            sb.append(memberShip[i].toString());
        }

        return new String(sb);
    }
}
