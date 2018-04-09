import { Component, OnInit } from '@angular/core';
import { Movie } from '../utils/movie';
import { MovieService } from '../movie.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-favorites',
  template: `
  <div *ngIf="favorites == undefined || favorites.length == 0; then emptyFavorites; else listingPage"></div>

    <ng-template #emptyFavorites>
      <div class="media-el">
        <h2 class="title">Currently, you don't have any favorites</h2>
      </div>
    </ng-template>

    <ng-template #listingPage>
      <movie-listing [isFavoritesPage]="true" [title]="'Favorites'" [movies] = "favorites" ></movie-listing>
    </ng-template>

  
  `,
  styles: []
})
export class FavoritesComponent implements OnInit {

  private favorites : Movie[];

  constructor(private spinner : Ng4LoadingSpinnerService,private movieSvc : MovieService) { }

  ngOnInit() {
    this.spinner.show();
    this.movieSvc.getAllMovies().subscribe(movies => {
      this.favorites = movies;
      this.favorites.forEach(movie => {
        movie.favorite = true;
      });
      this.spinner.hide();
    });
  }

}
