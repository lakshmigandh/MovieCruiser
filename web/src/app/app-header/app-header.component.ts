import { Component, OnInit } from '@angular/core';
import { MovieSearchComponent } from '../movie-search/movie-search.component';

@Component({
  selector: 'app-header',
  template: `
   
  <nav class="app-footer-bg navbar navbar-expand-sm navbar-dark ">
  <div class="container">
    <a class="navbar-brand" href="#"><span id="brand-title">{{ title }}</span></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item ">
          <a class="nav-link" [routerLink]="['/home']" [routerLinkActive]="['active']">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" [routerLink]="['/favorites']" [routerLinkActive]="['active']">Favorites</a>
        </li>
      </ul>
      <movie-search></movie-search>
    </div>
  </div>
</nav>
`,
  styles: [`
  
  #brand-title{
    font-size: 2em; 
    margin-left: 30px;
  }
  
  
  `]
})
export class AppHeaderComponent implements OnInit {
  
  title = 'Movie Cruiser';

  constructor() { }

  ngOnInit() {
  }

}
