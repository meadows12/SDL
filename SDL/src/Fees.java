import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Fees {
    String dos;
    int mcode,fees = 0;
    String fclity;
    Stack<String> more_facility = new Stack<>();
    Scanner sc = new Scanner(System.in);
    public HashMap<String,Integer> facility = new HashMap<String,Integer>();

    public void facility_fees(){
        facility.put("Swimming",10000);
        facility.put("Swimming with Instructor",16000);
        facility.put("Badminton",13000);
        facility.put("Gym",10000);
        facility.put("Gym with Instructor",15000);
        facility.put("Tennis",13000);
        facility.put("Tennis Coaching",18000);
    }

    public void print_facility_fees() {
        System.out.println("Facility with annual fees" );
        for (String i : facility.keySet()) {
            System.out.println(i + "\tRs." + facility.get(i));

        }
    }



    public void add_admin_facility(String f,int fee){
        facility.put(f,fee);
    }

    public void add_user_facility(){
        System.out.println("Enter more facility you want to add: ");
        fclity = sc.next();
        more_facility.push(fclity);
        calculate_fees();
    }

    public void display_facility(){
        Enumeration enu = more_facility.elements();
        while (enu.hasMoreElements()) {
            System.out.println(enu.nextElement());
        }
        System.out.println("Total fees: "+ fees);
    }

    public void input_fee_detail(){
        System.out.println("Enter facility you want: ");
        fclity = sc.next();
        more_facility.push(fclity);
        System.out.println("Enter Member code: ");
        mcode = sc.nextInt();
        System.out.println("Enter date of submission of fees: ");
        dos = sc.next();
        calculate_fees();
    }

    public void calculate_fees(){
        if(facility.containsKey(fclity)){
            fees += facility.get(fclity);
        }
    }

    public int getMcode() {
        return mcode;
    }

    @Override
    public String toString(){
        return ("Fees: "+this.fees);
    }
}
