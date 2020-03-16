package address.gui;

import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddressBookGui {
     JFrame myFrame;

    private JPanel mainPanel;
    private JButton addButton;
    private JButton findButton;
    private JButton quitProgramButton;
    private JButton removeButton;
    private JList<AddressEntry> allList;
    private JList<AddressEntry> findList;
    private DefaultListModel<AddressEntry> model;
    private JScrollPane allPane;
    private JScrollPane findPane;
    private AddressBook ab;

    public AddressBookGui() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog dialog = new AddDialog();
                dialog.pack();
                dialog.setVisible(true);
                //create a temporary addressEntry object to hold the return value
                AddressEntry newEntry = new AddressEntry();
                newEntry = dialog.getEntry();

                //push the new entry to data base
                try {
                    addToDB(newEntry);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //fresh read of contents from from database
                try {
                    ab.readFromDB();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //clear for a fresh model
                ArrayList<AddressEntry> newList = ab.getList();
                model.clear();
                for(int i = 0; i < newList.size(); i++){
                    model.addElement(newList.get(i));
                }
                allList.setModel(model);

            }
        });
        quitProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.dispose();
                System.exit(0);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveDialog dialog = new RemoveDialog(ab);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindDialog dialog = new FindDialog();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    public void init() throws SQLException, ClassNotFoundException {
        ab = new AddressBook();

        //************************************************************
        //PLACEHOLDER
        ab.readFromDB();
        //************************************************************

        model = new DefaultListModel<AddressEntry>();
        ArrayList<AddressEntry> newList = ab.getList();

        for(int i = 0; i < newList.size(); i++){
            model.addElement(newList.get(i));
        }
        allList.setModel(model);
        allList.setLayoutOrientation(JList.VERTICAL);
        allList.setCellRenderer(new AddressEntryRenderer());


        myFrame = new JFrame("AddressBookGui");
        myFrame.setSize(600,600);
        myFrame.setContentPane(mainPanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    public void addToDB(AddressEntry e) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement();
        //ID auto increment from data base sequence
        stmt.executeQuery("INSERT INTO ADDRESSENTRYTABLE VALUES ('" + e.getName().getFirstName() + "', " + null +
                ", '" + e.getName().getLastName() + "', '" + e.getAddress().getStreet() + "', '" +
                e.getAddress().getCity() + "', '" + e.getAddress().getState() + "', " + e.getAddress().getZip() +
                ", '" + e.getEmail() + "', '" + e.getPhone() + "')");
        stmt.close();
        conn.close();
    }

    // Method in progress
    /*
    public void removeFromDB(ArrayList<AddressEntry> entries) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement();

        for (AddressEntry e: entries) {
            //ID auto increment from data base sequence
            stmt.executeQuery("INSERT INTO ADDRESSENTRYTABLE VALUES ('" + e.getName().getFirstName() + "', " + null +
                    ", '" + e.getName().getLastName() + "', '" + e.getAddress().getStreet() + "', '" +
                    e.getAddress().getCity() + "', '" + e.getAddress().getState() + "', " + e.getAddress().getZip() +
                    ", '" + e.getEmail() + "', '" + e.getPhone() + "')");
        }

        stmt.close();
        conn.close();
    }

     */
}
