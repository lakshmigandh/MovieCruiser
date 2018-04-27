import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import { Movie } from '../utils/movie';
import { MovieService } from '../movie.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'movie-search-results',
  template: `
  <div *ngIf="searchResult == undefined || searchResult.length == 0; then emptySearch; else searchResultsPage"></div>
  
  <ng-template #emptySearch>
      <div class="media-el">
        <h2 class="title">No results found. Please try again with different term</h2>
      </div>
    </ng-template>

    <ng-template #searchResultsPage>
      <movie-listing [title]="'Search Results'" [movies] = "searchResult" ></movie-listing>
    </ng-template>
   
  `,
  styles: []
})
export class MovieSearchResultsComponent implements OnInit {

  public searchResult : Movie[];
  public favorites = new Object();

  constructor(private spinner : Ng4LoadingSpinnerService, private movieSvc : MovieService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.spinner.show();
    this.movieSvc.getAllMovies().subscribe(movies => {
      movies.forEach(movie => {
        this.favorites[movie.id] = movie;
      });
    });

    this.route.paramMap.subscribe((params: ParamMap) => {
      let query = params.get('query');
      this.movieSvc.search(query).subscribe(apiResponse => {
        this.searchResult = apiResponse.results;
        this.movieSvc.checkIfFavorite(this.searchResult,this.favorites);
        this.spinner.hide();
      });
    });

    
  }

}
