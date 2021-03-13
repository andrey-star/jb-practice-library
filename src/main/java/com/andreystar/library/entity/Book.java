package com.andreystar.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "title")
	private String title;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "readers_books",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "reader_id")
	)
	@JsonIgnore
	private Set<Reader> readers;
	
	@CreationTimestamp
	private Instant creationTime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Set<Reader> getReaders() {
		return readers;
	}
	
	public void setReaders(Set<Reader> readers) {
		this.readers = readers;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book) o;
		return Objects.equals(isbn, book.isbn) &&
		       Objects.equals(author, book.author) &&
		       Objects.equals(title, book.title);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}
}
