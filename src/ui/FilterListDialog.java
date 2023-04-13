package ui;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterListDialog extends JDialog {
    private JComboBox typeCombo, sizeCombo;

    FilterListDialog(Frame owner, ManageEcusPanel mainPanel) {
        super(owner, "Filter Ecus", true);
        setLayout(new BorderLayout());
        this.setLocationRelativeTo(owner);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Form entry panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2));

        typeCombo = new JComboBox(EcuInventoryFacade.INSTANCE.getEcuTypes());
        formPanel.add(new JLabel("Type"));
        formPanel.add(typeCombo);

        sizeCombo = new JComboBox();
        var speeds = Arrays.stream(EcuInventoryFacade.INSTANCE.getAllEcus())
                .map(x-> ((Ecu)x).getEcuProcessor().getSpeed())
                .distinct()
                .collect(Collectors.toList());
        speeds.forEach(speed -> sizeCombo.addItem(speed));

        formPanel.add(new JLabel("Speed"));
        formPanel.add(sizeCombo);

        add(formPanel, BorderLayout.CENTER);

        // Buttons to submit or cancel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                var speed = (String) sizeCombo.getSelectedItem();
                var type = (EcuFactory.Type)typeCombo.getSelectedItem();

                IEcuFilter filterSpeed = (filterVals) -> filterVals instanceof Ecu &&
                        ((Ecu) filterVals).getEcuProcessor().getSpeed() == speed;
                IEcuFilter filterType = (filterVals) -> filterVals instanceof Ecu &&
                        type == EcuFactory.Type.ELECTRIC ? ((Ecu) filterVals).getEcuProcessor().isHybrid() == true :
                        ((Ecu) filterVals).getEcuProcessor().isHybrid() == false;

                mainPanel.filterList(filterSpeed.combine(filterType));
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
