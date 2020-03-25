package com.github.nickid2018.rdt.reader;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

import com.github.nickid2018.rdt.RDTReader;

public class RDTWalker extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3456294980920883046L;
	private JPanel on;
	private JTextField fn;
	private static JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RDTWalker frame = new RDTWalker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RDTWalker() {
		setTitle("RDTWalker");
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 600);
		on = new JPanel();
		on.setBorder(new EmptyBorder(5, 5, 5, 5));
		on.setLayout(new BorderLayout(0, 0));
		setContentPane(on);

		JPanel panel = new JPanel();
		on.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel a = new JLabel("File");
		a.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		panel.add(a, BorderLayout.WEST);

		fn = new JTextField();
		fn.addActionListener(e -> {
			
		});
		fn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		panel.add(fn, BorderLayout.CENTER);
		fn.setColumns(10);

		JButton cho = new JButton("Choose");
		cho.addActionListener(e -> {
			JFileChooser jfc = new JFileChooser();
			FileFilter ff = new FileNameExtensionFilter("RDT File", "rdt");
			jfc.setFileFilter(ff);
			int i = jfc.showOpenDialog(this);
			String f;
			if (i == JFileChooser.APPROVE_OPTION) {
				f = jfc.getSelectedFile().getAbsolutePath();
			} else {
				return;
			}
			fn.setText(f);
		});
		cho.setMnemonic('C');
		cho.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		panel.add(cho, BorderLayout.EAST);

		JScrollPane ino = new JScrollPane();
		ino.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		on.add(ino, BorderLayout.CENTER);

		tree = new JTree();
		tree.setRootVisible(false);
		tree.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		ino.setViewportView(tree);
	}

}
