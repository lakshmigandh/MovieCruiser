import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';

//Components
import { MovieSearchResultsComponent } from './movie-search-results/movie-search-results.component';
import { HomeComponent } from './home/home.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';
import { UserComponent } from './user/user.component';
import { AuthGuardService as AuthGuard } from './auth-guard.service';

const routes: Routes = [
{ path: '', redirectTo: '/home', pathMatch: 'full' },
{ path: 'login', component: UserComponent },
{ path: 'register', component: UserComponent },
{ path: 'logout', component: UserComponent },
{ path: 'home', component: HomeComponent, canActivate:[AuthGuard] },
{ path: 'search/:query', component: MovieSearchResultsComponent, canActivate:[AuthGuard] },
{ path: 'movie-detail/:id', component: MovieDetailComponent, canActivate:[AuthGuard] },
{ path: 'favorites', component: FavoritesComponent, canActivate:[AuthGuard] }];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [
  HomeComponent,
  FavoritesComponent,
  MovieSearchResultsComponent,
  MovieDetailComponent,
  UserComponent
];
