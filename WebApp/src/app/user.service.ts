import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { User } from './utils/user';
import { UserAuthResponse } from './utils/user-auth-response';
import { Config } from './utils/config';


@Injectable()
export class UserService {

  private headers = new HttpHeaders().append('Content-Type','application/json')
                                     .append('Accept','application/json');

	constructor(private http: HttpClient) {}

	public login(user:User) {
      let endPoint = Config.Helper.getAbsoluteUserSvcURL(Config.URL.LOGIN,null);
      return this.http.post<UserAuthResponse>(endPoint, user,{headers:this.headers});
  }
  
  public register(user:User) {
    let endPoint = Config.Helper.getAbsoluteUserSvcURL(Config.URL.REGISTER,null);
    return this.http.post(endPoint, user,{headers:this.headers});
  }

  public exists(userId:string) {
    let urlParam = new Config.URLParam();
    urlParam.userId=userId;
    let endPoint = Config.Helper.getAbsoluteUserSvcURL(Config.URL.EXISTS,urlParam);
    return this.http.get(endPoint); 
 }
 
	private handleError(error: any) {
		console.error('An error occurred', error);
  }
  
  public isUserLoggedIn(){
      return localStorage.getItem('token');
  }
  

}
