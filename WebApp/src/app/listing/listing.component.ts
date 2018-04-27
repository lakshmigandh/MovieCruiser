import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../utils/movie';
import { MovieService } from '../movie.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'movie-listing',
  template: `
  <div class="media-el">
  <h2 class="title">{{title}}</h2>
    <div class="results flex results_poster_mcard">
      <div class="item poster mcard" *ngFor="let movie of movies">
          <a href="#/movie-detail/{{movie.id}}" class="image_content">
            <img class="d-flex mr-3 movie-poster" src="https://image.tmdb.org/t/p/w185_and_h278_bestv2/{{movie.poster_path}}" alt="{{movie.title}}">
          </a>
            <div class="info">
            <div class="wrapper">
              <div class="flex">
              <a class="title result">{{movie.title}}</a>
              <span class="subdtl_movie">Release Date: {{movie.release_date}}</span>
              <span class="subdtl_movie">Rating: {{movie.vote_average}}</span>
              <p class="overview">{{readMore(movie.overview)}}</p>
              </div>
            </div>
            <a (click)="addOrRemoveFromFavorites(!movie.favorite,movie)" ><i class="fa favorite_icon" [ngClass]="{'fa-heart' : movie.favorite , 'fa-heart-o' : !movie.favorite }"></i></a>
            </div>
          </div>
  </div>
</div>
  `,
  styles: []
})
export class ListingComponent implements OnInit {

  @Input('movies') public movies : Movie[];
  @Input('title')  public title : string;
  @Input('isFavoritesPage')  public isFavoritesPage : boolean;

  private maxWords = 30;

  constructor(private spinner : Ng4LoadingSpinnerService,private movieSvc : MovieService) { }

  ngOnInit() { }

  private addOrRemoveFromFavorites(isFavorite : boolean, movie : Movie){
    this.spinner.show();
    movie.favorite=isFavorite; 
    if(movie.favorite){
      this.movieSvc.saveOrUpdateMovie(movie).subscribe(response => {
        this.spinner.hide();
      });
    }
    else{
      this.movieSvc.deleteMovie(movie.id).subscribe(response => {
        if(this.isFavoritesPage) 
          this.movies.splice(this.movies.indexOf(movie), 1);
        this.spinner.hide();
      });
   
    }
  }

  private readMore(content : string) {           
    var myArray = content.split(" ");
    var arrLen = myArray.length;
    var myStr = myArray.splice(0, this.maxWords).join(" ");

    if(arrLen > this.maxWords) {
      myStr += "...";
    }

        return myStr ;
}

}
