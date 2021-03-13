package com.andreystar.library.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "readers")
public class Reader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "readers_books",
			joinColumns = @JoinColumn(name = "reader_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private Set<Book> books;
	
	@CreationTimestamp
	private Instant creationTime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Set<Book> getBooks() {
		return books;
	}
	
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		if (books == null) {
			books = new HashSet<>();
		}
		books.add(book);
	}
}
