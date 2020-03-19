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
 * Purpose: Dialog for selecting an entry from multiple matches in the database.
 * Used by the Remove dialog.
 */
public class MatchingDialog extends JDialog {
    /**
     * Panel holding the main page layout
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
     * The model for the JList
     */
    private DefaultListModel<AddressEntry> model;

    /**
     * The objects selected in the JList
     */
    private List<AddressEntry> selected;

    /**
     * The objects in the database that match the previous search
     */
    private JList<AddressEntry> MatchingList;

    /**
     * The class constructor. Searches the entries for matches,
     *  populates the JList, and adds event listeners to the buttons.
     * @param lname String used as the search input.
     * @param ab
     */
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
                selected = onOK(ab);
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
     * Method to get the selected entries
     * @return list of the selected JList entries
     */
    public List<AddressEntry> getSelected() {
        return selected;
    }

    /**
     * Method called when the OK button is pressed
     * Closes the dialog, creates a list of the selected entries, and returns them
     * @param ab
     * @return
     */
    private List<AddressEntry> onOK(AddressBook ab) {
        dispose();
        for (AddressEntry e: MatchingList.getSelectedValuesList()) {
            System.out.println(e);
        }
        return MatchingList.getSelectedValuesList();

    }

    /**
     * Method called when the Cancel button is pressed
     * Simply closes the dialog
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
