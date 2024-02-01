package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sql.Employees.EUPassWordL;
import static sql.Employees.EUToggleBTNPassWord;
import static sql.SQL.url;

/**
 *
 * @author escan
 */
public class SQLE {

    public static String url = "jdbc:sqlite:C:/DB/DB.db";

    public static void viewpanelVehicles() {

        //jtable must be public static, viewtable is the jtable swing name
        //you must also edit the table contents-columns, usually they would mach what comes from the resultset
        //Since my jtable is not used to edit data, strings are fine
        DefaultTableModel tm = (DefaultTableModel) Employees.VehiclesPanel.getModel();

        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VEHICLES "); //get everything from table, result set
            tm.setRowCount(0);
            while (rs.next()) { //rs is result set, use while to iterate through it

                tm.addRow(new Object[]{rs.getString("VehicleID"), rs.getString("VehicleName"),
                    rs.getInt("VehicleCpacity"), rs.getString("VehicleColor"), rs.getFloat("VehiclePrice"),
                     rs.getString("VehicleCondition"), rs.getString("VehicleSize"), rs.getInt("VehicleYearModel")});

            }//end while

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //if there is an error a popup is issued

        }//end try catch
    }

    public static void cleartableVehicles() {

        DefaultTableModel tm = (DefaultTableModel) Employees.VehiclesPanel.getModel();
        tm.setRowCount(0);

    }

    public static void viewpanelInvoices() {

        DefaultTableModel tm = (DefaultTableModel) Employees.InvoicesPanel.getModel();

        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM INVOICE "); //get everything from table, result set
            tm.setRowCount(0);
            while (rs.next()) { //rs is result set, use while to iterate through it

                tm.addRow(new Object[]{rs.getInt("WorkerID"), rs.getString("WorkerName"),
                    rs.getString("InvoiceID"), rs.getString("InvoiceDate"), rs.getString("VehicleID"),
                     rs.getString("UserID"), rs.getString("UserCardNumber")});

            }//end while

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //if there is an error a popup is issued

        }//end try catch
    }

    public static void cleartableInvoices() {

        DefaultTableModel tm = (DefaultTableModel) Employees.InvoicesPanel.getModel();
        tm.setRowCount(0);

    }

    public static void viewpanelCustomers() {

        DefaultTableModel tm = (DefaultTableModel) Employees.CustomerPanel.getModel();

        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS "); //get everything from table, result set
            tm.setRowCount(0);
            while (rs.next()) { //rs is result set, use while to iterate through it

                tm.addRow(new Object[]{rs.getString("UserID"), rs.getString("UserPassWord"),
                    rs.getString("UserDOB"), rs.getInt("UserPhoneNumber"), rs.getString("UserAddress"),
                     rs.getString("UserEriCode"), rs.getString("VehicleID"), rs.getString("UserName")});

            }//end while

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //if there is an error a popup is issued

        }//end try catch
    }

    public static void cleartableCustomers() {

        DefaultTableModel tm = (DefaultTableModel) Employees.CustomerPanel.getModel();
        tm.setRowCount(0);

    }

    public static void cleartable() {

        DefaultTableModel tm = (DefaultTableModel) Customers.jTable1.getModel();
        tm.setRowCount(0);

    }

    public static void viewpanelCreditCard() {

        DefaultTableModel tm = (DefaultTableModel) Employees.VehiclesPanel1.getModel();

        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CREDITCARD "); //get everything from table, result set
            tm.setRowCount(0);
            while (rs.next()) { //rs is result set, use while to iterate through it

                tm.addRow(new Object[]{rs.getString("UserCardNumber"), rs.getInt("UserCCVNumber"),
                    rs.getString("UserCardExpirationDate"), rs.getFloat("UserID")});

            }//end while

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //if there is an error a popup is issued

        }//end try catch
    }

    public static void cleartableCreditCard() {

        DefaultTableModel tm = (DefaultTableModel) Employees.VehiclesPanel1.getModel();
        tm.setRowCount(0);

    }

    public static void updateUsersforEmployees() {

        String ExpirationDateUserCard = Employees.EXPYearECF.getText() + "-" + Employees.EXPMonthECF.getText()
                + "-" + Employees.EXPDayECF.getText();
        String UserDOB = Employees.ECYearDOB.getText() + "-" + Employees.ECMonthDOB.getText() + "-"
                + Employees.ECDayDOB.getText();

        //Start of update button
        //update will not change ID, db constraint? maybe because UPDATE statement?
        //try and make a connection to the DB using the gloabal URL variable
        //The connection and statements objects might be better if global?
        try ( Connection conn = DriverManager.getConnection(url)) {

            String str = Employees.ECIDF.getText();
            String VehicleID = Employees.ECVIDAWCF.getText();

            //PreparedStatement – Used to execute parameterized SQL queries
            PreparedStatement st = conn.prepareStatement("select * from USERS where UserID=?");
            PreparedStatement st1 = conn.prepareStatement("select * from CREDITCARD where UserID=?");
            PreparedStatement st2 = conn.prepareStatement("select * from VEHICLES where VehicleID=?");

            st.setString(1, str);
            st1.setString(1, str);
            st2.setString(1, VehicleID);
            //Excuting Query  
            ResultSet rs = st.executeQuery();
            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();

            if (!rs.next()) {

                JOptionPane.showMessageDialog(null, "Make sure you have the right info in the ID field."); //let user know

            } else if (!rs2.next()) {

                JOptionPane.showMessageDialog(null, "write the correct vehicle ID."); //let user know

            } else if (!rs1.next()) {

                JOptionPane.showMessageDialog(null, "Make sure you have the right info in the ID field."); //let user know

            } else {

                //Statement – Used to execute string-based SQL queries
                Statement statement = conn.createStatement();
                Statement statement1 = conn.createStatement();

                if (Employees.EPassWordF.getText().isEmpty() == true || Employees.ECIDF.getText().isEmpty() == true
                        || Employees.ECPhoneNOF.getText().isEmpty() == true || Employees.ECYearDOB.getText().isEmpty() == true
                        || Employees.ECMonthDOB.getText().isEmpty() == true || Employees.ECDayDOB.getText().isEmpty() == true
                        || Employees.EXPYearECF.getText().isEmpty() == true || Employees.EXPMonthECF.getText().isEmpty() == true
                        || Employees.EXPDayECF.getText().isEmpty() == true || Employees.ECCardNOF.getText().isEmpty() == true
                        || Employees.ECCCVNOF.getText().isEmpty() == true || Employees.ECNameF.getText().isEmpty()
                        || Employees.ECERICodeF.getText().isEmpty() == true || Employees.ECAddressF.getText().isEmpty() == true) {

                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {

                    statement.execute("UPDATE USERS SET UserName='"
                            + Employees.ECNameF.getText() + "',UserAddress='" + Employees.ECAddressF.getText()
                            + "',UserEriCode='" + Employees.ECERICodeF.getText()
                            + "',UserPhoneNumber='" + Employees.ECPhoneNOF.getText()
                            + "',UserDOB='" + UserDOB
                            + "',UserPassWord='" + Employees.EPassWordF.getText()
                            + "' WHERE UserID=" + Employees.ECIDF.getText() + "");

                    statement1.execute("UPDATE CREDITCARD SET UserCardNumber='"
                            + Employees.ECCardNOF.getText()
                            + "',UserCardExpirationDate='" + ExpirationDateUserCard
                            + "',UserCCVNumber='" + Employees.ECCCVNOF.getText()
                            + "' WHERE UserID=" + Employees.ECIDF.getText() + "");

                    JOptionPane.showMessageDialog(null, "Record updated..."); //let user know

                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //show real error message, good for testing, not user friendly 

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Is the data wanted chosen carefully?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        } //end try catch

    }

    public static void viewCustomerDetailsforEmployees() {

        try ( Connection conn = DriverManager.getConnection(url)) {

            String str = Employees.ECIDF.getText();
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
                Employees.ECNameF.setText(s8);
                Employees.ECAddressF.setText(s5);
                Employees.ECERICodeF.setText(s6);
                Employees.ECPhoneNOF.setText(s4);
                Employees.EPassWordF.setText(s2);
                Employees.EUDOBFORMATL.setText(s3);
                Employees.ECCardNOF.setText(D1);
                Employees.EUEXPFORMATL.setText(D3);
                Employees.ECCCVNOF.setText(D2);
                Employees.ECVIDAWCF.setText(rs.getString(7));

            } else {

                JOptionPane.showMessageDialog(null, "Error! Make sure that ID exists in the DB."); //error getting rs

            } //end if else  
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        }//end try catch

    }

    public static void deleteUserforEmployees() {

        String userId = Employees.ECIDF.getText();
        String query = "DELETE FROM USERS WHERE UserID='" + userId + "'";

        try ( Connection conn = DriverManager.getConnection(url);  Statement statement = conn.createStatement()) {

            PreparedStatement st1 = conn.prepareStatement("select * from CREDITCARD where UserID=?");
            st1.setString(1, userId);
            //Excuting Query  
            ResultSet rs1 = st1.executeQuery();

            if (!rs1.next()) {

                JOptionPane.showMessageDialog(null, "Make sure you entered the right data.");

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

                    statement.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "The user with UserID " + userId + " has been deleted!");

                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, (e.getMessage()));

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        }

    }

    public static void ClearCustomerFields() {

        Employees.ECNameF.setText(null);;
        Employees.ECAddressF.setText(null);;
        Employees.ECERICodeF.setText(null);;
        Employees.ECPhoneNOF.setText(null);;
        Employees.EPassWordF.setText(null);;
        Employees.EUDOBFORMATL.setText("YYYY-MM-DD");
        Employees.ECCardNOF.setText(null);
        Employees.EUEXPFORMATL.setText("YYYY-MM-DD");
        Employees.ECCCVNOF.setText(null);;
        Employees.ECYearDOB.setText(null);
        Employees.ECMonthDOB.setText(null);
        Employees.ECDayDOB.setText(null);
        Employees.EXPMonthECF.setText(null);
        Employees.EXPDayECF.setText(null);
        Employees.EXPYearECF.setText(null);
        Employees.EUPassWordL.setText("Password is hidden for security reasons");

    }

    public static void ToggleBTNForPassWord() {

        if (EUToggleBTNPassWord.isSelected() == true) {

            EUPassWordL.setText(new String(Employees.EPassWordF.getPassword()));
            EUToggleBTNPassWord.setText("Hide-password?");

        } else if (EUToggleBTNPassWord.isSelected() == false) {

            EUPassWordL.setText("Password is hidden for security reasons");
            EUToggleBTNPassWord.setText("Show-password?");

        }
    }

    public static void viewVehicleforEdit() {

        try ( Connection conn = DriverManager.getConnection(url)) {

            String str = Employees.PVIDFC.getText();

            //PreparedStatement – Used to execute parameterized SQL queries
            PreparedStatement st = conn.prepareStatement("select * from VEHICLES where VehicleID=?");
            st.setString(1, str); //pass id string to statement, why 1? multiple statements? location?
            //Excuting Query  
            ResultSet rs = st.executeQuery(); //get result set rs by executing the Prepared Statement

            if (rs.next()) { //iterate rs
                String s1 = rs.getString(2); //get name from rs, save as s1 
                String s2 = rs.getString(3); //get capacity from rs, save as s2
                String s3 = rs.getString(4);
                String s4 = rs.getString(5);
                String s5 = rs.getString(6);
                String s6 = rs.getString(7);
                String s7 = rs.getString(8);
                //Sets Records in TextFields.  
                Employees.VName.setText(s1);  //set name in textbox 
                Employees.VCapacity.setText(s2);  //set capacity in textbox 
                Employees.VCOLOR.setText(s3);
                Employees.VPrice.setText(s4);
                Employees.VCondition.setText(s5);
                Employees.VSize.setText(s6);
                Employees.VYearModel.setText(s7);

            } else {

                JOptionPane.showMessageDialog(null, "Error! Make sure to enter correct data."); //error getting rs

            } //end if else  
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //error from try

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        }//end try catch  

    }

    public static void ClearVFieldsForEmployees() {

        Employees.VName.setText(null);
        Employees.VCapacity.setText(null);
        Employees.VCOLOR.setText(null);
        Employees.VPrice.setText(null);
        Employees.VCondition.setText(null);
        Employees.VSize.setText(null);
        Employees.VYearModel.setText(null);
        Employees.PVIDFC.setText(null);

    }

    public static void deleteVehiclesForEmployees() {

        String VehicleId = Employees.PVIDFC.getText();
        String query = "DELETE FROM VEHICLES WHERE VehicleID='" + VehicleId + "'";

        try ( Connection conn = DriverManager.getConnection(url);  Statement statement = conn.createStatement()) {

            PreparedStatement st = conn.prepareStatement("select * from VEHICLES where VehicleID=?");

            st.setString(1, VehicleId);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {

                JOptionPane.showMessageDialog(null, "Make sure you have the right info in the ID field."); //let user know

            } else {

                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "The Vehicle with ID '" + VehicleId + "' has been deleted!");

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, (e.getMessage()));

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        }

    }

    public static void updateVehiclesForEmployees() {

        try ( Connection conn = DriverManager.getConnection(url)) {

            //Statement – Used to execute string-based SQL queries
            Statement statement = conn.createStatement();

            String VehicleID = Employees.PVIDFC.getText();

            PreparedStatement st = conn.prepareStatement("select * from VEHICLES where VehicleID=?");

            st.setString(1, VehicleID);
            ResultSet rsVehicleID = st.executeQuery();

            if (!rsVehicleID.next()) {

                JOptionPane.showMessageDialog(null, "Hmm... Make sure you have the right ID. Try again!");

            } else {

                if (Employees.VCOLOR.getText().isEmpty() == true || Employees.PVIDFC.getText().isEmpty() == true
                        || Employees.VCapacity.getText().isEmpty() == true || Employees.VName.getText().isEmpty() == true
                        || Employees.VCondition.getText().isEmpty() == true || Employees.VPrice.getText().isEmpty() == true
                        || Employees.VYearModel.getText().isEmpty() == true || Employees.VSize.getText().isEmpty() == true) {

                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {

                    statement.execute("UPDATE VEHICLES SET VehicleName='"
                            + Employees.VName.getText()
                            + "',VehicleCpacity='" + Employees.VCapacity.getText()
                            + "',VehicleColor='" + Employees.VCOLOR.getText()
                            + "',VehiclePrice='" + Employees.VPrice.getText()
                            + "',VehicleCondition='" + Employees.VCondition.getText()
                            + "',VehicleSize='" + Employees.VSize.getText()
                            + "',VehicleYearModel='" + Employees.VYearModel.getText()
                            + "' WHERE VehicleID=" + Employees.PVIDFC.getText() + "");

                    JOptionPane.showMessageDialog(null, "Record updated..."); //let user know

                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Hmm... something went wrong! Or in other words \n" + (e.getMessage())); //show real error message, good for testing, not user friendly 

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        } //end try catch

    }

    /////Test method here by me 
    public static void addUser() {

        String ADD_CREDITCARD_SQL = "INSERT INTO CREDITCARD "
                + "(UserCardNumber, UserCCVNumber, UserCardExpirationDate, UserID) VALUES (?, ?, ?, ?)";
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
        String BankCardNumber = Employees.ECCardNOF.getText();
        String CCVNumber = Employees.ECCCVNOF.getText();
        String EXPDate = Employees.ECYearDOB.getText() + "-" + Employees.ECMonthDOB.getText() + "-"
                + Employees.ECDayDOB.getText();

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

    public static void AddNewVehicleForEmployees() {

        try ( Connection conn = DriverManager.getConnection(url)) {

            Statement statement = conn.createStatement();

            String VehicleID = Employees.PVIDFC.getText();

            PreparedStatement st = conn.prepareStatement("select * from VEHICLES where VehicleID=?");

            st.setString(1, VehicleID);

            ResultSet rsVehicleID = st.executeQuery();

            if (rsVehicleID.next()) {

                JOptionPane.showMessageDialog(null, "Seems the ID already exists in the DB!\n"
                        + "Here is what you can do:\n"
                        + "Try to choose a different ID for the vehicle\n"
                        + "or try to update the existing vehicle instead."); //let user know

            } else {

                if (Employees.VCOLOR.getText().isEmpty() == true || Employees.PVIDFC.getText().isEmpty() == true
                        || Employees.VCapacity.getText().isEmpty() == true || Employees.VName.getText().isEmpty() == true
                        || Employees.VCondition.getText().isEmpty() == true || Employees.VPrice.getText().isEmpty() == true
                        || Employees.VYearModel.getText().isEmpty() == true || Employees.VSize.getText().isEmpty() == true) {

                    JOptionPane.showMessageDialog(null, "Fields can not be empty!"); //let user know

                } else {

                    statement.executeUpdate("INSERT INTO VEHICLES VALUES("
                            + Employees.PVIDFC.getText() + ",'"
                            + Employees.VName.getText() + "','"
                            + Employees.VCapacity.getText() + "','"
                            + Employees.VCOLOR.getText() + "','"
                            + Employees.VPrice.getText() + "','"
                            + Employees.VCondition.getText() + "','"
                            + Employees.VSize.getText() + "','"
                            + Employees.VYearModel.getText() + "')");

                    JOptionPane.showMessageDialog(null, "Record inserted..."); //let user know 

                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, (e.getMessage())); //show message, good for testing, not user friendly

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Is the data wanted chosen carefully?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        } //end try catch

    }

    public static void clearInvoiceFields() {

        Employees.InvoiceIDF.setText(null);
        Employees.VIDInvoiceF.setText(null);
        Employees.UserIDInvoiceF.setText(null);
        Employees.year.setText(null);
        Employees.month.setText(null);
        Employees.day.setText(null);
        Employees.cardnumberInvoiceF.setText(null);
        Employees.invoiceWorkerNameF.setText(null);
        Employees.WorkerNameInvoiceF.setText(null);

    }

    /////test method by me 
    public static void addInvoices() {

        String ADD_INVOICE_SQL = "INSERT INTO INVOICE "
                + "(WorkerID, WorkerName, InvoiceID, InvoiceDate, VehicleID, UserID, UserCardNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String InvoiceID = Employees.InvoiceIDF.getText();
        String VehicleID = Employees.VIDInvoiceF.getText();
        String UserID = Employees.UserIDInvoiceF.getText();
        String InvoiceDate = Employees.year.getText() + "-" + Employees.month.getText() + "-"
                + Employees.day.getText();
        String UserCardNumber = Employees.cardnumberInvoiceF.getText();
        String WorkerID = Employees.invoiceWorkerNameF.getText();
        String WorkerName = Employees.WorkerNameInvoiceF.getText();

        // Code to set values for the variables InvoiceID, VehicleID, UserID, UserCardNumber, WorkerID, and WorkerName
        try ( Connection connection = DriverManager.getConnection(url)) {

            // Check if the worker exists
            String CHECK_INVOICE_SQL = "SELECT * FROM INVOICE WHERE WorkerID = ?";
            PreparedStatement checkWorker = connection.prepareStatement(CHECK_INVOICE_SQL);
            checkWorker.setString(1, WorkerID);
            ResultSet workerResult = checkWorker.executeQuery();
            // Check if the user exists
            String CHECK_USERS_SQL = "SELECT * FROM USERS WHERE UserID = ?";
            PreparedStatement checkUser = connection.prepareStatement(CHECK_USERS_SQL);
            checkUser.setString(1, UserID);
            ResultSet userResult = checkUser.executeQuery();
            // Check if the Vehicle exists
            String CHECK_CEHICLE_SQL = "SELECT * FROM VEHICLES WHERE VehicleID = ?";
            PreparedStatement checkvehicle = connection.prepareStatement(CHECK_CEHICLE_SQL);
            checkvehicle.setString(1, VehicleID);
            ResultSet vehicleResult = checkvehicle.executeQuery();
            // Check if the worker exists
            String CHECK_INVOICEs_SQL = "SELECT * FROM INVOICE WHERE InvoiceID = ?";
            PreparedStatement checks = connection.prepareStatement(CHECK_INVOICEs_SQL);
            checks.setString(1, InvoiceID);
            ResultSet invoiceResult = checks.executeQuery();

            if (Employees.InvoiceIDF.getText().isEmpty() == true || Employees.VIDInvoiceF.getText().isEmpty() == true || Employees.UserIDInvoiceF.getText().isEmpty() == true
                    || Employees.cardnumberInvoiceF.getText().isEmpty() == true || Employees.invoiceWorkerNameF.getText().isEmpty() == true
                    || Employees.WorkerNameInvoiceF.getText().isEmpty() == true || Employees.year.getText().isEmpty() == true
                    || Employees.day.getText().isEmpty() == true || Employees.month.getText().isEmpty() == true) {

                JOptionPane.showMessageDialog(null, "Try to fill all the fields");

            } else {

                if (invoiceResult.next()) {

                    JOptionPane.showMessageDialog(null, "The ID of the invoice already exist in the DB! Here what you can do:\n"
                            + "Either change the ID or try to edit and update the existing one instead.");
                    return;

                } else if (!vehicleResult.next()) {

                    JOptionPane.showMessageDialog(null, "The Vehicle with id " + VehicleID + " doesn't exist in the database.");
                    return;

                } else if (!userResult.next()) {

                    JOptionPane.showMessageDialog(null, "The user with id " + UserID + " doesn't exist in the database.");
                    return;

                } else if (!workerResult.next()) {

                    JOptionPane.showMessageDialog(null, "The worker with id " + WorkerID + " doesn't exist in the database.");
                    return;

                } else {

                    PreparedStatement preparedStatement = connection.prepareStatement(ADD_INVOICE_SQL);
                    preparedStatement.setString(1, WorkerID);
                    preparedStatement.setString(2, WorkerName);
                    preparedStatement.setString(3, InvoiceID);
                    preparedStatement.setString(4, InvoiceDate);
                    preparedStatement.setString(5, VehicleID);
                    preparedStatement.setString(6, UserID);
                    preparedStatement.setString(7, UserCardNumber);

                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "The invoice has been create for the user " + UserID + " by " + WorkerName
                            + " on " + InvoiceDate);

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
    ////need to check if the method is working, the method is not finished yet 

    public static void deleteInvoice() {

        String DELETE_INVOICE_SQL = "DELETE FROM INVOICE WHERE InvoiceID = ?";
        String InvoiceID = Employees.InvoiceIDF.getText();

        try ( Connection connection = DriverManager.getConnection(url)) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVOICE_SQL);
            preparedStatement.setString(1, InvoiceID);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (Employees.InvoiceIDF.getText().isEmpty() == true) {

                JOptionPane.showMessageDialog(null, "The invoice ID field is empty!");

            } else {

                if (rowsDeleted > 0) {

                    JOptionPane.showMessageDialog(null, "The invoice with Invoice ID: " + InvoiceID + " has been deleted successfully.");

                } else {

                    JOptionPane.showMessageDialog(null, "No invoice found with Invoice ID: " + InvoiceID + ".");

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong.");

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        }
    }

    public static void updateInvoice() {

        String CHECK_VEHICLE_SQL = "SELECT * FROM VEHICLES WHERE VehicleID = ?";
        String CHECK_USER_SQL = "SELECT * FROM USERS WHERE UserID = ?";
        String UPDATE_INVOICE_SQL = "UPDATE INVOICE SET "
                + "WorkerID = ?, WorkerName = ?, InvoiceDate = ?, VehicleID = ?, UserID = ?, UserCardNumber = ? WHERE InvoiceID = ?";

        String InvoiceID = Employees.InvoiceIDF.getText();;
        String VehicleID = Employees.VIDInvoiceF.getText();;
        String UserID = Employees.UserIDInvoiceF.getText();
        String InvoiceDate = Employees.year.getText() + "-" + Employees.month.getText() + "-"
                + Employees.day.getText();
        String UserCardNumber = Employees.cardnumberInvoiceF.getText();;
        String WorkerID = Employees.invoiceWorkerNameF.getText();
        String WorkerName = Employees.WorkerNameInvoiceF.getText();;

        // Code to set values for the variables InvoiceID, VehicleID, UserID, UserCardNumber, WorkerID, and WorkerName
        try ( Connection connection = DriverManager.getConnection(url)) {

            String InvoiceF = Employees.InvoiceIDF.getText();
            String WorkerIDF = Employees.invoiceWorkerNameF.getText();
            //PreparedStatement – Used to execute parameterized SQL queries
            PreparedStatement InvoiceQ = connection.prepareStatement("select * from INVOICE where InvoiceID=?");
            PreparedStatement WorkerQ = connection.prepareStatement("select * from INVOICE where WorkerID=?");
            InvoiceQ.setString(1, InvoiceF);
            WorkerQ.setString(1, WorkerIDF);
            //Excuting Query  
            ResultSet InvoiceQrs = InvoiceQ.executeQuery();
            ResultSet WorkerQrs = WorkerQ.executeQuery();

            if (Employees.InvoiceIDF.getText().isEmpty() == true || Employees.VIDInvoiceF.getText().isEmpty() == true || Employees.UserIDInvoiceF.getText().isEmpty() == true
                    || Employees.cardnumberInvoiceF.getText().isEmpty() == true || Employees.invoiceWorkerNameF.getText().isEmpty() == true
                    || Employees.WorkerNameInvoiceF.getText().isEmpty() == true || Employees.year.getText().isEmpty() == true
                    || Employees.day.getText().isEmpty() == true || Employees.month.getText().isEmpty() == true) {

                JOptionPane.showMessageDialog(null, "Try to fill all the fields");

            } else {

                if (!WorkerQrs.next()) {

                    JOptionPane.showMessageDialog(null, "You can not update worker ID to " + WorkerIDF + "\n"
                            + "Because the ID does not exist in the DB");
                    return;

                } else if (!InvoiceQrs.next()) {

                    JOptionPane.showMessageDialog(null, "You can not update invoice ID to " + InvoiceID + "\n"
                            + "Because the ID does not exist in the DB");
                    return;

                }

                PreparedStatement checkVehicleStatement = connection.prepareStatement(CHECK_VEHICLE_SQL);
                checkVehicleStatement.setString(1, VehicleID);
                ResultSet checkVehicleResult = checkVehicleStatement.executeQuery();

                PreparedStatement checkUserStatement = connection.prepareStatement(CHECK_USER_SQL);
                checkUserStatement.setString(1, UserID);
                ResultSet checkUserResult = checkUserStatement.executeQuery();

                if (!checkVehicleResult.next()) {
                    JOptionPane.showMessageDialog(null, "You can not update vehicle ID to " + VehicleID + "\n"
                            + "Because the ID does not exist in the DB");
                    return;

                } else if (!checkUserResult.next()) {

                    JOptionPane.showMessageDialog(null, "You can not update worker ID to " + UserID + "\n"
                            + "Because the ID does not exist in the DB");
                    return;

                } else {

                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE_SQL);

                    preparedStatement.setString(1, WorkerID);
                    preparedStatement.setString(2, WorkerName);
                    preparedStatement.setString(3, InvoiceDate);
                    preparedStatement.setString(4, VehicleID);
                    preparedStatement.setString(5, UserID);
                    preparedStatement.setString(6, UserCardNumber);
                    preparedStatement.setString(7, InvoiceID);

                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "The invoice with ID " + InvoiceID + " has been updated.");

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hmm... Something went wrong!");

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }

        }
    }

    public static void searchInvoice() {

        String SEARCH_INVOICE_SQL = "SELECT * FROM INVOICE WHERE InvoiceID = ?";
        String InvoiceID = Employees.InvoiceIDF.getText();

        try ( Connection connection = DriverManager.getConnection(url)) {

            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_INVOICE_SQL);
            preparedStatement.setString(1, InvoiceID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (Employees.InvoiceIDF.getText().isEmpty() == true) {

                JOptionPane.showMessageDialog(null, "The invoice ID field is empty!");

            } else {

                if (resultSet.next()) {

                    String workerID = resultSet.getString("WorkerID");
                    String workerName = resultSet.getString("WorkerName");
                    String invoiceDate = resultSet.getString("InvoiceDate");
                    String vehicleID = resultSet.getString("VehicleID");
                    String userID = resultSet.getString("UserID");
                    String userCardNumber = resultSet.getString("UserCardNumber");

                    Employees.InvoiceIDF.setText(InvoiceID);
                    Employees.invoiceWorkerNameF.setText(workerID);
                    //Employees.InvoiceIDF.setText(invoiceDate);////////////attention here 
                    Employees.cardnumberInvoiceF.setText(userCardNumber);
                    Employees.VIDInvoiceF.setText(vehicleID);
                    Employees.UserIDInvoiceF.setText(userID);
                    Employees.WorkerNameInvoiceF.setText(workerName);

                    JOptionPane.showMessageDialog(null, "This is the result of your search\n\n"
                            + "Location             Result\n"
                            + "============================\n"
                            + "InvoiceID             " + InvoiceID + "\n"
                            + "============================\n"
                            + "WorkerID              " + workerID + "\n"
                            + "============================\n"
                            + "WorkerName           " + workerName + "\n"
                            + "============================\n"
                            + "InvoiceDate         " + invoiceDate + "\n"
                            + "============================\n"
                            + "VehicleID               " + vehicleID + "\n"
                            + "============================\n"
                            + "UserID             " + userID + "\n"
                            + "============================\n"
                            + "UserCardNumber             " + userCardNumber + "\n\n");

                } else {

                    JOptionPane.showMessageDialog(null, "No invoice found with InvoiceID: " + InvoiceID);

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hmm... Something went wrong!");

            JOptionPane.showMessageDialog(null, "You can exit the program and reopen it, then click on populate the DB.");

            int X = JOptionPane.showConfirmDialog(null, "Do you want us to close the program for you?", "Do you want us to close the program for you?", JOptionPane.YES_NO_OPTION);

            if (X == 0) {

                System.exit(0);

            }
        }

    }

    public static void Stat() {

        try ( Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/DB/DB.db")) {
            // Retrieve all vehicles from the database
            PreparedStatement getAllVehicles = conn.prepareStatement("SELECT * FROM VEHICLES");
            ResultSet rs = getAllVehicles.executeQuery();

            // Calculate the sum, average, amount, and average amount of the vehicles' prices
            int vehicleCount = 0;
            double totalVehiclePrice = 0;

            while (rs.next()) {

                vehicleCount++;
                totalVehiclePrice += rs.getDouble("VehiclePrice");

            }

            double averageVehiclePrice = totalVehiclePrice / vehicleCount;

            // Print the sum, average, amount, and average amount of the vehicles' prices
            Employees.SumVehiclesPrices.setText("The sum prices of all vehicles is : " + totalVehiclePrice + " $");
            Employees.AverageVehiclesPrice.setText("Average price of all vehicles is : " + averageVehiclePrice + " $");
            Employees.TotalVehicles.setText("The amount of all vehicles that available are: " + (int) vehicleCount + " vehicle");
            Employees.ViewStat.setText("Refresh?");

        } catch (SQLException e) {

            // Handle any errors
            JOptionPane.showMessageDialog(null, "Error retrieving vehicles from database: " + e.getMessage());

        }
    }

    public static void MostCommonColorStat() {

        try ( Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/DB/DB.db")) {

            // Retrieve all vehicles from the database
            PreparedStatement getAllVehicles = conn.prepareStatement("SELECT * FROM VEHICLES");
            ResultSet rs = getAllVehicles.executeQuery();

            // Initialize a map to store the count of each color
            Map<String, Integer> colorCount = new HashMap<>();

            // Loop through each vehicle and update the color count accordingly
            while (rs.next()) {

                String color = rs.getString("VehicleColor");
                colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);

            }

            // Find the color with the highest count
            String mostCommonColor = null;
            int maxCount = 0;

            for (Map.Entry<String, Integer> entry : colorCount.entrySet()) {

                if (entry.getValue() > maxCount) {

                    mostCommonColor = entry.getKey();
                    maxCount = entry.getValue();

                }
            }

            // Print the most common color
            Employees.AverageVehiclesColor.setText("Most common color code of the vehicles in the database is: " + mostCommonColor);

        } catch (SQLException e) {

            // Handle any errors
            JOptionPane.showMessageDialog(null, "Error retrieving vehicles from database: " + e.getMessage());

        }
    }
}
