package Classes;

import java.io.*;
import Exaptions.InvalidMemberNumberException;

public class MemberMaintainer {
    public static void main(String[] args) {
        Membership3 membs = null;
        try {
            FileInputStream fis = new FileInputStream("Membership3.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ClubMember.membershipNumber = ois.readInt();
            membs = (Membership3) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            membs = new Membership3(5);
        } catch (Exception e) {
            System.out.println("Object read error! " + e.getMessage());
            System.exit(1);
        }

        // Process command-line arguments
        for (String arg : args) {
            if (arg.startsWith("+")) {
                int sepIndex = arg.indexOf('+', 1);
                if (sepIndex < 2) {
                    System.out.println("Invalid add command: " + arg);
                    continue;
                }
                String name = arg.substring(1, sepIndex);
                String address = arg.substring(sepIndex + 1);
                membs.addMember(name, address);
            } else if (arg.startsWith("-")) {
                try {
                    int number = Integer.parseInt(arg.substring(1));
                    membs.removeMember(number);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid remove command: " + arg);
                } catch (InvalidMemberNumberException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Unknown command: " + arg);
            }
        }

        // Save state
        try {
            FileOutputStream fos = new FileOutputStream("Membership3.data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(ClubMember.membershipNumber);
            oos.writeObject(membs);
            oos.close();
        } catch (Exception e) {
            System.out.println("Object write error! " + e.getMessage());
        }

        System.out.println(membs);
    }
}