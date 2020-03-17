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
import java.util.List;

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
    private DefaultListModel<AddressEntry> findModel;
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

                // In progress
                //push the new entry to data base
                try {
                    removeFromDB(dialog.getSelected());
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

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindDialog dialog = new FindDialog(ab);
                dialog.pack();
                dialog.setVisible(true);

                //clear for a fresh model
                List<AddressEntry> selected = dialog.getSelected();
                findModel.clear();
                for(int i = 0; i < selected.size(); i++){
                    findModel.addElement(selected.get(i));
                }
                findList.setModel(findModel);
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
        allList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));

        findModel = new DefaultListModel<AddressEntry>();
        findList.setModel(findModel);
        findList.setLayoutOrientation(JList.VERTICAL);
        findList.setCellRenderer(new AddressEntryRenderer());
        findList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));

        myFrame = new JFrame("AddressBookGui");
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

    public void removeFromDB(List<AddressEntry> entries) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement();

        if (entries != null && entries.size() > 0) {
            for (AddressEntry e : entries) {
                //ID auto increment from data base sequence
                stmt.executeQuery("DELETE FROM ADDRESSENTRYTABLE WHERE ID = ('" + e.getID() + "')");
            }
        }

        stmt.close();
        conn.close();
    }
}
