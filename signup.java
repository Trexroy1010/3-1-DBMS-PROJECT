import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class signup implements ActionListener {

        JFrame frame = new JFrame();
       JLabel welcome = new JLabel("Welcome To PICKandDROPbyANY");
       JLabel instruction = new JLabel("Please make sure to fill up off of the boxes.");
       JLabel message = new JLabel("");
       JLabel name = new JLabel("Name: ");
       JTextField namefield = new JTextField();
       JLabel password = new JLabel("Password: ");
       JPasswordField passfield = new JPasswordField();
       JLabel confirmpass = new JLabel("Confirm Password: ");
       JPasswordField confirmpasssagain = new JPasswordField();
       JLabel useremail = new JLabel("Email: ");
       JTextField usermailget = new JTextField();
       JLabel age = new JLabel("Age: ");
       Integer[] ages = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};

       JComboBox<Integer> ageget = new JComboBox<>(ages);
       JLabel address = new JLabel("Address");
       JTextField addressget = new JTextField();
    public String[] citysnames = {"Dhaka","Faridpur","Gazipur","Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi","Netrokona","Rajbari","Shariatpur","Sherpur","Tangail","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi","Sirajgonj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur","Chittagong","Comilla","Coxs Bazar","Feni","Khagrachari","Lakshmipur","Noakhali","Rangamati","Habiganj","Maulvibazar","Sunamganj","Sylhet","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna","Kushtia","Magura","Meherpur","Narail","Satkhira"};
    JComboBox<String> citys = new JComboBox<>(citysnames);
        JPanel background;
        JLabel city = new JLabel("City");
        JButton createaccount = new JButton("Create Account");
       JLabel phoneno = new JLabel("Phone No: ");
        JTextField phonenoget = new JTextField();
        signup() {
                welcome.setBounds(10, 30,300,35);

                instruction.setBounds(10,70,300,35);

                name.setBounds(30, 110,30,25);
                namefield.setBounds(65,110, 100,25);

                password.setBounds(30,140,30,25);
                passfield.setBounds(65,140,100,25);

                confirmpass.setBounds(30,170,30,25);
                confirmpasssagain.setBounds(65,170,100,25);

                useremail.setBounds(30,200,30,25);
                usermailget.setBounds(65,200,100,25);

                age.setBounds(30,230,30,25);
                ageget.setBounds(60,230,100,25);

                address.setBounds(30,260,30,25);
                addressget.setBounds(60,260,100,25);

                city.setBounds(30,290,30,25);
                citys.setBounds(60,290,100,25);

                phoneno.setBounds(30,320,30,25);
                phonenoget.setBounds(60,320, 100,25);

                createaccount.setBounds(150,350,50,35);
                createaccount.addActionListener(this);

                message.setBounds(10,400,350,55);

                frame.add(welcome);
                frame.add(instruction);
                frame.add(name);
                frame.add(namefield);
                frame.add(password);
                frame.add(passfield);
                frame.add(confirmpass);
                frame.add(confirmpasssagain);
                frame.add(useremail);
                frame.add(usermailget);
                frame.add(age);
                frame.add(ageget);
                frame.add(address);
                frame.add(addressget);
                frame.add(city);
                frame.add(citys);
                frame.add(createaccount);
                frame.add(message);
                frame.add(phoneno);
                frame.add(phonenoget);



                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(480, 600);

                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenDimensions = toolkit.getScreenSize();
                int x = (screenDimensions.width - frame.getWidth()) / 2;
                int y = (screenDimensions.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);

                frame.setLayout(null);
                frame.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createaccount) {
            message.setText("");
            String givenname = namefield.getText();
            String firstpass = String.valueOf(passfield.getPassword());
            String secondpass = String.valueOf(confirmpasssagain.getPassword());
            String givenmail = usermailget.getText();
            Integer givenage = (Integer) ageget.getSelectedItem();
            String givenaddress = addressget.getText();
            String givencity = (String) citys.getSelectedItem();
            String givenphoneno = phonenoget.getText();

            // Check if any fields are empty
            if (givenname.isEmpty() || givenaddress.isEmpty() || givenphoneno.isEmpty() ||
                    givenmail.isEmpty() || firstpass.isEmpty() || secondpass.isEmpty()) {
                message.setText("Please fill up all the boxes to proceed.");
                return; // Exit early if fields are empty
            }

            // Check if passwords match
            if (!firstpass.equals(secondpass)) {
                message.setText("Please input the same passwords.");
                return; // Exit early if passwords do not match
            }

            // Adjusted SQL statement with correct column names
            String insertsql = "INSERT INTO signup(User_ID, User_password, User_email, Age, Address, City, Mobile_Phone) VALUES(?,?,?,?,?,?,?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(insertsql)) {

                // Set the parameters for the PreparedStatement
                pstmt.setString(1, givenname);
                pstmt.setString(2, firstpass);
                pstmt.setString(3, givenmail);
                pstmt.setInt(4, givenage);
                pstmt.setString(5, givenaddress);
                pstmt.setString(6, givencity);
                pstmt.setString(7, givenphoneno);

                // Execute the insert statement
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    message.setText("Sign Up Successful. Please close the app and log in.");
                } else {
                    message.setText("Sign Up failed. Please try again.");
                }
            } catch (SQLException r) {
                r.printStackTrace();
                message.setText("An error occurred. Please try again.");
            }
        }
    }



}
