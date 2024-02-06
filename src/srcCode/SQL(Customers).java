package sql;

import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.sql.*;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sql.EmployeeFirstFrame.EmployeeF1;
import static sql.Customers.jLabel13;
import static sql.Customers.jToggleButton1;
import static sql.Customers.VSID;
import static sql.CustomerFirstFrame.UserFFIDF;
import static sql.CustomerFirstFrame.UPassWordFFF;

/**
 *
 * @author Eskandar
 */
public class SQL {
    
    public static String url = "jdbc:sqlite:C:/DB/DB.db";

    /**
     * @param args the command line arguments
     */

    /*
    to create a location the name has to end with .db 
    the location can be C:/myDB/
     */
    public static void addUnregisterationFormUser() {
        
        String ADD_CREDITCARD_SQL = "INSERT INTO CREDITCARD "
                + "(UserCardNumber, UserCCVNumber, UserCardExpirationDate, UserID) VALUES (?, ?, ?, ?)";
        String ADD_USER_SQL = "INSERT INTO USERS "
                + "(UserID, UserPassWord, UserDOB, UserPhoneNumber, UserAddress, UserEriCode, VehicleID, UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        String password = RegisterationForm.EPassWordF.getText();;
        String CustomerID = RegisterationForm.ECIDF.getText();;
        String phoneNo = RegisterationForm.ECPhoneNOF.getText();
        String DOB = RegisterationForm.ECYearDOB.getText() + "-" + RegisterationForm.ECMonthDOB.getText() + "-"
                + RegisterationForm.ECDayDOB.getText();
        String Address = RegisterationForm.ECAddressF.getText();;
        String ERICode = RegisterationForm.ECERICodeF.getText();
        String Name = RegisterationForm.ECNameF.getText();
        String VehicleAssociated = RegisterationForm.ECVIDAWCF.getText();
        String BankCardNumber = RegisterationForm.ECCardNOF.getText();
        String CCVNumber = RegisterationForm.ECCCVNOF.getText();
        String EXPDate = RegisterationForm.ECYearDOB.getText() + "-" + RegisterationForm.ECMonthDOB.getText() + "-"
                + RegisterationForm.ECDayDOB.getText();
        
        try ( Connection connection = DriverManager.getConnection(url)) {

            // Check if the User exists
            String CHECK_USER_SQL = "SELECT * FROM USERS WHERE UserID = ?";
            PreparedStatement checUser = connection.prepareStatement(CHECK_USER_SQL);
            checUser.setString(1, CustomerID);
            ResultSet userResult = checUser.executeQuery();

            //check if the vehicle exist in the DB 
            String CHECK_Vehicle_SQL = "SELECT * FROM VEHICLES WHERE VehicleID = ?";
            
            PreparedStatement checVehicle = connection.prepareStatement(CHECK_Vehicle_SQL);
            checVehicle.setString(1, VehicleAssociated);
            ResultSet vehicleResult = checVehicle.executeQuery();

            //check if the Card exist in the database
            String CHECK_CreditCard_SQL = "SELECT * FROM CREDITCARD WHERE UserCardNumber = ?";
            
            PreparedStatement checCard = connection.prepareStatement(CHECK_CreditCard_SQL);
            checCard.setString(1, BankCardNumber);
            ResultSet CardResult = checCard.executeQuery();
            
            if (CardResult.next()) {
                
                JOptionPane.showMessageDialog(null, "The Card with number " + BankCardNumber + " Already exist in the DB.");
                return;
                
            } else if (userResult.next()) {
                
                JOptionPane.showMessageDialog(null, "The user with id " + CustomerID + " already exist in the database.");
                return;
                
            } else if (!vehicleResult.next()) {
                
                JOptionPane.showMessageDialog(null, "Please go to the VIEW-VEHICLES tab \nand choose one of the vehicles that you want to buy");
                return;
                
            } else {
                
                if (RegisterationForm.EPassWordF.getText().isEmpty() == true || RegisterationForm.ECIDF.getText().isEmpty() == true
                        || RegisterationForm.ECPhoneNOF.getText().isEmpty() == true || RegisterationForm.ECYearDOB.getText().isEmpty() == true
                        || RegisterationForm.ECMonthDOB.getText().isEmpty() == true || RegisterationForm.ECDayDOB.getText().isEmpty() == true
                        || RegisterationForm.EXPYearECF.getText().isEmpty() == true || RegisterationForm.EXPMonthECF.getText().isEmpty() == true
                        || RegisterationForm.EXPDayECF.getText().isEmpty() == true || RegisterationForm.ECCardNOF.getText().isEmpty() == true
                        || RegisterationForm.ECCCVNOF.getText().isEmpty() == true || RegisterationForm.ECNameF.getText().isEmpty()
                        || RegisterationForm.ECERICodeF.getText().isEmpty() == true || RegisterationForm.ECAddressF.getText().isEmpty() == true) {
                    
                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL);
                    preparedStatement.setString(1, CustomerID);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, DOB);
                    preparedStatement.setString(4, phoneNo);
                    preparedStatement.setString(5, Address);
                    preparedStatement.setString(6, ERICode);
                    preparedStatement.setString(7, VehicleAssociated);
                    preparedStatement.setString(8, Name);
                    
                    PreparedStatement preparedStatement1 = connection.prepareStatement(ADD_CREDITCARD_SQL);                    
                    
                    preparedStatement1.setString(2, CCVNumber);
                    preparedStatement1.setString(3, EXPDate);
                    preparedStatement1.setString(1, BankCardNumber);
                    preparedStatement1.setString(4, CustomerID);
                    
                    preparedStatement1.executeUpdate();
                    preparedStatement.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "New ID " + CustomerID + " been added to the DB");
                    
                }
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(SQLE.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }            
        }        
    }
    
    public static void addCreditCard() {
        
        String ADD_CREDITCARD_SQL = "INSERT INTO CREDITCARD "
                + "(UserCardNumber, UserCCVNumber, UserCardExpirationDate, UserID) VALUES (?, ?, ?, ?)";
        
        String CustomerID = CustomerFirstFrame.UserFFIDF.getText();
        String BankCardNumber = Customers.UCardNOF.getText();
        String CCVNumber = Customers.UCCVBankCardF.getText();
        String EXPDate = Customers.YearEXPDate.getText() + "-" + Customers.MonthUserEXPDateF.getText() + "-"
                + Customers.DayEXPDateUserF.getText();
        
        try ( Connection connection = DriverManager.getConnection(url)) {

            //check if the Card exist in the database
            String CHECK_CreditCard_SQL = "SELECT * FROM CREDITCARD WHERE UserCardNumber = ?";
            
            PreparedStatement checCard = connection.prepareStatement(CHECK_CreditCard_SQL);
            checCard.setString(1, BankCardNumber);
            ResultSet CardResult = checCard.executeQuery();
            
            if (CardResult.next()) {
                
                JOptionPane.showMessageDialog(null, "The Card with number " + BankCardNumber + " Already exist in the DB.");
                return;
                
            } else {
                
                if (Customers.UCardNOF.getText().isEmpty() == true || Customers.UCCVBankCardF.getText().isEmpty() == true
                        || Customers.YearEXPDate.getText().isEmpty() == true || Customers.MonthUserEXPDateF.getText().isEmpty() == true
                        || Customers.DayEXPDateUserF.getText().isEmpty() == true) {
                    
                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {
                    
                    PreparedStatement preparedStatement1 = connection.prepareStatement(ADD_CREDITCARD_SQL);                    
                    
                    preparedStatement1.setString(2, CCVNumber);
                    preparedStatement1.setString(3, EXPDate);
                    preparedStatement1.setString(1, BankCardNumber);
                    preparedStatement1.setString(4, CustomerID);
                    
                    preparedStatement1.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "The Card with number " + BankCardNumber + " has been added to user with ID " + CustomerID);
                    
                }
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(SQLE.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
        }        
    }
    
    public static void viewpanelVehicles() {
        
        DefaultTableModel tm = (DefaultTableModel) Customers.jTable1.getModel();
        
        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VEHICLES "); //get everything from table, result set
            tm.setRowCount(0);
            while (rs.next()) { //rs is result set, use while to iterate through it
                
                tm.addRow(new Object[]{rs.getString("VehicleID"), rs.getString("VehicleName"),
                    rs.getString("VehicleCpacity"), rs.getString("VehicleColor"), rs.getFloat("VehiclePrice"),
                     rs.getString("VehicleCondition"), rs.getString("VehicleSize"), rs.getInt("VehicleYearModel")});
                
            }//end while
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, (e.getMessage())); //if there is an error a popup is issued
            
        }//end try catch
    }
    
    public static void cleartable() {
        
        DefaultTableModel tm = (DefaultTableModel) Customers.jTable1.getModel();
        tm.setRowCount(0);
        
    }
    
    public static void viewpanelVehiclesForUnregistered() {
        
        DefaultTableModel tm = (DefaultTableModel) RegisterationForm.jTable1.getModel();
        
        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VEHICLES "); //get everything from table, result set
            tm.setRowCount(0);
            while (rs.next()) { //rs is result set, use while to iterate through it
                
                tm.addRow(new Object[]{rs.getString("VehicleID"), rs.getString("VehicleName"),
                    rs.getString("VehicleCpacity"), rs.getString("VehicleColor"), rs.getFloat("VehiclePrice"),
                     rs.getString("VehicleCondition"), rs.getString("VehicleSize"), rs.getInt("VehicleYearModel")});
                
            }//end while
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, (e.getMessage())); //if there is an error a popup is issued
            
        }//end try catch
    }
    
    public static void cleartableForUnregistered() {
        
        DefaultTableModel tm = (DefaultTableModel) RegisterationForm.jTable1.getModel();
        tm.setRowCount(0);
        
    }

    //function 1 to create file and connection, mostly used by employees jframe 
    public static void startMethod() {
        //start of button
        String fileName = EmployeeDB.jTextField1.getText(); //gets the DB name from the textbox
        String Dir = EmployeeDB.jTextField2.getText(); //gets the DB location from the textbox
        File directory = new File(Dir);
        url = "jdbc:sqlite:" + Dir + fileName; //creates a URL for the database

        try { //loads driver into memory, just one way of doing it
            
            Class.forName("org.sqlite.JDBC").newInstance();            
            
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage()); //println for catching error, popup would be better
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
        }//end try catch
        
        if (!directory.exists()) {
            
            directory.mkdir(); //creates the folder were the DB will be saved.
            UserIdentifier.main(null);

            //Makes a connection called conn to the url created earlier
            try ( Connection conn = DriverManager.getConnection(url)) {
                
                if (conn != null) { //if the connection is not null 
                    
                    EmployeeDB.jButton1.setText("You are allowed to populate the DB only once!"); //change the text on the create DB button

                }//end if
                else {
                    
                    JOptionPane.showMessageDialog(null, "Hmm... something went wrong, please try again!");
                    EmployeeDB.main(null);
                    
                }
                
            } catch (SQLException e) {
                
                JOptionPane.showMessageDialog(null, e.getMessage()); //println for catching error, popup would be better

                JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
                
                int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
                
                if (X == 0) {
                    
                    System.exit(0);
                    
                }
                
            }//end try catch
            
            ScriptRunner();
            
        } else {
            
            JOptionPane.showMessageDialog(null, "Hmm... our system detected that the DB been created before! please choose a different directory.");
            EmployeeDB.main(null);
            
        }
    }

    //function 2 making connection by script runner to get the SQL code running 
    public static void ScriptRunner() {
        
        try {
            
            DriverManager.registerDriver(new org.sqlite.JDBC());
            Connection conn = DriverManager.getConnection(url);
            ScriptRunner runner = new ScriptRunner(conn, false, false);
            Reader reader = new BufferedReader(new FileReader("src/sql/SQLCode.sql"));
            runner.runScript(reader);
            
        } catch (Exception e) {
            
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
        }
        
    }

    //function 3 log in verification for Customers and Employees 
    public static void loginVerificationforCustomer() {
        
        try ( Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/DB/DB.db")) {            
            
            String UserID = UserFFIDF.getText();            
            String UserPassWord = UPassWordFFF.getText();
            
            PreparedStatement ID = conn.prepareStatement("select * from users where UserID=?");            
            PreparedStatement PassWord = conn.prepareStatement("select * from users where UserPassWord=?");            
            ID.setString(1, UserID);            
            PassWord.setString(1, UserPassWord);
            //Excuting Query  
            ResultSet rsForID = ID.executeQuery();            
            ResultSet rsForPassWord = PassWord.executeQuery();            
            
            if (rsForID.next() && rsForPassWord.next()) {
                
                System.out.println("Welcome, admin!");
                //CustomerFirstFrame.
                Customers.main(null);
                UserFFIDF.setEditable(false);
                
            } else {                
                
                JOptionPane.showMessageDialog(null, "Hmmm... seems you haven't typed the right info!");                
                System.exit(0);
                
            }            
        } catch (Exception e) {            
            
            JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
            CustomerFirstFrame.main(null);
            
        }//end try catch
    }

    //====================================================================
    public static void loginVerificationforEmployees() {
        
        try {
            
            Class.forName("org.sqlite.JDBC").newInstance();
            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            
            System.out.println("WTF" + ex);
            
        }
        
        try ( Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/DB/DB.db")) {            
            
            String EmployeeID = EmployeeF1.getText();            
            
            PreparedStatement ID = conn.prepareStatement("select * from invoice where WorkerID=?");            
            ID.setString(1, EmployeeID);
            //Excuting Query  
            ResultSet rsForID = ID.executeQuery();            
            
            if (rsForID.next()) { //iterate rs
                
                System.out.println("Welcome, admin!");
                //CustomerFirstFrame.
                Employees.main(null);
                
            } else {                
                
                JOptionPane.showMessageDialog(null, "Hmmm... seems you haven't typed the right info!");                
                
            } //end if else  
            //Create Exception Handler  
        } catch (Exception e) {            
            
            JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
            EmployeeFirstFrame.main(null);
            
        }//end try catch
        
    }
    
    public static void jSlider2StateChanged() {

        //jLabel7.setText(Integer.toString(jSlider1.getValue()));
    }
    
    public static void update() {
        
        int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "update record", JOptionPane.YES_NO_OPTION);
        
        if (X == 0) {
            
            String ExpirationDateUserCard = Customers.YearEXPDate.getText() + "-" + Customers.MonthUserEXPDateF.getText()
                    + "-" + Customers.DayEXPDateUserF.getText();
            String UserDOB = Customers.UDOBLYear.getText() + "-" + Customers.UMonthDOBF.getText() + "-"
                    + Customers.UDayDOBF.getText();
            
            try ( Connection conn = DriverManager.getConnection(url)) {

                //Statement – Used to execute string-based SQL queries
                Statement statement = conn.createStatement();
                Statement statement1 = conn.createStatement();

                //maybe statement.executeUpdate? execute also works
                if (Customers.UPhoneNoF.getText().isEmpty() == true || Customers.UAddressF.getText().isEmpty() == true
                        || Customers.UERICodeF.getText().isEmpty() == true || Customers.UERICodeF.getText().isEmpty() == true
                        || Customers.UPasswordF.getText().isEmpty() == true || Customers.UCardNOF.getText().isEmpty() == true
                        || Customers.YearEXPDate.getText().isEmpty() == true || Customers.MonthUserEXPDateF.getText().isEmpty() == true
                        || Customers.DayEXPDateUserF.getText().isEmpty() == true || Customers.UDOBLYear.getText().isEmpty() == true
                        || Customers.UMonthDOBF.getText().isEmpty() == true || Customers.UDayDOBF.getText().isEmpty()
                        || Customers.UCCVBankCardF.getText().isEmpty() == true || Customers.UserNameF.getText().isEmpty() == true) {
                    
                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {
                    
                    statement.execute("UPDATE USERS SET UserName='"
                            + Customers.UserNameF.getText() + "',UserAddress='" + Customers.UAddressF.getText()
                            + "',UserEriCode='" + Customers.UERICodeF.getText()
                            + "',UserPhoneNumber='" + Customers.UPhoneNoF.getText()
                            + "',UserDOB='" + UserDOB
                            + "',UserPassWord='" + Customers.UPasswordF.getText()
                            + "' WHERE UserID=" + CustomerFirstFrame.UserFFIDF.getText() + "");
                    
                    statement1.execute("UPDATE CREDITCARD SET UserCardNumber='"
                            + Customers.UCardNOF.getText()
                            + "',UserCardExpirationDate='" + ExpirationDateUserCard
                            + "',UserCCVNumber='" + Customers.UCCVBankCardF.getText()
                            + "' WHERE UserID=" + CustomerFirstFrame.UserFFIDF.getText() + "");
                    
                    JOptionPane.showMessageDialog(null, "Record updated...");
                    
                }
                //end of update button
            } catch (Exception e) {                
                
                JOptionPane.showMessageDialog(null, (e.getMessage()));
                
                JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
                
                int Xm = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
                
                if (Xm == 0) {
                    
                    System.exit(0);
                    
                }
                
            } //end try catch
        }
    }
    
    public static void viewforCustomers() {
        
        String id = Customers.VSID.getText();
        
        Customers.jTextArea3.setText(null);        
        
        if (Customers.VSID.getText().isEmpty() == true) {
            
            JOptionPane.showMessageDialog(null, "The vehicle-ID field is empty!");
            
        } else {
            
            try ( Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/DB/DB.db")) {                
                
                PreparedStatement ID = conn.prepareStatement("select * from VEHICLES where VehicleID=?");                
                ID.setString(1, id);                
                
                ResultSet rsForID = ID.executeQuery();                
                
                if (rsForID.next()) { //iterate rs
                    
                    String[] columns = {"ID", "Name", "Cpacity", "Color",
                        "Price", "Condition", "Size", "YearModel"};
                    int[] maxLengths = new int[columns.length];
                    
                    for (int i = 0; i < columns.length; i++) {
                        
                        maxLengths[i] = columns[i].length();
                        
                    }

                    // Try and make a connection to the DB using the global URL variable
                    // Statement – Used to execute string-based SQL queries
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM VEHICLES WHERE VehicleID='" + id + "'");                    
                    
                    while (rs.next()) {                        
                        
                        String[] values = {
                            rs.getString("VehicleID"),
                            rs.getString("VehicleName"),
                            rs.getString("VehicleCpacity"),
                            rs.getString("VehicleColor"),
                            rs.getString("VehiclePrice"),
                            rs.getString("VehicleCondition"),
                            rs.getString("VehicleSize"),
                            rs.getString("VehicleYearModel")
                        
                        };
                        
                        for (int i = 0; i < values.length; i++) {
                            
                            maxLengths[i] = Math.max(maxLengths[i], values[i].length());
                            
                        }
                    }

                    // Print header
                    StringBuilder sb = new StringBuilder();
                    
                    for (int i = 0; i < columns.length; i++) {
                        
                        sb.append(String.format("%-" + maxLengths[i] + "s\t", "||" + columns[i]));
                        
                    }
                    
                    Customers.jTextArea3.append("Luckly, the vehicle is available for purchase.\n\n" + " " + sb.toString().trim()
                            + "\n" + "=================================================================="
                            + "=======================\n");

                    // Print data
                    rs = stmt.executeQuery("SELECT * FROM VEHICLES WHERE VehicleID='" + id + "'");
                    
                    while (rs.next()) {
                        
                        String[] values = {
                            rs.getString("VehicleID"),
                            rs.getString("VehicleName"),
                            rs.getString("VehicleCpacity"),
                            rs.getString("VehicleColor"),
                            rs.getString("VehiclePrice"),
                            rs.getString("VehicleCondition"),
                            rs.getString("VehicleSize"),
                            rs.getString("VehicleYearModel")
                        
                        };
                        
                        sb = new StringBuilder();
                        
                        for (int i = 0; i < values.length; i++) {
                            
                            sb.append(String.format("%-" + maxLengths[i] + "s\t", "|| " + values[i]));
                            
                        }
                        
                        Customers.jTextArea3.append(sb.toString().trim() + "\n");
                        Customers.jButton7.setEnabled(true);
                    }
                } else {                    
                    
                    JOptionPane.showMessageDialog(null, "Make sure to type the correct ID"); //error getting rs

                } //end if else  
            } catch (Exception e) {                
                
                JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try

                JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
                
                int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
                
                if (X == 0) {
                    
                    System.exit(0);
                    
                }
                
            }//end try catch
            
        }
    }

    //this method is for NewFrameHelp
    public static void viewCustomerDetails() {
        
        try ( Connection conn = DriverManager.getConnection(url)) {            
            
            String str = Customers.UIDF.getText();
            //PreparedStatement – Used to execute parameterized SQL queries
            PreparedStatement st = conn.prepareStatement("select * from USERS where UserID=?");            
            PreparedStatement st1 = conn.prepareStatement("select * from CREDITCARD where UserID=?");
            st.setString(1, str); //pass id string to statement, why 1? multiple statements? location?
            st1.setString(1, str);
            //Excuting Query  
            ResultSet rs = st.executeQuery(); //get result set rs by executing the Prepared Statement
            ResultSet rs1 = st1.executeQuery();
            
            if (rs.next()) { //iterate rs
                /*
                UserID Char NOT NULL,
                UserPassWord Varchar NOT NULL,
                UserDOB Date NOT NULL,
                UserPhoneNumber int NOT NULL,
                UserAddress Varchar NOT NULL,
                UserEriCode Varchar NOT NULL,
                VehicleID Char NOT NULL,
                UserName Varchar NOT NULL,
                 */
                //From USERS database 
                String s2 = rs.getString(2);//password
                String s3 = rs.getString(3);//DOB
                String s4 = rs.getString(4);//Phone Number
                String s5 = rs.getString(5); //Address
                String s6 = rs.getString(6);//EriCode 
                String s8 = rs.getString(8);//username 
                //from CREDITCARD database 
                String D1 = rs1.getString(1);//card number
                String D2 = rs1.getString(2);//UserCCVNumber
                String D3 = rs1.getString(3);//UserCardExpirationDate

                //Sets Records in TextFields.  
                Customers.UserNameF.setText(s8);                
                Customers.UAddressF.setText(s5);                
                Customers.UERICodeF.setText(s6);                
                Customers.UPhoneNoF.setText(s4);                
                Customers.UPasswordF.setText(s2);
                Customers.UDOFL.setText(s3);
                Customers.UCardNOF.setText(D1);
                Customers.UCardEXPDateL.setText(D3);
                Customers.UCCVBankCardF.setText(D2);
                
                Customers.jButton2.setEnabled(true);
                Customers.jButton16.setEnabled(true);
                
            } else {                
                
                JOptionPane.showMessageDialog(null, "Seems you have not clicked on the ID field yet! try again!"); //error getting rs
                
            } //end if else  
            //Create Exception Handler  
        } catch (Exception e) {            
            
            JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
        }//end try catch
        
    }
    
    public static void ClearCustomerFields() {
        
        Customers.UPhoneNoF.setText(null);
        Customers.UAddressF.setText(null);
        Customers.UERICodeF.setText(null);
        Customers.UERICodeF.setText(null);
        Customers.UPasswordF.setText(null);
        Customers.UCardNOF.setText(null);
        Customers.UCCVBankCardF.setText(null);
        Customers.UDOFL.setText("YYYY-MM-DD");
        Customers.UCardEXPDateL.setText("YYYY-MM-DD");
        Customers.DayEXPDateUserF.setText(null);
        Customers.MonthUserEXPDateF.setText(null);
        Customers.YearEXPDate.setText(null);
        Customers.UDayDOBF.setText(null);
        Customers.UMonthDOBF.setText(null);
        Customers.UDOBLYear.setText(null);
        Customers.UserNameF.setText(null);
        Customers.jLabel13.setText("");
        
    }
    
    public static void deleteUser() {
        
        String ID = Customers.UIDF.getText();
        String query = "DELETE FROM USERS WHERE UserID='" + ID + "'";
        
        try ( Connection conn = DriverManager.getConnection(url);  Statement statement = conn.createStatement()) {
            
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "The user with UserID '" + ID + "' has been deleted!");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, (e.getMessage()));
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
        }
        
    }
    
    public static void ToggleBTNForPassWord() {
        
        if (jToggleButton1.isSelected() == true) {
            
            jLabel13.setText(new String(Customers.UPasswordF.getPassword()));
            jToggleButton1.setText("Hide-password");
            
        } else if (jToggleButton1.isSelected() == false) {
            
            jLabel13.setText("Password is hidden for security reasons");
            jToggleButton1.setText("Show-password");
            
        }
    }
    
    public static void viewVehicleforPurchse() {
        
        try ( Connection conn = DriverManager.getConnection(url)) {            
            
            String str = VSID.getText();            
            
            PreparedStatement st = conn.prepareStatement("select * from VEHICLES where VehicleID=?");            
            st.setString(1, str);
            //Excuting Query  
            ResultSet rs = st.executeQuery(); //get result set rs by executing the Prepared Statement

            if (rs.next()) { //iterate rs
                String s1 = rs.getString(2);                
                String s2 = rs.getString(3);                
                String s3 = rs.getString(4);
                String s4 = rs.getString(5);
                String s5 = rs.getString(6);
                String s6 = rs.getString(7);
                String s7 = rs.getString(8);
                //Sets Records in TextFields.  
                CustomerPurchaseConfirmation.jTextField2.setText(s1);                
                CustomerPurchaseConfirmation.jTextField6.setText(s2);                
                CustomerPurchaseConfirmation.jTextField5.setText(s3);
                CustomerPurchaseConfirmation.jTextField3.setText(s4);
                CustomerPurchaseConfirmation.jTextField7.setText(s5);
                CustomerPurchaseConfirmation.jTextField4.setText(s6);
                CustomerPurchaseConfirmation.jTextField8.setText(s7);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "ERROR");
                
            } //end if else  
        } catch (Exception e) {            
            
            JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
            
        }//end try catch  
        
    }
    
    public static void ConfirmToPurchase() {
        
        String UserID = CustomerFirstFrame.UserFFIDF.getText();
        String VehicleID = Customers.VSID.getText();        
        
        JOptionPane.showMessageDialog(null, "The User with the ID " + UserID + " has purchased the vehicle with the ID " + VehicleID);
        Customers.jTextArea3.setText(null);
        Customers.VSID.setText(null);
        
    }
    
    public static void addUser() {
        
        String ADD_USER_SQL = "INSERT INTO USERS "
                + "(UserID, UserPassWord, UserDOB, UserPhoneNumber, UserAddress, UserEriCode, VehicleID, UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        String password = Employees.EPassWordF.getText();;
        String CustomerID = Employees.ECIDF.getText();;
        String phoneNo = Employees.ECPhoneNOF.getText();
        String DOB = Employees.ECYearDOB.getText() + "-" + Employees.ECMonthDOB.getText() + "-"
                + Employees.ECDayDOB.getText();
        String Address = Employees.ECAddressF.getText();;
        String ERICode = Employees.ECERICodeF.getText();
        String Name = Employees.ECNameF.getText();
        String VehicleAssociated = Employees.ECVIDAWCF.getText();
        
        try ( Connection connection = DriverManager.getConnection(url)) {

            // Check if the User exists
            String CHECK_USER_SQL = "SELECT * FROM USERS WHERE UserID = ?";
            PreparedStatement checUser = connection.prepareStatement(CHECK_USER_SQL);
            checUser.setString(1, CustomerID);
            ResultSet userResult = checUser.executeQuery();

            //check if the vehicle exist in the DB 
            String CHECK_Vehicle_SQL = "SELECT * FROM VEHICLES WHERE VehicleID = ?";
            
            PreparedStatement checVehicle = connection.prepareStatement(CHECK_Vehicle_SQL);
            checVehicle.setString(1, VehicleAssociated);
            ResultSet vehicleResult = checVehicle.executeQuery();
            
            if (userResult.next()) {
                
                JOptionPane.showMessageDialog(null, "The user with id " + CustomerID + " already exist in the database.");
                return;
                
            } else if (!vehicleResult.next()) {
                
                JOptionPane.showMessageDialog(null, "The customer needs to be associated with a vehicle ID.\n"
                        + "Here is a tip on what you can do: Either enter the correct Vehicle-ID or\n"
                        + "Create a new vehicle in MANAGE-VEHICLE tab to associate it with the new user.");
                return;
                
            } else {
                
                if (Employees.EPassWordF.getText().isEmpty() == true || Employees.ECIDF.getText().isEmpty() == true
                        || Employees.ECPhoneNOF.getText().isEmpty() == true || Employees.ECYearDOB.getText().isEmpty() == true
                        || Employees.ECMonthDOB.getText().isEmpty() == true || Employees.ECDayDOB.getText().isEmpty() == true
                        || Employees.EXPYearECF.getText().isEmpty() == true || Employees.EXPMonthECF.getText().isEmpty() == true
                        || Employees.EXPDayECF.getText().isEmpty() == true || Employees.ECCardNOF.getText().isEmpty() == true
                        || Employees.ECCCVNOF.getText().isEmpty() == true || Employees.ECNameF.getText().isEmpty()
                        || Employees.ECERICodeF.getText().isEmpty() == true || Employees.ECAddressF.getText().isEmpty() == true) {
                    
                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL);
                    preparedStatement.setString(1, CustomerID);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, DOB);
                    preparedStatement.setString(4, phoneNo);
                    preparedStatement.setString(5, Address);
                    preparedStatement.setString(6, ERICode);
                    preparedStatement.setString(7, VehicleAssociated);
                    preparedStatement.setString(8, Name);
                    
                    preparedStatement.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "New ID " + CustomerID + " been added to the DB");
                    
                }
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(SQLE.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");
            
            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);
            
            if (X == 0) {
                
                System.exit(0);
                
            }
        }        
        
    }
}
