import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class gui {
	
	public static void main(String[] args) {
		frame f = new frame();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));
		//add button
		for(int i =0; i<9; i++) {
			JButton button = new JButton("Button " + i);
			button.addActionListener(event -> {
				System.out.println("Button clicked " + event.getActionCommand());
				button.setText("Clicked!");
			});
			panel.add(button);
		}
		
		
		f.add(panel, BorderLayout.CENTER);
		f.setVisible(true);
		
	}

	

}
