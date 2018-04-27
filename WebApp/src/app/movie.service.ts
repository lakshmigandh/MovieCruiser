import { Injectable } from '@angular/core';
import { Config } from './utils/config';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Movie } from './utils/movie';
import { ApiResponse } from './utils/api-response';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class MovieService {

  private headers = new HttpHeaders().append('Content-Type','application/json')
                                     .append('Accept','application/json')
                                     .append('Authorization','Bearer ' + localStorage.getItem("token"));

  constructor(private http:HttpClient) { }


  public checkIfFavorite(movies : Movie[],favorites : Object) {
    movies.forEach(movie => {
      if(favorites[movie.id] != undefined)
        movie.favorite = true;
    });
    
  }

  public search(searchQuery : string) {
    let urlParam = new Config.URLParam();
    urlParam.searchString=searchQuery;
    let endPoint = Config.Helper.getAbsoluteAPIURL(Config.URL.SEARCH,urlParam);
    return this.http.get<ApiResponse>(endPoint);
 }

 public getTrending() {
  let endPoint = Config.Helper.getAbsoluteAPIURL(Config.URL.TRENDING,null);
  return this.http.get<ApiResponse>(endPoint);
}

public getUpcoming() {
  let endPoint = Config.Helper.getAbsoluteAPIURL(Config.URL.UPCOMING,null);
  return this.http.get<ApiResponse>(endPoint);
}

public saveOrUpdateMovie(movie : Movie) {
  const json = JSON.stringify(movie);
  let endPoint = Config.Helper.getAbsoluteURL(Config.URL.SAVE_OR_UPDATE_MOVIE,null);
  return this.http.post(endPoint, movie,{headers:this.headers});
}

public deleteMovie(movieId : number) {
  let urlParam = new Config.URLParam();
  urlParam.movieId=movieId;
  let endPoint = Config.Helper.getAbsoluteURL(Config.URL.DELETE_MOVIE_BY_ID,urlParam);
  return this.http.delete(endPoint,{headers:this.headers});
}

public getAllMovies() {
  let endPoint = Config.Helper.getAbsoluteURL(Config.URL.GET_ALL_MOVIES,null);
  return this.http.get<Movie[]>(endPoint,{headers:this.headers});
}

public getMovie(movieId : number) {
  let urlParam = new Config.URLParam();
  urlParam.movieId=movieId;
  let endPoint = Config.Helper.getAbsoluteURL(Config.URL.GET_MOVIE_BY_ID,urlParam);
  return this.http.get<Movie>(endPoint,{headers:this.headers});
}

public getMovieDetail(movieId : number) {
  let urlParam = new Config.URLParam();
  urlParam.movieId=movieId;
  let endPoint = Config.Helper.getAbsoluteAPIURL(Config.URL.GET_MOVIE_DETAIL,urlParam);
  return this.http.get<Movie>(endPoint);
}

public getRecommendedMovies(movieId : number) {
  let urlParam = new Config.URLParam();
  urlParam.movieId=movieId;
  let endPoint = Config.Helper.getAbsoluteAPIURL(Config.URL.RECOMMENDED,urlParam);
  return this.http.get<ApiResponse>(endPoint);
}



}
