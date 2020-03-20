package address.gui;
import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: The dialog for adding a new AddressEntry to the database
 */
public class AddDialog extends JDialog {
    /**
     * JPanel containing all dialog elements
     */
    private JPanel contentPane;

    /**
     * Dialog OK button
     */
    private JButton buttonOK;

    /**
     * Dialog Cancel button
     */
    private JButton buttonCancel;

    /**
     * Dialog OK button
     */
    private JTextField fNameField;

    /**
     * Object name entry field
     */
    private JTextField lNameField;

    /**
     * Object street entry field
     */
    private JTextField streetField;

    /**
     * Object city field
     */
    private JTextField cityField;

    /**
     * Object state field
     */
    private JTextField stateField;

    /**
     * Object zip entry field
     */
    private JTextField zipField;

    /**
     * Object phone entry field
     */
    private JTextField phoneField;

    /**
     * Object email entry field
     */
    private JTextField emailField;

    /**
     * The resulting AddressEntry object
     */
    private AddressEntry ae;

    /**
     * Class constructor that adds event listeners to the GUI buttons
     */
    public AddDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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


    /**
     * Method that attempts to add object to the database when OK button is pushed
     */
    private void onOK() {
        // add your code here
        Boolean valid = true;
        AddressEntry newEntry = new AddressEntry();
        String result = "";

        String line = fNameField.getText();
        if (line.length() == 0){
            result = result + "First name is empty\n";
            valid = false;
        }else{
            newEntry.getName().setFirstName(line);
        }
        line = lNameField.getText();
        if (line.length() == 0){
            result = result + "Last name is empty\n";
            valid = false;
        }else{
            newEntry.getName().setLastName(line);
        }
        line = streetField.getText();
        if (line.length() == 0){
            result = result + "Street name is empty\n";
            valid = false;
        }else{
            newEntry.getAddress().setStreet(line);
        }
        line = cityField.getText();
        if (line.length() == 0){
            result = result + "City is empty\n";
            valid = false;
        }else{
            newEntry.getAddress().setCity(line);
        }
        line = stateField.getText();
        if (line.length() == 0){
            result = result + "State is empty\n";
            valid = false;
        }else{
            newEntry.getAddress().setState(line);
        }
        try {
            line = zipField.getText();
            if (line.length() == 0){
                result = result + "ZIP code is empty\n";
                valid = false;
            }else {
                newEntry.getAddress().setZip(Integer.parseInt(line));
            }
        }catch (NumberFormatException e){
            result = result + "ZIP code must be a number\n";
            valid = false;
        }
        line = phoneField.getText();
        if (line.length() == 0){
            result = result + "Phone is empty\n";
            valid = false;
        }else{
            newEntry.setPhone(line);
        }
        line = emailField.getText();
        if (line.length() == 0){
            result = result + "Email is empty\n";
            valid = false;
        }else{
            newEntry.setEmail(line);
        }
        if(valid){
            //ADD TO DATABASE
            ae = newEntry;
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, result);
        }
    }

    /**
     * Method that will simply close the dialog when cancel button is pressed
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Getter for the AddressEntry object created through the dialog
     * @return AddressEntry object
     */
    public AddressEntry getEntry(){
        return ae;
    }
}
