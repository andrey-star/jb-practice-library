import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {User} from '../models/user';

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
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(environment.apiUrl + '/users');
  }

  public getUserById(userId: number): Observable<User> {
    return this.http.get<User>(environment.apiUrl + '/users/' + userId);
  }

  public addUser(user: User): Observable<User> {
    return this.http.post<User>(environment.apiUrl + '/users', user, httpOptions);
  }

  public addBook(bookId: number, userId: number): Observable<User> {
    return this.http.put<User>(environment.apiUrl + `/users/${userId}/books/${bookId}`, {}, httpOptions);
  }

  public deleteUser(userId: number): Observable<string> {
    // @ts-ignore
    return this.http.delete<string>(environment.apiUrl + `/users/${userId}`, httpOptionsText);
  }
}
