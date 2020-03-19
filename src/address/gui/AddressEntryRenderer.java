package address.gui;
import address.data.AddressEntry;
import java.awt.Component;
import javax.swing.*;
/**
 * @author Lauren Dennedy, Yueheng Zheng, John Gilcreast, John Berge
 * @since  March 2020, SDK 13
 * @version 2.0
 *
 * Purpose: A JList renderer that determines the format entries are printed in
 */
public class AddressEntryRenderer extends JLabel implements ListCellRenderer<AddressEntry> {

    /**
     *  Needed for selection highlight when selecting an entry
     */
    public AddressEntryRenderer() {
        setOpaque(true);
    }


    /**
     * Custom list cell renderer
     * @param list JList of AddressEntry objects we are formatting
     * @param value The AddressEntry object of the current cell (returned by list.getModel().getElementAt(index))
     * @param index The index of the current cell
     * @param isSelected  is the cell selected?
     * @param cellHasFocus does the cell have focus?
     * @return Component that will render according to this method
     */
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

        //JList is formatted in HTML
        setText("<html> <div style='width: 100%'>"+displayString+"<hr></div></html>");
        return this;
    }
}