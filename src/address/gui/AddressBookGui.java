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
import java.util.*;

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
    private JButton editButton;
    private AddressBook ab;

    public AddressBookGui() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog dialog = new AddDialog();
                dialog.pack();
                dialog.setVisible(true);
                //create a temporary addressEntry object to hold the return value
                AddressEntry newEntry;
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
                if (dialog.getSelected() != null) {
                    try {
                        removeFromDB(dialog.getSelected().get(0));
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
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

                //clear for a fresh model

                List<AddressEntry> existing = new ArrayList<AddressEntry>();
                for (int i = 0; i < findModel.size(); i++) {
                    existing.add(findModel.get(i));
                }

                List<AddressEntry> removed = dialog.getSelected();
                findModel.clear();
                for(int i = 0; i < existing.size(); i++){
                    if (existing.get(i) != removed.get(0)) {
                        findModel.addElement(existing.get(i));
                    }
                }

                findList.setModel(findModel);
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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddressEntry toEdit = new AddressEntry();
                toEdit = findList.getSelectedValue();
                EditDialog dialog = new EditDialog(toEdit);
                dialog.pack();
                dialog.setVisible(true);

                //gets the returned change from edit dialog
                toEdit = dialog.getEdited();

                //Update changes to Database
                try {
                    editDB(toEdit);
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

                //clear for a fresh model
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

        // Set to something...
        /*
        ArrayList<AddressEntry> selected = new ArrayList<>();

        if (findList != null || findList.size() < 1) {
            for(int i = 0; i < newList.size(); i++){
                findModel.addElement(selected.get(i));
            }
        }
        *
         */

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

    public void removeFromDB(AddressEntry entry) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement();

        if (entry != null) {
            stmt.executeQuery("DELETE FROM ADDRESSENTRYTABLE WHERE ID = ('" + entry.getID() + "')");
        }

        stmt.close();
        conn.close();
    }

    public void editDB(AddressEntry e) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1003/85kTyIfb@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
        Statement stmt = conn.createStatement();
        //ID auto increment from data base sequence
        stmt.executeQuery("UPDATE ADDRESSENTRYTABLE SET FIRSTNAME = '" + e.getName().getFirstName() +
                        "', LASTNAME = '" + e.getName().getLastName() +
                        "', STREET = '" + e.getAddress().getStreet() +
                        "', CITY = '" + e.getAddress().getCity() +
                        "', STATE = '" + e.getAddress().getState() +
                        "', ZIP = " + e.getAddress().getZip() +
                        ", EMAIL = '" + e.getEmail() +
                        "', PHONE = '" + e.getPhone() + "' WHERE ID = " + e.getID());
        stmt.close();
        conn.close();
    }

}
