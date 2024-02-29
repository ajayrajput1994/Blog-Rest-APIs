package com.bloging.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "blogs")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	private LocalDateTime date;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	 
	
	
}
