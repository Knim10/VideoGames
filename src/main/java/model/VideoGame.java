package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Kenneth Nimmo - Knimmo
 * CIS175 - Fall 2021
 * Sep 13, 2023
 */
@Entity
@Table(name="items")
public class VideoGame {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="STORE")
	private String store;
	@Column(name="GAME")
	private String game;
	@Column(name="PRICE")
	private double price;
	
	public VideoGame() {
		super();
	}
	
	public VideoGame(String store, String game, double price) {
		super();
		this.store = store;
		this.game = game;
		this.price = price;
	}
	
	public void setStore(String store) {
		this.store = store;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStore() {
		return this.store;
	}
	
	public String getGame() {
		return this.game;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getGameDetails() {
		return "Store: " + this.store + ", Game: " + this.game + ", Price: $" + this.price;
	}

}
