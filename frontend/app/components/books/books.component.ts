import {Component, Input, OnInit} from '@angular/core';
import {Book} from '../../models/book';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  @Input() books: Book[];
  displayedColumns = ['title', 'author', 'isbn' , 'link'];

  constructor() { }

  ngOnInit(): void {
  }

}
