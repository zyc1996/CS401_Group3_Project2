package address.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookGui {
     JFrame myFrame;

    private JPanel mainPanel;
    private JButton addButton;
    private JButton findButton;
    private JButton quitProgramButton;
    private JButton removeButton;
    private JList allList;
    private JList findList;
    private JScrollPane allPane;
    private JScrollPane findPane;

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
        myFrame = new JFrame("AddressBookGui");
        myFrame.setContentPane(mainPanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}
