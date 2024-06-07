import {Routes} from '@angular/router';
import {MovieDetailComponent} from "./movie-detail/movie-detail.component";
import {BasketComponent} from "./basket/basket.component";

export const routes: Routes = [
  {path: 'detail/:id', component: MovieDetailComponent},
  {path: 'basket', component: BasketComponent},
  {path: '', pathMatch: "full", redirectTo: "/detail/1"}
];
