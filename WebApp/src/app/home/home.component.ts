import { Component, OnInit } from '@angular/core';
import { Movie } from '../utils/movie';
import { MovieService } from '../movie.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-home',
  template: `
  <movie-listing [title]="'Trending'" [movies] = "upcomingMovies" ></movie-listing>
  <movie-listing [title]="'Upcoming'" [movies] = "trendingMovies" ></movie-listing>
  `,
  styles: []
})
export class HomeComponent implements OnInit {

  constructor(private spinner : Ng4LoadingSpinnerService,private movieSvc : MovieService) { }

  public trendingMovies : Movie[];
  public upcomingMovies : Movie[];
  public favorites = new Object();

  ngOnInit() {
      this.spinner.show();
      this.movieSvc.getAllMovies().subscribe(movies => {
        movies.forEach(movie => {
          this.favorites[movie.id] = movie;
        });
      });  

      this.movieSvc.getUpcoming().subscribe(apiResponse => {
        this.upcomingMovies = apiResponse.results;
        this.movieSvc.checkIfFavorite(this.upcomingMovies,this.favorites);
        this.spinner.hide();
      });

      this.movieSvc.getTrending().subscribe(apiResponse => {
        this.trendingMovies = apiResponse.results; 
        this.movieSvc.checkIfFavorite(this.trendingMovies,this.favorites);
        this.spinner.hide();
      });

      //
  }

}
