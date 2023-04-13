package ui;

import db.*;
import domain.EcuInventoryFacade;
import domain.IEcuFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ManageEcusPanel extends JPanel implements EntityListener {
    private Frame owner;
    private EcuListModel ecusModel;
    private IEcuFilter filter;

    ManageEcusPanel(final Frame owner) {
        super(new BorderLayout());
        this.owner = owner;
       
        // Scrollable list of engines
        ecusModel = new EcuListModel();
        add(new JScrollPane(new JList(ecusModel)), BorderLayout.CENTER);
       
        // Buttons to add and save
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setSize(1000, 1000);
       
        JButton buildEngineButton = new JButton("Build Ecu");
        buildEngineButton.addActionListener(event -> new BuildEcuDialog(owner).setVisible(true));
        buttonPanel.add(buildEngineButton);
 
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(event -> {
            try {
                EcuInventoryFacade.INSTANCE.saveEcus();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(ManageEcusPanel.this, ex, "Error", JOptionPane.ERROR_MESSAGE); //DG ListEnginesPanel.this -> ManageEnginesPanel.this
            }
        });
        buttonPanel.add(saveButton);
 
        JButton restoreButton = new JButton("Restore");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    EcuInventoryFacade.INSTANCE.restoreEcus();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ManageEcusPanel.this, ex, "Error", JOptionPane.ERROR_MESSAGE); //DG ListEnginesPanel.this -> ManageEnginesPanel.this
                }
            }
        });
        buttonPanel.add(restoreButton);

        JButton generateCsvButton = new JButton("Generate CSV");
        generateCsvButton.addActionListener(event -> {
            try{
                EcuInventoryFacade.INSTANCE.generateCsv(this.filter != null ? this.filter : filter -> true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ManageEcusPanel.this, ex, "Error", JOptionPane.ERROR_MESSAGE); //DG ListEnginesPanel.this -> ManageEnginesPanel.this
            }
        });
        buttonPanel.add(generateCsvButton);

        JButton generateJsonButton = new JButton("Generate JSON");
        generateJsonButton.addActionListener(event -> {
            try{
                EcuInventoryFacade.INSTANCE.generateJson(this.filter != null ? this.filter : filter -> true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ManageEcusPanel.this, ex, "Error", JOptionPane.ERROR_MESSAGE); //DG ListEnginesPanel.this -> ManageEnginesPanel.this
            }
        });
        buttonPanel.add(generateJsonButton);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(event -> new FilterListDialog(owner, this).setVisible(true));
       buttonPanel.add(filterButton);

        JButton removeFilterButton = new JButton("Remove Filter");
        removeFilterButton.addActionListener(event -> removeFilter());
        buttonPanel.add(removeFilterButton);


        add(buttonPanel, BorderLayout.SOUTH);
    }
 
    public void entityAdded(EntityEvent event) {
        ecusModel.ecuAdded(event.getValue());
    }
 
    public void entityRestored(EntityEvent event) {
        ecusModel.loadEcus();
    }


    public void filterList(IEcuFilter filter) {
        ecusModel.filterEcus(filter);
        this.filter = filter;
    }


    public void removeFilter() {
        ecusModel.loadEcus();
        filter = null;
    }

    // Inner class: ListModel for engines
    private class EcuListModel extends DefaultListModel {
        private java.util.List<Object> engines;

        EcuListModel() {
            engines = new ArrayList<Object>();
            loadEcus();
        }
    
    void loadEcus() {
        engines.clear();
        engines.addAll(Arrays.asList(EcuInventoryFacade.INSTANCE.getAllEcus()));
        fireContentsChanged(this, 0, engines.size() - 1);
    }

    void filterEcus(IEcuFilter filter){
            engines.clear();
            engines.addAll(Arrays.asList(EcuInventoryFacade.INSTANCE.getFilteredEngines(filter)));
            fireContentsChanged(this, 0, engines.size() -1);
    }
   
    public Object getElementAt(int index) {
        return engines.get(index);
    }
    
    public int getSize() {
        return engines.size();
    }
   
    void ecuAdded(Object engine) {
        engines.add(engine);
        fireContentsChanged(this, 0, engines.size() - 1);
    }
   
}
}