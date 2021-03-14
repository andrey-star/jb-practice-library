import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  @Output() addUser: EventEmitter<any> = new EventEmitter();

  username: string;

  constructor() {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const user = {
      username: this.username
    };
    this.addUser.emit(user);
  }

}
