import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, OnChanges {

  @Input() users;
  displayedColumns = ['username', 'books', 'link'];

  ngOnChanges(changes: SimpleChanges): void {
    const change = changes.users;
    if (change && !change.isFirstChange()) {
      this.users = change.currentValue;
    }
  }

  constructor() {
  }

  ngOnInit(): void {
  }

}
