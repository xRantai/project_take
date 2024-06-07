import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {MovieService} from "../domain/movie.service";
import {Movie} from "../domain/entity/Movie";
import {NavigationEnd, Router, RouterLink, RouterModule} from "@angular/router";
import {filter} from "rxjs";
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import {Category} from "../domain/entity/Category";

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    RouterLink,
    RouterModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  providers: [MovieService],
  templateUrl: './movies.component.html',
  styleUrl: './movies.component.css'
})
export class MoviesComponent implements OnInit {
  movies: Movie[] = [];
  categories: Category[] = [];
  selected = 'None';

  constructor(private movieService: MovieService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getPersistedCategory();

    this.movieService.getCategories().subscribe((categories) => {
      this.categories = categories;
    });
  }

  loadMovies(): void {
    if (this.selected === 'None') {
      this.movieService.getMovies().subscribe(movies => {
        this.movies = movies;
      });
    } else {
      this.movieService.getMoviesByCategory(this.selected).subscribe(movies => {
        this.movies = movies;
      });
    }
  }

  reload(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      window.location.reload();
    });
  }

  onCategoryChange(): void {
    this.setPersistedCategory();
    this.loadMovies();
  }

  // Method to persist selected category
  setPersistedCategory(): void {
    localStorage.setItem('selectedCategory', this.selected);
  }

  // Method to get persisted category
  getPersistedCategory(): void {
    const persistedCategory = localStorage.getItem('selectedCategory');
    if (persistedCategory)
      this.selected = persistedCategory;

    this.loadMovies();
  }
}

