import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { AppComponent } from './app.component';
import { AppHeaderComponent } from './app-header/app-header.component';
import { AppFooterComponent } from './app-footer/app-footer.component';
import { MovieSearchComponent } from './movie-search/movie-search.component';
import { ListingComponent } from './listing/listing.component';
import { MovieService } from './movie.service';
import { UserService } from './user.service';
import { AuthGuardService } from './auth-guard.service';
import {routingComponents} from './app-routing.module';
import { AlertComponent } from './alert/alert.component';




@NgModule({
  declarations: [
    AppComponent,
    AppHeaderComponent,
    AppFooterComponent,
    MovieSearchComponent,
    ListingComponent,
    routingComponents,
    AlertComponent
  ],
  imports: [
    Ng4LoadingSpinnerModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [MovieService,UserService,AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
