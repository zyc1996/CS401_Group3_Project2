package address.gui;
import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: Dialog for finding matching entries in the database
 */
public class FindDialog extends JDialog {

    /**
     * JPanel that holds the dialog's layout
     */
    private JPanel contentPane;

    /**
     * The dialog's Ok button
     */
    private JButton buttonOK;

    /**
     * The dialog's cancel button
     */
    private JButton buttonCancel;

    /**
     * The user entry text field, used to search for matching entries
     */
    private JTextField findField;

    /**
     * The label containing the dialog instructions
     */
    private JLabel headerLabel;

    /**
     * The list containing the matching entries
     */
    private List<AddressEntry> selected;

    /**
     * The class constructor. Creates action listeners on the buttons.
     * @param ab The AddressBook object to search through
     */
    public FindDialog(AddressBook ab) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(ab);
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

    /**
     * The method called when the Ok button is clicked.
     * Calls the AddressBooks find method and populates
     * the 'selected' list with matching entries
     * @param ab
     */
    private void onOK(AddressBook ab) {
        if (findField.getText().length() > 0 && ab.find(findField.getText()).size() > 0) {
            dispose();
            selected = ab.find(findField.getText());
        } else {
            headerLabel.setText("No matching entries were found");
        }
    }

    /**
     * Gets the list of matching entries in the dialog
     * @return list of matching entries
     */
    public List<AddressEntry> getSelected() {
        return selected;
    }

    /**
     * Called when the Cancel button is clicked.
     * Simply closes the window.
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
