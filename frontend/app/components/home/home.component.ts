import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {Router} from '@angular/router';
import {Book} from '../../models/book';
import {BookService} from '../../services/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users: User[];
  books: Book[];
  searchIsbn: string;

  constructor(private userService: UserService,
              private bookService: BookService) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((users: User[]) => {
      this.users = users;
    });
    this.bookService.getBooks().subscribe((books: Book[]) => {
      this.books = books;
    });
  }

  addUser(user: User): void {
    this.userService.addUser(user).subscribe(addedUser => {
      this.users = [...this.users, addedUser];
    });
  }

  onSubmit(): void {
    if (this.searchIsbn !== undefined) {
      this.bookService.getBooksByIsbn(this.searchIsbn).subscribe(books => {
        this.books = books;
      });
    }
  }
}
