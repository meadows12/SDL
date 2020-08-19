import java.util.Queue;
import java.util.Scanner;

public class Customer {
    String name,address,doj;
    int mcode,fac;
    String phone;

    Scanner sc = new Scanner(System.in);
    public void input_deatils(){
        System.out.println("Enter Membership code: ");
        mcode = sc.nextInt();
        System.out.println("Enter name: ");
        name = sc.next();
        System.out.println("Enter address: ");
        address = sc.next();
        System.out.println("Enter doj: ");
        doj = sc.next();
        System.out.println("Enter Phone number: ");
        phone = sc.next();
        sc.nextLine();
    }

    @Override
    public String toString(){
        return ("-----------------------"+"\nName: "+this.name +
                "\nMembership code: "+ this.mcode +
                "\nAddress: " + this.address +
                "\nDate of joining: " + this.doj +
                "\nPhone number: "+ this.phone +
                "\n-------------------------");
    }
}
