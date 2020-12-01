package com.vermeg.ams.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.vermeg.ams.entities.Book;

@Entity
public class Command {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	// @NotBlank(message = "date is mandatory")
	@Column(name = "date")
	private LocalDate date;

	// @NotBlank(message = "totalPrice is mandatory")
	@Column(name = "totalPrice")
	private Double totalPrice;

	public Command(LocalDate date, Double totalPrice) {
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public Command() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/*private List<LigneCommand> ligneCommands;

	@OneToMany(mappedBy = "command")
	public List<LigneCommand> getLigneCommands() {
		return ligneCommands;
	}

	public void setLigneCommands(List<LigneCommand> ligneCommands) {
		this.ligneCommands = ligneCommands;
	}*/

}
