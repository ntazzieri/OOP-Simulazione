package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logic logic; 
    
    public GUI(int size) {
        logic = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        var check = new JButton("Check > Restart");
        check.addActionListener(e -> {
            cells.keySet().forEach(k -> k.setEnabled(true));
            if(logic.check()) {
                logic.getDisabledPositions().forEach(p -> cells.entrySet().stream()
                    .filter(en -> en.getValue().equals(p))
                    .forEach(en -> en.getKey().setEnabled(false)));
            } else {
                logic.reset();
            }
        }); 
        main.add(BorderLayout.SOUTH, new JButton("Go"));
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if(logic.hit(position)) {
                    button.setText("*");
                } else {
                    button.setText(" ");
                }
                   //button.setText(""+position);
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer,Integer>(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
}
