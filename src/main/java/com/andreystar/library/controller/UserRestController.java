package com.andreystar.library.controller;

import com.andreystar.library.entity.Book;
import com.andreystar.library.entity.User;
import com.andreystar.library.service.BookService;
import com.andreystar.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Resource
	private final UserService userService;
	@Resource
	private final BookService bookService;
	
	@Autowired
	public UserRestController(UserService userService, BookService bookService) {
		this.userService = userService;
		this.bookService = bookService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<User> getUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable int userId) {
		Optional<User> user = userService.findById(userId);
		if (user.isEmpty()) {
			throw new RuntimeException("User id not found: " + userId);
		}
		return user.get();
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		user.setId(0);
		userService.save(user);
		return user;
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable int userId) {
		Optional<User> user = userService.findById(userId);
		if (user.isEmpty()) {
			throw new RuntimeException("User id not found: " + userId);
		}
		userService.deleteById(userId);
		return "Deleted user with id: " + userId;
	}
	
	@GetMapping("/{userId}/books")
	public Set<Book> getBooks(@PathVariable int userId) {
		Optional<User> user = userService.findById(userId);
		if (user.isEmpty()) {
			throw new RuntimeException("User id not found: " + userId);
		}
		return user.get().getBooks();
	}
	
	@PutMapping("/{userId}/books/{bookId}")
	public User addBook(@PathVariable int userId, @PathVariable int bookId) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User id not found: " + userId);
		}
		Optional<Book> book = bookService.findById(bookId);
		if (book.isEmpty()) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		User user = userOptional.get();
		user.addBook(book.get());
		userService.save(user);
		return user;
	}
	
}
