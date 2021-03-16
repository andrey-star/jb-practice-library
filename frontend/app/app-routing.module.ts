import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {UserComponent} from './components/user/user.component';
import {AddBookComponent} from './components/add-book/add-book.component';
import {BookComponent} from './components/book/book.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'user', component: UserComponent},
  {path: 'book', component: BookComponent},
  {path: 'add-book', component: AddBookComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
