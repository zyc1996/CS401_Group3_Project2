package address.gui;

import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MatchingDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private DefaultListModel<AddressEntry> model;
    private JList<AddressEntry> MatchingList;

    public MatchingDialog(String lname, AddressBook ab) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        model = new DefaultListModel<AddressEntry>();
        List<AddressEntry> newList = ab.find(lname);

        for(int i = 0; i < newList.size(); i++){
            model.addElement(newList.get(i));
        }

        MatchingList.setModel(model);
        MatchingList.setLayoutOrientation(JList.VERTICAL);
        MatchingList.setCellRenderer(new AddressEntryRenderer());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int[] matches = MatchingList.getSelectedIndices();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
