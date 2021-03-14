import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from '../models/book';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

const httpOptionsText = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  }),
  responseType: 'text'
};

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  public getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(environment.apiUrl + '/books');
  }

  public getBookById(bookId: number): Observable<Book> {
    return this.http.get<Book>(environment.apiUrl + '/books/' + bookId);
  }

  public getBooksByIsbn(isbn: string): Observable<Book[]> {
    return this.http.get<Book[]>(environment.apiUrl + `/books?isbn=${isbn}`);
  }

  public addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(environment.apiUrl + '/books', book, httpOptions);
  }

  public updateBook(book: Book): Observable<Book> {
    return this.http.put<Book>(environment.apiUrl + '/books', book, httpOptions);
  }

  public deleteBook(bookId: number): Observable<string> {
    // @ts-ignore
    return this.http.delete<string>(environment.apiUrl + `/books/${bookId}`, httpOptionsText);
  }
}
