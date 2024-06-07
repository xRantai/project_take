import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {Movie} from "../domain/entity/Movie";
import {MovieService} from "../domain/movie.service";

@Component({
  selector: 'app-basket',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './basket.component.html',
  styleUrl: './basket.component.css'
})
export class BasketComponent implements OnInit {

  movies: Movie[] = [];
  total: number = 0;

  constructor(private movieService: MovieService) {
  }

  ngOnInit(): void {
    this.loadBasket();
  }

  loadBasket(): void {
    this.movieService.getBasket().subscribe(movies => {
      this.movies = movies;
    });

    this.movieService.getTotal().subscribe(total => {
      this.total = total;
    })
  }

  removeFromBasket(id: number): void {
    this.movieService.removeFromBasket(id).subscribe(() =>{
      this.loadBasket();
    });
  }
}
