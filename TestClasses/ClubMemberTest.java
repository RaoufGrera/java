package TestClasses;

import Classes.ClubMember;

public class ClubMemberTest {
    public static void main(String[] args) {
        ClubMember mem1, mem2, mem3;

        mem1 = new ClubMember("White", "WWE 123");
        mem2 = new ClubMember("Black", "BWE 124");
        mem3 = new ClubMember("Green", "GWE 125");

        System.out.println(mem1.toString());
        System.out.println(mem2.toString());
        System.out.println(mem3.toString());

        mem2.changeAddress("BEW");

        System.out.println(mem2.getName() + "'s address changed:");
        System.out.println(mem2.toString());
    }
}
