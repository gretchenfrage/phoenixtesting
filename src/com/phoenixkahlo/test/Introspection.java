package com.phoenixkahlo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JFrame;

public class Introspection {
	
	private List<Object> objects;
	
	public Introspection(Object... objects) {
		this.objects = new ArrayList<>(Arrays.asList(objects));
	}
	
	public void display() {
		JFrame frame = new JFrame("Introspection");
		
		
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		List<Exception> exceptions = new ArrayList<Exception>();
		exceptions.add(new RuntimeException("durr"));
		exceptions.add(new ArrayIndexOutOfBoundsException());
		exceptions.add(new NoSuchElementException());
		Object haha = "haha";
		Introspection introspection = new Introspection(exceptions, haha);
		introspection.display();
	}
	
}
