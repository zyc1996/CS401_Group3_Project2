package address.gui;

import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;

public class AddDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fNameField;
    private JTextField lNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JTextField emailField;

    private AddressEntry ae;

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
            JOptionPane.showMessageDialog(null, "Valid");
            //dispose();
        }else{
            JOptionPane.showMessageDialog(null, result);
        }


        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddDialog dialog = new AddDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public AddressEntry getEntry(){
        return ae;
    }
}
