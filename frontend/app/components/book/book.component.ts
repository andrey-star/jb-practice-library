import {Component, OnInit} from '@angular/core';
import {BookService} from '../../services/book.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Book} from '../../models/book';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  book: Book;
  loading = false;
  error = false;
  changeIsbn: string;

  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.error = false;
    this.route.queryParams
      .subscribe(params => {
        const bookId = params.bookId;
        if (bookId === undefined) {
          this.error = true;
        } else {
          this.bookService.getBookById(bookId).subscribe(book => {
              this.loading = false;
              this.book = book;
            },
            error => this.error = true);
        }
      });
  }

  onSubmit(): void {
    const newBook: Book = {
      id: this.book.id,
      isbn: this.changeIsbn,
      author: this.book.author,
      title: this.book.title
    };
    this.bookService.updateBook(newBook).subscribe(updatedBook => {
      this.book = updatedBook;
    });
  }

  onDelete(): void {
    this.bookService.deleteBook(this.book.id)
      .subscribe(result => {
        this.router.navigate(['/']);
      });
  }
}
