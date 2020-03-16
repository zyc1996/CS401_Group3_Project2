package address.gui;

import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                RemoveDialog dialog = new RemoveDialog();
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

    public void init(){
        ab = new AddressBook();

        //************************************************************
        //PLACEHOLDER
        ab.add(new AddressEntry("test", "abc", "somewhere 123",
                "someplace", "somestate", 123, "email", "tel", 1));
        ab.add(new AddressEntry("test", "dude", "somewhere 123",
                "someplace", "somestate", 123, "email", "tel", 2));
        ab.add(new AddressEntry("test", "abc", "somewhere new, should be 1st in list",
                "someplace", "somestate", 123, "email", "tel", 3));
        ab.add(new AddressEntry("test", "bay", "should be third in list",
                "someplace", "somestate", 123, "email", "tel", 2));
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
        myFrame.setContentPane(mainPanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}
