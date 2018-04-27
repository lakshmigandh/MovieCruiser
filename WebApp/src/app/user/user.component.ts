import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import { UserService } from '../user.service';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { User } from '../utils/user';
import { UserAuthResponse } from '../utils/user-auth-response';
import { Config } from '../utils/config';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'user',
  template: `
  <div class="container">
   <div class="row">
    <div class="col-md-6 userForm">
      <div class="panel panel-login">
        <div class="panel-body">
        <alert-message [alertLevel]="alertLevel" [alertMessage] = "alertMsg"></alert-message>
          <div class="row">
            <div class="col-lg-12">
            <div *ngIf="pageContext == 'register'; then registerForm; else loginForm"></div>
            <ng-template #loginForm>
              <form id="login-form" action="#" method="post" role="form" style="display: block;">
                <h2>Sign In</h2>
                  <div class="form-group">
                    <input type="text" [(ngModel)]="user.id" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
                  </div>
                  <div class="form-group">
                    <input type="password" [(ngModel)]="user.password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                  </div>
                  <!--<div class="col-xs-6 form-group pull-left checkbox">
                    <input id="checkbox1" type="checkbox" name="remember">
                    <label for="checkbox1">Remember Me</label>   
                  </div>-->
                  <div class="col-xs-6 form-group">     
                        <input (click)="login();" type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Sign In">
                  </div>
                  <div class="col-lg-12 my-3">
												<div class="text-center">
													<a href="/#/register" tabindex="5" class="forgot-password">Don't have an account ? Sign Up</a>
												</div>
									</div>
              </form>
              </ng-template>

              <ng-template #registerForm>
              <form id="register-form" action="#" method="post" role="form">
                <h2>Sign Up</h2>
                  <div class="form-group">
                    <input required (blur)="exists();" type="text" [(ngModel)]="user.id" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
                  </div>
                  <div class="form-group">
                    <input required type="text"  [(ngModel)]="user.firstName" name="firstname" id="firstname" tabindex="1" class="form-control" placeholder="Firstname" value="">
                  </div>
                  <div class="form-group">
                    <input required type="text"  [(ngModel)]="user.lastName" name="lastname" id="lastname" tabindex="1" class="form-control" placeholder="Lastname" value="">
                  </div>
                  <div class="form-group">
                    <input type="email"  [(ngModel)]="user.email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">
                  </div>
                  <div class="form-group">
                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                  </div>
                  <div class="form-group">
                    <input type="password"  [(ngModel)]="user.password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-md-12">
                        <input (click)="register();" type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Sign Up">
                      </div>

                      <div class="col-lg-12 my-3">
												<div class="text-center">
													<a href="/#/login" tabindex="5" class="forgot-password">Have an account already ? Sign In</a>
												</div>
									</div>
                    </div>
                  </div>
              </form>
              </ng-template>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
  `,
  styles: []
})
export class UserComponent implements OnInit {

  constructor(private spinner : Ng4LoadingSpinnerService, private userSvc : UserService,private router: Router, private route: ActivatedRoute) {
    this.user = {} as User;
   }

  public pageContext : string;
  public user : User;
  public alertLevel : Config.AlertType;
  public alertMsg : string;

  ngOnInit() {
    this.spinner.show();
    this.route.url.subscribe(params => {
      this.pageContext = params[0].path;
    });

    if(this.pageContext == 'logout'){
      localStorage.removeItem('token');
      localStorage.removeItem('name');
      localStorage.removeItem('userId');
       window.location.reload();
       this.router.navigate(['']);
    }

   

    this.spinner.hide();
  }

  public login() {
    this.spinner.show();
    if(this.user === undefined)
      return;
    this.userSvc.login(this.user).subscribe(response => {
      console.log(response);
      if(response.errorCode){
        this.alertLevel = Config.AlertType.ERROR;
        this.alertMsg = response.errorMsg;
      }
      else if (response.token) {
        this.router.navigate(['/home']);
        localStorage.setItem('token', response.token);
        localStorage.setItem('userId', response.userId);
        localStorage.setItem('name', response.firstName);
        window.location.reload();
      }
      this.spinner.hide();
    }
  );  
  }


  public exists() {
    if(this.user.id === undefined)
      return;
    this.spinner.show();
    this.userSvc.exists(this.user.id).subscribe(response => {
      if(response == true){
        this.alertLevel = Config.AlertType.ERROR;
        this.alertMsg = "User Id already exists";
      }
      else if (response == false) {
        this.alertLevel = Config.AlertType.SUCCESS;
        this.alertMsg = "User Id is available";
      }
      this.spinner.hide();
    }
  );  
  }

  public register() {
    if(this.user === undefined)
      return;
    this.spinner.show();
      this.userSvc.register(this.user).subscribe(response => {
      if (response === -2) {
        this.alertLevel = Config.AlertType.ERROR;
        this.alertMsg = "Please enter all fields";
      }
      else if (response === 0) {
        this.alertLevel = Config.AlertType.SUCCESS;
        this.alertMsg = "User created successfully";
      }
      this.spinner.hide();
    });  
  }


  

}
