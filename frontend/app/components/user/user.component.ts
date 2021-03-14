import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../models/user';
import {UserService} from '../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BookService} from '../../services/book.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User;
  loading = false;
  error = false;
  addIsbn: string;

  constructor(private userService: UserService,
              private bookService: BookService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.error = false;
    this.route.queryParams
      .subscribe(params => {
        const userId = params.userId;
        if (userId === undefined) {
          this.error = true;
        } else {
          this.userService.getUserById(userId).subscribe(user => {
              this.loading = false;
              this.user = user;
            },
            error => this.error = true);
        }
      });
  }

  onSubmit(): void {
    this.bookService.getBooksByIsbn(this.addIsbn).subscribe(book => {
      if (book !== undefined) {
        this.userService.addBook(book[0].id, this.user.id).subscribe(updatedUser => this.user = updatedUser);
      }
    });
  }

  onDelete(): void {
    this.userService.deleteUser(this.user.id)
      .subscribe(result => {
        this.router.navigate(['/']);
      });
  }
}
