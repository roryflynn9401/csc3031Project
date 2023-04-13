package ui;

import domain.EcuInventoryFacade;

import java.awt.*;
import javax.swing.*;

public class EcusFrame extends JFrame {
    private ManageEcusPanel enginesPanel;
   
    public EcusFrame () {
        super("Manage Ecus");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(buildUI(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }
   
    private Component buildUI() {
        JPanel uiPanel = new JPanel(new BorderLayout());
        enginesPanel = new ManageEcusPanel(this); //pass in this -book left this out
        EcuInventoryFacade.INSTANCE.addEcuListener(enginesPanel);
        uiPanel.add(enginesPanel, BorderLayout.CENTER);
        return uiPanel;
    }
}
