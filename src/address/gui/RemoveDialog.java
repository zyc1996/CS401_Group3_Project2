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
 * Purpose: Dialog for editing an existing AddressEntry object in the database
 */
public class RemoveDialog extends JDialog {
    /**
     * JPanel that holds the dialog's layout
     */
    private JPanel contentPane;

    /**
     * The dialog's Ok button
     */
    private JButton buttonOK;

    /**
     * The dialog's Cancel button
     */
    private JButton buttonCancel;

    /**
     * The dialog's textfield, holds the user input for the removal target's last name
     */
    private JTextField textField1;

    /**
     * Label for the removal instructions at the top of the dialog
     */
    private JLabel removeHeader;

    /**
     * List holding the selected objects to remove
     */
    private List<AddressEntry> selected;

    /**
     * The class constructor
     * @param ab The AddressBook object to remove objects from
     */
    public RemoveDialog(AddressBook ab) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onOK(ab); }
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
     * Method called when the Ok button is clicked
     * Opens MatchingDialog which allows the user to select which found objects to remove.
     * @param ab The address book to remove objects from
     */
    private void onOK(AddressBook ab) {
        if (textField1.getText().length() > 0 && ab.find(textField1.getText()).size() > 0) {
            dispose();
            MatchingDialog dialog = new MatchingDialog(textField1.getText(), ab);
            dialog.pack();
            dialog.setVisible(true);
            selected = dialog.getSelected();
        } else {
            removeHeader.setText("No matching entries were found");
        }
    }

    public List<AddressEntry> getSelected() {
        return selected;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
