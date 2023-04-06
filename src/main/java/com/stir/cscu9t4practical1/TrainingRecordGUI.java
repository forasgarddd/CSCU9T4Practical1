// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import javax.swing.*;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField terrain = new JTextField(30);
    private JTextField tempo = new JTextField(3);
    private JTextField repetetions = new JTextField(4);
    private JTextField recovery = new JTextField(3);
    private JTextField where = new JTextField(25);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labtempo = new JLabel(" Tempo (Only for cycle sessions): ");
    private JLabel labter = new JLabel(" Terrain type (Only for cycle sessions): ");
    private JLabel labrepet = new JLabel(" Number of repetitions (Only for sprint sessions): ");
    private JLabel labrec = new JLabel(" Recovery time (Only for spring sessions): ");

    private JLabel labwhere = new JLabel(" Where (Only for swim sessions): ");
    private JButton addR = new JButton("Add");

    private JButton removeR = new JButton("Remove");
    private JButton lookUpByDate = new JButton("Look Up");

    private JButton findAllByDate = new JButton("Find all");

    private String[] listOfEntries = {"Cycle", "Swim", "Sprint"};
    private JComboBox<String> chooseEntry = new JComboBox<>(listOfEntries);

    private TrainingRecord myAthletes = new TrainingRecord();

    private List<Entry> tr = myAthletes.getTr();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());

        add(labn);
        add(name);
        name.setEditable(true);

        add(chooseEntry);
        chooseEntry.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);

        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(removeR);
        removeR.addActionListener(this);

        add(labter);
        add(terrain);
        terrain.setEditable(true);
        add(labtempo);
        add(tempo);
        tempo.setEditable(true);

        add(labrepet);
        add(repetetions);
        repetetions.setEditable(true);
        add(labrec);
        add(recovery);
        recovery.setEditable(true);

        add(labwhere);
        add(where);
        where.setEditable(true);

        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);

        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);


        setSize(720, 200);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == removeR) {
            message = removeEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
            message = findAllEntries();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding " + what + " entry to the records");
        String n = name.getText();
        String type = (String) chooseEntry.getSelectedItem();
        System.out.println(type);
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        String ter = terrain.getText();
        String temp = tempo.getText();
        int rep = Integer.parseInt(repetetions.getText());
        int rec = Integer.parseInt(recovery.getText());
        String wh = where.getText();
        for (Entry current : myAthletes.getTr()) {
            if (Objects.equals(current.getName(), n) && current.getMonth() == m &&
                    current.getDay() == d && current.getYear() == y) {
                    return message = "This entry already exists";
            }
        }
        switch (type) {
            case "Cycle":
                CycleEntry ce = new CycleEntry(n, d, m, y, h, mm, s, km, ter, temp);
                myAthletes.addEntry(ce);
                break;
            case "Sprint":
                SprintEntry spe = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
                myAthletes.addEntry(spe);
                break;
            case "Swim":
                SwimEntry swe = new SwimEntry(n, d, m, y, h, mm, s, km, wh);
                myAthletes.addEntry(swe);
                break;
        }

        return message;
    }

    public String removeEntry(String what) {
        String message = "Record removed \n";
        System.out.println("Removing " + what + " entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        if(myAthletes.getTr().isEmpty()) {
            return "No available records";
        }
        for (Entry current : myAthletes.getTr()) {
            if (Objects.equals(current.getName(), n) && current.getMonth() == m &&
                    current.getDay() == d && current.getYear() == y) {
                myAthletes.removeEntry(current);
                if(myAthletes.getTr().isEmpty()) {
                    return message;
                }
            }
        }
        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String findAllEntries() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up all records for the date ...");
        String message = myAthletes.findAllEntries(d, m, y);
        return message;
    }



    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        terrain.setText("");
        tempo.setText("");
        repetetions.setText("0");
        recovery.setText("0");
        where.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
        terrain.setText(String.valueOf(ent.getTerrain()));
        tempo.setText(String.valueOf(ent.getTempo()));
        repetetions.setText(String.valueOf(ent.getRepetitions()));
        recovery.setText(String.valueOf(ent.getRecovery()));
        where.setText(String.valueOf(ent.getWhere()));

    }

} // TrainingRecordGUI

