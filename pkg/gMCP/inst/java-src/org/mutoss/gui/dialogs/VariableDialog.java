package org.mutoss.gui.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.mutoss.gui.CreateGraphGUI;
import org.mutoss.gui.RControl;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class VariableDialog extends JDialog implements ActionListener {
	JButton ok = new JButton("Ok");

	Hashtable<String,Double> ht = new Hashtable<String,Double>(); 

    CreateGraphGUI parent;
    Object[] variables;
    List<JTextField> jtl;
    
	public VariableDialog(CreateGraphGUI parent, Set<String> v) {
		super(parent, "Correlated test statistics?", true);
		setLocationRelativeTo(parent);
		this.parent = parent;
		variables = v.toArray();
				
        String cols = "5dlu, pref, 5dlu, fill:pref:grow, 5dlu";
        String rows = "5dlu, pref, 5dlu, pref, 5dlu, pref, 5dlu, pref, 5dlu";
        
        FormLayout layout = new FormLayout(cols, rows);
        getContentPane().setLayout(layout);
        CellConstraints cc = new CellConstraints();

        int row = 2;
        
        jtl = new Vector<JTextField>();
        
        for (Object s : variables) {        	
        	JTextField jt = new JTextField("0");
        	getContentPane().add(new JLabel("Value for '"+s+"':"), cc.xy(2, row));
        	getContentPane().add(jt, cc.xy(4, row));
        	jtl.add(jt);

        	row += 2;
        }
        
                
        getContentPane().add(ok, cc.xy(4, row));
        ok.addActionListener(this);        
        
        pack();
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			//Fill HashTable:
			for (int i=0; i<variables.length; i++) {
				double value = RControl.getR().eval(jtl.get(i).getText()).asRNumeric().getData()[0];
				ht.put(variables[i].toString(), value);
			}
			dispose();
		}
	}	
	
	public Hashtable<String,Double> getHT() {
		return ht;
	}
}