import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BookService} from '../../services/book.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  author: string;
  title: string;
  isbn: string;

  constructor(private bookService: BookService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const book = {
      author: this.author,
      title: this.title,
      isbn: this.isbn
    };
    this.bookService.addBook(book)
      .subscribe(addedBook => this.router.navigate(['/']));
  }
}
