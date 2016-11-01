package com.phoenixkahlo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class Introspection {
	
	private JFrame frame;
	private DefaultMutableTreeNode top;
	private boolean displayed = false;
	
	public Introspection() {
		top = new DefaultMutableTreeNode("introspection");
	}
	
	public void introspect(Object object) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("class=" + object.getClass().getSimpleName() + "    hashCode=" + object.hashCode());
		SwingUtilities.invokeLater(() -> {
			top.add(node);
			if (frame != null) {
				System.out.println("frame != null");
				frame.revalidate();
				frame.validate();
				frame.repaint();
			} else System.out.println("frame == null");
		});
		
	}
	
	public void display() {
		if (!displayed) {
			JTree tree = new JTree(top);
			JScrollPane treeView = new JScrollPane(tree);
			frame = new JFrame("Introspection");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(treeView);
			frame.setSize(600, 800);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			displayed = true;
		}
	}
	
	public static void main(String[] args) {
		List<Exception> exceptions = new ArrayList<Exception>();
		exceptions.add(new RuntimeException("durr"));
		exceptions.add(new ArrayIndexOutOfBoundsException());
		exceptions.add(new NoSuchElementException());
		Object haha = "haha";
		Introspection introspection = new Introspection();
		introspection.introspect(exceptions);
		introspection.display();
		introspection.introspect(haha);
	}
	
}
