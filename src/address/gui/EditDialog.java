package address.gui;

import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;

public class EditDialog extends JDialog {
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
    private AddressEntry edited;

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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public AddressEntry getEdited(){
        return edited;
    }
}
