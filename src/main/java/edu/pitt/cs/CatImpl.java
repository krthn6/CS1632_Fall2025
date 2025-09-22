package edu.pitt.cs;

public class CatImpl implements Cat {

	// TODO: Fill in with member variables
	private String name; 
	private int id;
	private boolean rented; 
	


	public CatImpl(int id, String name) {
		// TODO: Fill in
		this.name = name; 
		this.id = id; 
		this.rented = false;
	}

        @Override
	public void rentCat() {
		// TODO: Fill in
		this.rented = true;

	}

        @Override
	public void returnCat() {
		// TODO: Fill in
		this.rented = false;
	}

        @Override
	public void renameCat(String name) {
		// TODO: Fill in
		this.name = name;
	}

        @Override
	public String getName() {
		// TODO: Fill in
		return this.name;
	}

        @Override
	public int getId() {
		// TODO: Fill in
		return this.id;
	}

        @Override
	public boolean getRented() {
		// TODO: Fill in
		return this.rented;
	}

        @Override
	public String toString() {
		// TODO: Fill in
		return "ID " +id+ ". " +name;
	}

}