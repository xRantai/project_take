import {Component, OnInit} from '@angular/core';
import {Movie} from "../domain/entity/Movie";
import {MovieService} from "../domain/movie.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    RouterLink
  ],
  templateUrl: './movie-detail.component.html',
  styleUrl: './movie-detail.component.css'
})
export class MovieDetailComponent implements OnInit {
  movie?: Movie

  constructor(
    private movieService: MovieService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.getMovie()
  }

  getMovie(): void {
    const pathId = this.route.snapshot.paramMap.get('id');
    if (pathId) {
      this.movieService.getMovieById(+pathId)
        .subscribe(movie => this.movie = movie)
    }
  }

  addItemToBasket(): void {
    const pathId = this.movie?.id;

    if (pathId)
      this.movieService.addToBasket(+pathId);
  }

}
