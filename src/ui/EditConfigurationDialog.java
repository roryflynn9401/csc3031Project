package ui;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditConfigurationDialog extends JDialog {
    private JComboBox typeCombo;
    private JCheckBox configVar2;
    private JTextField topspeedText, configVar1;

    private EcuConfiguration configuration;
    private EcuConfigurationMemento memento;


    EditConfigurationDialog(Frame owner, EcuConfiguration configuration) {
        super(owner, "Edit Ecu Configuration", true);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.memento = new EcuConfigurationMemento(configuration);
       
        // Form entry panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
       
        typeCombo = new JComboBox(EcuInventoryFacade.INSTANCE.getEcuTypes());
        formPanel.add(new JLabel("Type"));
        typeCombo.setSelectedItem(configuration.getEngineType());
        formPanel.add(typeCombo);

        topspeedText = new JTextField();
        formPanel.add(new JLabel("Speed"));
        topspeedText.setText(String.valueOf(configuration.getTopSpeed()));
        formPanel.add(topspeedText);

        configVar1 = new JTextField();
        formPanel.add(new JLabel("No Cylinders/Motors"));
        configVar1.setText(String.valueOf(configuration instanceof ElectricEcuConfiguration ?
                ((ElectricEcuConfiguration) configuration).getNoMotors() :
                ((CombustionEcuConfiguration) configuration).getCylinders()));
        formPanel.add(configVar1);

        configVar2 = new JCheckBox();
        formPanel.add(new JLabel("Remapped/Frunk"));
        configVar2.setSelected(configuration instanceof ElectricEcuConfiguration ?
                ((ElectricEcuConfiguration) configuration).hasFrunk() :
                ((CombustionEcuConfiguration) configuration).isRemapped());
        formPanel.add(configVar2);
       
        add(formPanel, BorderLayout.CENTER);
       
        // Buttons to submit or cancel
        JPanel buttonPanel = new JPanel(new FlowLayout());
       
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });
        buttonPanel.add(okButton);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                typeCombo.setSelectedItem(configuration.getEngineType());
                topspeedText.setText(String.valueOf(configuration.getTopSpeed()));
                configVar1.setText(String.valueOf(configuration instanceof ElectricEcuConfiguration ?
                        ((ElectricEcuConfiguration) configuration).getNoMotors() :
                        ((CombustionEcuConfiguration) configuration).getCylinders()));
                configVar2.setSelected(configuration instanceof ElectricEcuConfiguration ?
                        ((ElectricEcuConfiguration) configuration).hasFrunk() :
                        ((CombustionEcuConfiguration) configuration).isRemapped());
            }
        });
        buttonPanel.add(undoButton);

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