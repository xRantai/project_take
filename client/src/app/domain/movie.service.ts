import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders } from "@angular/common/http";
import {Movie} from "./entity/Movie";
import {Observable} from "rxjs";
import {Category} from "./entity/Category";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private MoviesApiUrl = 'http://localhost:8080/movies';
  private BasketApiUrl = 'http://localhost:8080/basket';

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    const url = this.MoviesApiUrl + '/getAll'
    return this.http.get<Movie[]>(url)
  }

  getMoviesByCategory(category: string): Observable<Movie[]> {
    const url = this.MoviesApiUrl + '/getByCategory/' + category;
    return this.http.get<Movie[]>(url)
  }

  getCategories(): Observable<Category[]> {
    const url = this.MoviesApiUrl + '/getCategories';
    return this.http.get<Category[]>(url)
  }

  getMovieById(id: number): Observable<Movie> {
    const url = this.MoviesApiUrl + '/getById/' + id;
    return this.http.get<Movie>(url)
  }

  getBasket(): Observable<Movie[]> {
    const url = this.BasketApiUrl + '/getContents'
    return this.http.get<Movie[]>(url)
  }

  addToBasket(id: number): void {
    const url = this.BasketApiUrl + '/add/' + id;
    this.http.post<void>(url, {}, httpOptions).subscribe();
  }

  removeFromBasket(id: number): Observable<void> {
    const url = this.BasketApiUrl + '/delete/' + id;
    return this.http.delete<void>(url);
  }

  getTotal(): Observable<number> {
    const url = this.BasketApiUrl + '/getTotal';
    return this.http.get<number>(url);
  }
}
