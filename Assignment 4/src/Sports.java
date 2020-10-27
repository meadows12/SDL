import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.*;

public class Sports {
    public static void main(String args[]){
        Ui ui = new Ui();
    }

}

class Ui extends JFrame{

    public Ui(){
        JFrame f = new JFrame();
        JPanel mainPanel = new JPanel();
        f.setLayout(new FlowLayout());
        JButton b1 = new JButton("Admin");
        JButton b2 = new JButton("Customer");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));

        mainPanel.add(b1);
        mainPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        mainPanel.add(b2);

        f.add(mainPanel);
        f.setLocationRelativeTo(null);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                JFrame f1 = new JFrame();
                f1.setLayout(new FlowLayout());
                f1.setVisible(true);
                f1.setSize(800,600);
                JButton b1 = new JButton("Add new Facility");
                JButton b2 = new JButton("Display all Facilities");
                JButton b3 = new JButton("Display all customers");
                JButton b4 = new JButton("Go back");
                f1.add(b1);
                f1.add(b2);
                f1.add(b3);
                f1.add(b4);
                f1.setLocationRelativeTo(null);
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f1.setVisible(false);
                        JFrame f2 = new JFrame();
                        f2.setLayout(new FlowLayout());
                        f2.setVisible(true);
                        f2.setSize(800,600);
                        JLabel l1 = new JLabel("Facility name");

                        JTextField t1 = new JTextField(20);

                        JLabel l2 = new JLabel("Fees");
                        JTextField t2 = new JTextField(6);
                        JButton b1 = new JButton("Submit");
                        JButton b2 = new JButton("Go back");
                        f2.add(l1);
                        f2.add(t1);
                        f2.add(l2);
                        f2.add(t2);
                        f2.add(b1);
                        f2.add(b2);
                        f2.setLocationRelativeTo(null);
                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String fac_name = t1.getText();
                                    String fees = t2.getText();
                                    System.out.println(fac_name);
                                    Connection con = Database.getConnection();
                                    Statement stmt = con.createStatement();
                                    String fac = " ";
                                    int i = 0;
                                    ResultSet rs = stmt.executeQuery("select facility from facility_fee where facility='"+fac_name+"'");
                                    int fee = Integer.parseInt(fees);

                                    if(rs.next()){
                                        JOptionPane.showOptionDialog(null, "Facility already exists", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                    }else
                                    {

                                        i = FacilityDB.save(fac_name, fee);

                                    }


                                    if (i > 0) {
                                        System.out.println("Data added successfully");
                                        int input = JOptionPane.showOptionDialog(null, "Facility added successfully", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (input == JOptionPane.OK_OPTION) {
                                            f2.setVisible(false);
                                            f1.setVisible(true);
                                        }
                                    }
                                }catch (Exception er){
                                    System.out.println(er);
                                }
                            }
                        });

                        b2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                f2.setVisible(false);
                                f1.setVisible(true);
                            }
                        });

                        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });



                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            f1.setVisible(false);
                            JFrame f2 = new JFrame();
                            JPanel mainpanel = new JPanel();
                            mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
                            mainpanel.add(Box.createRigidArea(new Dimension(100, 100)));
                            f2.setSize(800, 600);
                            JButton b1 = new JButton("Go back");
                            Connection con = Database.getConnection();
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from facility_fee");

                            DefaultTableModel model1 = new DefaultTableModel(new String[]{"Facility", "Fees"}, 0);
                            while(rs.next()){
//                                System.out.println(rs.getString(1) + " " + rs.getInt(2));
                                String d = rs.getString(1);
                                int e1 = rs.getInt(2);
                                model1.addRow(new Object[]{d, e1, f2});
                            }

                            JTable jt=new JTable(model1);
                            mainpanel.add(jt);
                            JScrollPane sp=new JScrollPane(jt);

                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    f2.setVisible(false);
                                    f1.setVisible(true);
                                }
                            });

                            mainpanel.add(sp);
                            mainpanel.add(b1);
                            f2.add(mainpanel);
                            f2.setLayout(new FlowLayout());
                            f2.setVisible(true);

                        }catch(Exception er){
                            System.out.println(er);
                        }
                    }
                });

                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            f1.setVisible(false);
                            JFrame f2 = new JFrame();
                            f2.setSize(800, 600);

                            JButton b1 = new JButton("Go back");
                            Connection con = Database.getConnection();
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from customer");

                            DefaultTableModel model1 = new DefaultTableModel(new String[]{"Mcode", "Name","Address","DOJ","Phone no."}, 0);
                            while(rs.next()){
                                int mcode = rs.getInt(1);
                                String name = rs.getString(2);
                                String address = rs.getString(3);
                                String doj = rs.getString(4);
                                long ph = rs.getLong(5);
                                model1.addRow(new Object[]{mcode, name,address,doj,ph,f2});
                            }

                            JTable jt=new JTable(model1);
                            f2.add(jt);
                            JScrollPane sp=new JScrollPane(jt);

                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    f2.setVisible(false);
                                    f1.setVisible(true);
                                }
                            });

                            f2.add(sp);
                            f2.add(b1);
                            f2.setLayout(new FlowLayout());
                            f2.setVisible(true);

                        }catch(Exception er){
                            System.out.println(er);
                        }

                    }
                });

                b4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f1.setVisible(false);
                        f.setVisible(true);
                    }
                });


                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                JFrame f1 = new JFrame();
                JPanel mainPanel = new JPanel();
                f1.setLayout(new FlowLayout());
                f1.setVisible(true);
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));
                JButton b1 = new JButton("Input details");
                JButton b2 = new JButton("Display your details");
                JButton b3 = new JButton("Show available facilities");
                JButton b4 = new JButton("Input fee details");
                JButton b5 = new JButton("Add one more facility");
                JButton b6 = new JButton("Print customer's facilities");
                f1.setLocationRelativeTo(null);
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f1.setVisible(false);
                        JFrame f2 = new JFrame();
                        JPanel mainPanel = new JPanel();
                        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                        mainPanel.add(Box.createRigidArea(new Dimension(100,10)));
                        JLabel l1 = new JLabel("Name");
                        JTextField t1 = new JTextField(10);

                        mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));
                        JLabel l2 = new JLabel("Address");
                        JTextField t2 = new JTextField(20);

                        JLabel l3 = new JLabel("DOJ(yyyy-mm-dd)");
                        JTextField t3 = new JTextField(10);

                        JLabel l4 = new JLabel("Phone no.");
                        JTextField t4 = new JTextField(10);

                        JButton b1 = new JButton("Submit");
                        JButton b2 = new JButton("Go back");
                        f2.setLayout(new FlowLayout());
                        f2.setLocationRelativeTo(null);
                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String name = t1.getText();
                                String address = t2.getText();
                                String doj = t3.getText();
                                String ph = t4.getText();
                                long phone = Long.parseLong(ph);
                                int i = CustomerDB.save(name,address,doj,phone);
                                if(i>0){
                                    System.out.println("Data added successfully");
                                    int input = JOptionPane.showOptionDialog(null, "Customer added successfully", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                    if(input == JOptionPane.OK_OPTION)
                                    {
                                        f2.setVisible(false);
                                        f1.setVisible(true);
                                    }
                                }
                            }
                        });

                        b2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                f2.setVisible(false);
                                f1.setVisible(true);
                            }
                        });

                        mainPanel.add(l1);mainPanel.add(t1);mainPanel.add(l2);mainPanel.add(t2);
                        mainPanel.add(l3);mainPanel.add(t3);mainPanel.add(l4);mainPanel.add(t4);
                        mainPanel.add(Box.createRigidArea(new Dimension(50, 50)));
                        mainPanel.add(b1);
                        mainPanel.add(Box.createRigidArea(new Dimension(50, 50)));
                        mainPanel.add(b2);
                        f2.add(mainPanel);
                        f2.setVisible(true);
                        f2.setSize(400,500);
                        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });

                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            f1.setVisible(false);
                            JFrame f2 = new JFrame();
                            f2.setSize(800, 600);

                            JButton b1 = new JButton("Go back");
                            Connection con = Database.getConnection();
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from customer");

                            DefaultTableModel model1 = new DefaultTableModel(new String[]{"Mcode", "Name","Address","DOJ","Phone no."}, 0);
                            while(rs.next()){
                                int mcode = rs.getInt(1);
                                String name = rs.getString(2);
                                String address = rs.getString(3);
                                String doj = rs.getString(4);
                                long ph = rs.getLong(5);
                                model1.addRow(new Object[]{mcode, name,address,doj,ph,f2});
                            }

                            JTable jt=new JTable(model1);
                            f2.add(jt);
                            JScrollPane sp=new JScrollPane(jt);

                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    f2.setVisible(false);
                                    f1.setVisible(true);
                                }
                            });


                            f2.add(sp);
                            f2.add(b1);
                            f2.setLayout(new FlowLayout());
                            f2.setVisible(true);

                        }catch(Exception er){
                            System.out.println(er);
                        }
                    }
                });

                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            f1.setVisible(false);
                            JFrame f2 = new JFrame();
                            f2.setSize(800, 600);
                            JButton b1 = new JButton("Go back");
                            Connection con = Database.getConnection();
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from facility_fee");

                            DefaultTableModel model1 = new DefaultTableModel(new String[]{"Facility", "Fees"}, 0);
                            while(rs.next()){
//                                System.out.println(rs.getString(1) + " " + rs.getInt(2));
                                String d = rs.getString(1);
                                int e1 = rs.getInt(2);
                                model1.addRow(new Object[]{d, e1, f2});
                            }

                            JTable jt=new JTable(model1);
                            f2.add(jt);
                            JScrollPane sp=new JScrollPane(jt);

                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    f2.setVisible(false);
                                    f1.setVisible(true);
                                }
                            });

                            f2.add(sp);
                            f2.add(b1);
                            f2.setLayout(new FlowLayout());
                            f2.setVisible(true);

                        }catch(Exception er){
                            System.out.println(er);
                        }
                    }
                });

                b4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f1.setVisible(false);
                        JFrame f2 = new JFrame();
                        JPanel mainPanel = new JPanel();
                        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                        mainPanel.add(Box.createRigidArea(new Dimension(100,10)));
                        JLabel l1 = new JLabel("Membership code");
                        JTextField t1 = new JTextField(10);

                        JLabel l2 = new JLabel("Facility name:");
                        JTextField t2 = new JTextField(20);

                        JLabel l3 = new JLabel("Date of fee submission(yyyy-mm-dd)");
                        JTextField t3 = new JTextField(10);

                        JButton b1 = new JButton("Submit");
                        JButton b2 = new JButton("Go back");
                        f2.setLayout(new FlowLayout());
                        f2.setLocationRelativeTo(null);
                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String code = t1.getText();
                                String facility = t2.getText();
                                String dos = t3.getText();
                                int mcode = Integer.parseInt(code);
                                int i = FeeDB.save(mcode,dos,facility,null);
                                if(i>0){
                                    System.out.println("Data added successfully");
                                    int input = JOptionPane.showOptionDialog(null, "Fee details added successfully", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                    if(input == JOptionPane.OK_OPTION)
                                    {
                                        f2.setVisible(false);
                                        f1.setVisible(true);
                                    }
                                }
                            }
                        });

                        b2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                f2.setVisible(false);
                                f1.setVisible(true);
                            }
                        });

                        mainPanel.add(l1);mainPanel.add(t1);mainPanel.add(l2);mainPanel.add(t2);
                        mainPanel.add(l3);mainPanel.add(t3);
                        mainPanel.add(b1);mainPanel.add(b2);
                        f2.add(mainPanel);
                        f2.setVisible(true);
                        f2.setSize(300,500);
                        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    }
                });

                b5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f1.setVisible(false);
                        JFrame f2 = new JFrame();

                        JLabel l1 = new JLabel("Membership code");
                        JTextField t1 = new JTextField(10);

                        JLabel l2 = new JLabel("Facility name:");
                        JTextField t2 = new JTextField(20);

                        JButton b1 = new JButton("Submit");
                        JButton b2 = new JButton("Go back");
                        f2.setLayout(new FlowLayout());

                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String code = t1.getText();
                                String facility = t2.getText();

                                int mcode = Integer.parseInt(code);
                                int i = FeeDB.update(mcode,facility);
                                if(i>0){
                                    System.out.println("Data added successfully");
                                    int input = JOptionPane.showOptionDialog(null, "Fee details added successfully", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                    if(input == JOptionPane.OK_OPTION)
                                    {
                                        f2.setVisible(false);
                                        f1.setVisible(true);
                                    }
                                }
                            }
                        });

                        b2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                f2.setVisible(false);
                                f1.setVisible(true);
                            }
                        });

                        f2.add(l1);f2.add(t1);f2.add(l2);f2.add(t2);

                        f2.add(b1);f2.add(b2);
                        f2.setVisible(true);
                        f2.setSize(500,500);
                        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });

                b6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                            f1.setVisible(false);
                            JFrame f2 = new JFrame();


                            JButton b1 = new JButton("Go back");
                            JButton b2 = new JButton("Submit");
                            JLabel l1 = new JLabel("Membership code");

                            JTextField t1 = new JTextField(10);
                        f2.add(l1);f2.add(t1);

                        f2.add(b1);
                        f2.add(b2);

                        f2.setSize(500,500);
                        f2.setLocationRelativeTo(null);

                            b2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try{

                                    String code = t1.getText();
                                    int mcode = Integer.parseInt(code);
                                    System.out.println(mcode);
                                    Connection con = Database.getConnection();
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery("select fc1,fc2,fees from customer_fee where mcode="+mcode);

                                    DefaultTableModel model1 = new DefaultTableModel(new String[]{"Facility 1", "Facility 2","Fees"}, 0);
                                       if(rs.next()){
                                           do{
                                               String d = rs.getString("fc1");
                                               String d1 = rs.getString("fc2");
                                               int e1 = rs.getInt("fees");
                                               System.out.println(d);

                                               model1.addRow(new Object[]{d, d1,e1, f2});
                                           }while(rs.next());
                                       }else{
                                           JOptionPane.showOptionDialog(null, "Id does not exists", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                       }




                                    JTable jt=new JTable(model1);

                                    f2.add(jt);
                                        JScrollPane sp=new JScrollPane(jt);


                                    f2.add(sp);

                                    f2.setVisible(true);
                                    }catch (Exception er){
                                        System.out.println(er);
                                    }

                                }
                            });


                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    f2.setVisible(false);
                                    f1.setVisible(true);
                                }
                            });

                        f2.setLayout(new FlowLayout());

                        f2.setVisible(true);


                    }
                });

                mainPanel.add(b1);
                mainPanel.add(b2);
                mainPanel.add(b3);
                mainPanel.add(b4);
                mainPanel.add(b5);
                mainPanel.add(b6);
                f1.add(mainPanel);
                f1.setSize(400,500);
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        f.setVisible(true);//shows the frame
        f.setSize(400,500);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
