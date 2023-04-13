package ui;

import db.DatabaseFacade;
import domain.EcuFactory;
import domain.EcuInventoryFacade;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BuildEcuDialog extends JDialog {
    private JComboBox typeCombo, sizeCombo;

    BuildEcuDialog(Frame owner) {
        super(owner, "Build Ecu", true);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       
        // Form entry panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
       
        typeCombo = new JComboBox(EcuInventoryFacade.INSTANCE.getEcuTypes());
        formPanel.add(new JLabel("Type"));
        formPanel.add(typeCombo);
       
        sizeCombo = new JComboBox();
        sizeCombo.addItem(1300);
        sizeCombo.addItem(1600);
        sizeCombo.addItem(2000);
        sizeCombo.addItem(2500);
        formPanel.add(new JLabel("Speed"));
        formPanel.add(sizeCombo);
       
        add(formPanel, BorderLayout.CENTER);
       
        // Buttons to submit or cancel
        JPanel buttonPanel = new JPanel(new FlowLayout());
       
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                EcuInventoryFacade.INSTANCE.addEcu((Integer)sizeCombo.getSelectedItem(), (EcuFactory.Type)typeCombo.getSelectedItem() == EcuFactory.Type.ELECTRIC);
                setVisible(false);
            }
        });
        buttonPanel.add(okButton);
 
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });
        buttonPanel.add(cancelButton);
       
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
}