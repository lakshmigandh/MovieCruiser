import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import { Movie } from '../utils/movie';
import { MovieService } from '../movie.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'movie-detail',
  template: `

  <div class="media-el">
  <div class="results flex results_poster_mcard">

  <div *ngIf = "selectedMovie != null" class="item poster mcard col-md-12 movie_dtl_wrapper">
      <div class="col-md-6">
        <img class="d-flex mr-3 movie-poster" src="https://image.tmdb.org/t/p/w300_and_h450_bestv2/{{selectedMovie.poster_path}}" alt="{{selectedMovie.title}}">
      </div>
        <div class="info_dtl col-md-6">
        <div class="wrapper">
          <div class="flex">
          <a class="title result">{{selectedMovie.title}}</a>
          <span class="subdtl_movie">Release Date: {{selectedMovie.release_date}}</span>
          <span class="subdtl_movie">Rating: {{selectedMovie.vote_average}}</span>
          <p class="overview">{{selectedMovie.overview}}</p>
          </div>
        </div>
        <a><i class="fa favorite_icon" [ngClass]="{'fa-heart' : selectedMovie.favorite , 'fa-heart-o' : !selectedMovie.favorite }"></i></a>
        <div class="form-group green-border-focus" style="margin-top: 20px;">
          <textarea [(ngModel)]="selectedMovie.comment" placeholder="Comments" class="form-control" rows="3">
           {{selectedMovie.comment}}
          </textarea>
        </div>
          <button (click)="saveMovie(selectedMovie);" type="button" class="btn btn-success">Save</button>
        </div>
      </div>
</div>

      <movie-listing *ngIf = "recommendedMovies != null" [title]="'Recommended Movies'" [movies] = "recommendedMovies" ></movie-listing>

  </div>    
  `,
  styles: []
})
export class MovieDetailComponent implements OnInit {

  private selectedMovie : Movie;
  private recommendedMovies : Movie[];
  private favorites = new Object();

  constructor(private spinner : Ng4LoadingSpinnerService,private movieSvc : MovieService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.spinner.show();
    this.movieSvc.getAllMovies().subscribe(movies => {
      movies.forEach(movie => {
        this.favorites[movie.id] = movie;
        
      });
    });

    this.route.paramMap.subscribe((params: ParamMap) => {
      let movieId = parseInt(params.get('id'));
      console.log(movieId);
      this.movieSvc.getMovieDetail(movieId).subscribe(movie => {
        this.selectedMovie = movie;

        if(this.favorites[movie.id] !== undefined){
            this.selectedMovie.favorite = true;
            this.selectedMovie.comment = this.favorites[movie.id].comment;
        }
        console.log(this.selectedMovie);
        this.movieSvc.getRecommendedMovies(movieId).subscribe(apiResponse => {
          this.recommendedMovies = apiResponse.results;
          this.movieSvc.checkIfFavorite(this.recommendedMovies,this.favorites);
        });
        this.spinner.hide();
      });
  
      
    });

    

  }

  


  private saveMovie(movie : Movie){
    this.spinner.show();
    movie.favorite=true; 
    this.movieSvc.saveOrUpdateMovie(movie).subscribe(response => {
      this.spinner.hide();
    });
  }

}
