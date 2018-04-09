import { Component, OnInit, } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'movie-search',
  template: `

   <form class="form-inline my-2 my-lg-0">
        <input name="Searchbox" #searchtextfield [(ngModel)]="searchQuery" id="search-text-field" class="form-control " (keyup.enter)="search();searchtextfield.value=''" type="text" placeholder="Search" aria-label="Search">
   </form>

  `,
  styles: []
})
export class MovieSearchComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {}

  public searchQuery: string;

  public search() {
    if(this.searchQuery === undefined || this.searchQuery === '')
      return;
    const link = ['/search/'+this.searchQuery];
    this.router.navigate(link);
  }

 
}