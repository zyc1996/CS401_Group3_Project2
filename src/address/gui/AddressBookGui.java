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
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: This is the main window of the user GUI for this program
 */
public class AddressBookGui {
    /**
     * JFrame that holds the main window
     */
    JFrame myFrame;

    /**
     * JPanel that holds the frame's layout
     */
    private JPanel mainPanel;

    /**
     * JButton to open the add dialog
     */
    private JButton addButton;

    /**
     * JButton to open the find dialog
     */
    private JButton findButton;

    /**
     * JButton to open the remove dialog
     */
    private JButton removeButton;

    /**
     * JButton to open the edit dialog
     */
    private JButton editButton;

    /**
     * JButton to close the GUI, which ends the program
     */
    private JButton quitProgramButton;

    /**
     * The list of all entries in the database
     */
    private JList<AddressEntry> allList;

    /**
     * The list of search results from the find dialog
     */
    private JList<AddressEntry> findList;

    /**
     * Model for the allList
     */
    private DefaultListModel<AddressEntry> model;

    /**
     * Model for the findList
     */
    private DefaultListModel<AddressEntry> findModel;

    /**
     * Pane containing the allList
     */
    private JScrollPane allPane;

    /**
     * Pane containing the findList
     */
    private JScrollPane findPane;

    /**
     * The GUI's local AddressBook object
     */
    private AddressBook ab;

    /**
     * Object constructor that adds event listeners to the various buttons
     * Also attempts to populate the allList with the database information
     * Refreshes the JList information whenever dialogs are closed
     */
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
                if (selected != null) {
                    for(int i = 0; i < selected.size(); i++){
                        findModel.addElement(selected.get(i));
                    }
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

                if (toEdit != null) {
                    //Update changes to Database
                    try {
                        editDB(toEdit);
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

                findModel.clear();
                for(int i = 0; i < existing.size(); i++){
                    findModel.addElement(existing.get(i));
                }

                findList.setModel(findModel);
            }
        });
    }

    /**
     * GUI initiation method.
     * Creates local AddressBook object and populates it with information from the database
     * Also controls various display settings such as layout and JList cell rendering
     * @throws SQLException if there is an error in the SQL exectution
     * @throws ClassNotFoundException if there is an error locating class
     */
    public void init() throws SQLException, ClassNotFoundException {
        ab = new AddressBook();
        ab.readFromDB();

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

    /**
     * Method that adds an AddressEntry object into the database
     * @param e AddressEntry object to add to the database
     * @throws SQLException if there is an error in the SQL exectution
     * @throws ClassNotFoundException if there is an error locating class
     */
    public static void addToDB(AddressEntry e) throws ClassNotFoundException, SQLException {
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

    /**
     * Method that removes AddressEntry objects from the database
     * @param entry AddressEntry objects to remove from the database
     * @throws SQLException if there is an error in the SQL exectution
     * @throws ClassNotFoundException if there is an error locating class
     */
    public static void removeFromDB(AddressEntry entry) throws ClassNotFoundException, SQLException {
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

    /**
     * Method that edits an object in the database
     * @param e AddressEntry object to add to the database
     * @throws SQLException if there is an error in the SQL exectution
     * @throws ClassNotFoundException if there is an error locating class
     */
    public static void editDB(AddressEntry e) throws ClassNotFoundException, SQLException {
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
