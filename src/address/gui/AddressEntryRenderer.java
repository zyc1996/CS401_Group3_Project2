package address.gui;

import address.data.AddressEntry;
import java.awt.Component;
import javax.swing.*;

public class AddressEntryRenderer extends JLabel implements ListCellRenderer<AddressEntry> {

    public AddressEntryRenderer() {
        setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends AddressEntry> list, AddressEntry value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        String displayString = "";

        //Last name, First name
        // Street, City
        // State, ZIP
        // Phone
        // Email
        displayString = displayString + value.getName().getLastName()+", "+value.getName().getFirstName()+"<br>";
        displayString = displayString + value.getAddress().getStreet()+", "+value.getAddress().getCity()+"<br>";
        displayString = displayString + value.getAddress().getState()+", "+value.getAddress().getZip()+"<br>";
        displayString = displayString + value.getPhone()+"<br>";
        displayString = displayString + value.getEmail()+"<br>";

        setText("<html> <div style='width: 100%'>"+displayString+"<hr></div></html>");
        return this;
    }
}