package address.gui;

import address.data.AddressEntry;
import java.awt.Component;
import javax.swing.*;

public class AddressEntryRenderer extends JLabel implements ListCellRenderer<AddressEntry> {

    @Override
    public Component getListCellRendererComponent(JList<? extends AddressEntry> list, AddressEntry value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        setText("<html>"+value.toString().replace("\n","<br>")+"</html>");
        return this;
    }
}