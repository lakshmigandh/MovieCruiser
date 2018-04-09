import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//Components
import { MovieSearchResultsComponent } from './movie-search-results/movie-search-results.component';
import { HomeComponent } from './home/home.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';

const routes: Routes = [
{ path: '', redirectTo: '/home', pathMatch: 'full' },
{ path: 'home', component: HomeComponent },
{ path: 'search/:query', component: MovieSearchResultsComponent },
{ path: 'movie-detail/:id', component: MovieDetailComponent },
{ path: 'favorites', component: FavoritesComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [
  HomeComponent,
  FavoritesComponent,
  MovieSearchResultsComponent,
  MovieDetailComponent
];
