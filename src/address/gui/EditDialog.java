package address.gui;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: Dialog for editing an existing AddressEntry object in the database
 */
public class EditDialog extends JDialog {
    /**
     * JPanel containing the dialog's layout
     */
    private JPanel contentPane;

    /**
     * The ok button of the dialog
     */
    private JButton buttonOK;

    /**
     * The cancel button of the dialog
     */
    private JButton buttonCancel;

    /**
     * The first name JTextField for the AddressEntry object
     */
    private JTextField fNameField;

    /**
     * The last name JTextField for the AddressEntry object
     */
    private JTextField lNameField;

    /**
     * The street JTextField for the AddressEntry object
     */
    private JTextField streetField;

    /**
     * The city JTextField for the AddressEntry object
     */
    private JTextField cityField;

    /**
     * The state JTextField for the AddressEntry object
     */
    private JTextField stateField;

    /**
     * The zip JTextField for the AddressEntry object
     */
    private JTextField zipField;

    /**
     * The phone JTextField for the AddressEntry object
     */
    private JTextField phoneField;

    /**
     * The email JTextField for the AddressEntry object
     */
    private JTextField emailField;

    /**
     * The resulting edited AddressEntry object
     */
    private AddressEntry edited;

    /**
     * Class constructor. Adds event listeners to the buttons.
     * @param toEdit The AddressEntry object to change in the database
     */
    public EditDialog(AddressEntry toEdit) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(toEdit);
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
     * Called when ok button is pressed.
     * Attempts to update entry field if any new input is given, then closes the dialog.
     * @param toEdit The AddressEntry to edit
     */
    private void onOK(AddressEntry toEdit) {
        // add your code here
        Boolean valid = true;
        String result = "";

        // different from add, if a text field is empty, that means no changes to the entry attribute
        String line = fNameField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.getName().setFirstName(line);
        }
        line = lNameField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.getName().setLastName(line);
        }
        line = streetField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.getAddress().setStreet(line);
        }
        line = cityField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.getAddress().setCity(line);
        }
        line = stateField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.getAddress().setState(line);
        }
        try {
            line = zipField.getText();
            if (line.length() == 0){
                //will be no changes
            }else {
                toEdit.getAddress().setZip(Integer.parseInt(line));
            }
        }catch (NumberFormatException e){
            result = result + "ZIP code must be a number\n";
            valid = false;
        }
        line = phoneField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.setPhone(line);
        }
        line = emailField.getText();
        if (line.length() == 0){
            //will be no changes
        }else{
            toEdit.setEmail(line);
        }
        if(valid){
            //make confirmed to changed content and store as dialog entry variable
            edited = toEdit;
            JOptionPane.showMessageDialog(null, "Valid");
            //dispose();
        }else{
            JOptionPane.showMessageDialog(null, result);
        }

        dispose();
    }

    /**
     * Method called when cancel button is pressed, closes the dialog
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Method to return the resulting edited object
     * @return The edited AddressEntry object
     */
    public AddressEntry getEdited(){
        return edited;
    }
}