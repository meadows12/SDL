import java.util.*;

public class Sports {



    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Sports s = new Sports();
        ArrayList<Customer> customers = new ArrayList<>();
        Deque<Fees> fee = new ArrayDeque<>();
        String t;
        Fees f = new Fees();
        f.facility_fees();
        while(true){
            System.out.println("Admin(A) or Customer(C)");
            String st = sc.next();

            if (st.equals("C")){
                Customer c = new Customer();
                Fees fe = new Fees();
                fe.facility_fees();
                while(true){
                    System.out.println("1.Input details\n2.Display details\n3.Show available facilities\n4.Input fee details\n5.Add more facility\n6.Print your facilities\n0.Exit");
                    t = sc.next();
                    if (t.equals("0")) {
                        break;
                    }
                    switch (t){
                        case "1":{
                            c.input_deatils();
                            customers.add(c);
                            break;
                        }
                        case "2":{
                            for(Customer cus:customers){
                                System.out.println(cus);
                            }break;
                        }
                        case "3":{
                            f.print_facility_fees();
                            break;
                        }
                        case "4":{

                            fe.input_fee_detail();
                            fee.add(fe);
                            break;

                        }
                        case "5":{
                            fe.add_user_facility();
                            break;
                        }
                        case "6":{
                            System.out.println("Enter Membership code: ");
                            int code = sc.nextInt();
                            for(Fees f1: fee) {
                                if (f1.getMcode() == code) {
                                    System.out.println(f1);
                                }
                                f1.display_facility();
                            }
                            break;
                        }
                    }
                }


            }else{


                System.out.println("1.Add facility\n2.Display facility\n3.Display all customer\n0.Exit");
                t = sc.next();
                if (t.equals("0")) {
                    break;
                }
                switch (t){
                    case "1":{
                        System.out.println("Enter facility name: ");
                        String se = sc.next();
                        System.out.println("Enter fees of facility: ");
                        int fee_ = sc.nextInt();
                        f.add_admin_facility(se,fee_);

                        break;
                    }
                    case "2":{
                        f.print_facility_fees();
                        break;
                    }
                    case "3":{
                        for(Customer customer:customers){
                            System.out.println(customer);
                        }
                    }
                }
            }
            System.out.println("Do you want to continue?");
            String a = sc.next();
            if(a.equals("Y")) {
                continue;
            } else {
                break;
            }
        }





    }
}
