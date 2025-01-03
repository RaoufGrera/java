package Classes;

import java.util.*;

import Exaptions.InvalidMemberNumberException;
import Exaptions.MembershipFullException;

@SuppressWarnings({ "rawtypes", "removal", "unchecked" })
public class MemberShip2 implements java.io.Serializable {

	private Hashtable clubMembers;
	private int maxMembers;

	public MemberShip2(int size) {
		clubMembers = new Hashtable<>();
		maxMembers = size;
	}

	public int getMaxNumberMembers() {
		return maxMembers;
	}

	public int getNumbMembers() {
		return clubMembers.size();
	}

	public void addMember(String name, String address) throws MembershipFullException {
		if (getNumbMembers() >= getMaxNumberMembers()) {
			throw new MembershipFullException(name, address);
		}

		var newOne = new ClubMember(name, address);

		clubMembers.put(new Integer(newOne.grtNumber()), newOne);
	}

	public Enumeration getAllNumbers() {
		return clubMembers.keys();
	}

	public Enumeration getAllMembers() {
		return clubMembers.elements();
	}

	public ClubMember getMember(int numberMember) {
		return (ClubMember) clubMembers.get(new Integer(numberMember));
	}

	public Enumeration getMember(String name) {
		Vector subsct = new Vector<>();

		Enumeration e = getAllMembers();

		while (e.hasMoreElements()) {
			var cm = (ClubMember) e.nextElement();

			if (cm.getName().equals(name)) {
				subsct.addElement(cm);
			}
		}

		return subsct.elements();
	}

	public void removeMember(int number) throws InvalidMemberNumberException {
		if (clubMembers.remove(new Integer(number)) == null) {
			throw new InvalidMemberNumberException(number);
		}
	}

	public String toString() {
		var sb = new StringBuffer("Membership: number of members=" + getNumbMembers() + " max number of members="
				+ getMaxNumberMembers());

		sb.append(clubMembers.toString());

		return new String(sb);
	}
}
