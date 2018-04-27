import { Component, OnInit } from '@angular/core';
import { MovieSearchComponent } from '../movie-search/movie-search.component';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  template: `
   
  <nav class="app-footer-bg navbar navbar-expand-sm navbar-dark ">
  <div class="container">
    <a class="navbar-brand" href="#"><span id="brand-title">{{ title }}</span></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div *ngIf="this.userSvc.isUserLoggedIn();" class="collapse navbar-collapse" id="navbarSupportedContent">
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
    <div *ngIf="this.userSvc.isUserLoggedIn(); then userSection;"></div>
    <ng-template #userSection>
      <div id="userSection" class="pull-right" style="
    min-width: 200px;
    margin-left: 40px;">
                <ul class="nav pull-right">
                    <li class="dropdown"> 
<i class="fa fa-user text-white" aria-hidden="true"></i><a href="#" class="dropdown-toggle ml-2 text-white" data-toggle="dropdown" aria-expanded="true">{{userName}}<b class="caret"></b></a>
                        <ul class="dropdown-menu" style="
    margin-top: 30px;">
                            
                            
                            <li class="divider"></li>
                            <li style=""><a class="text-dark ml-4" href="/#/logout"><i class="fa fa-power-off mr-2"></i> Logout</a></li>
                        </ul>
                    </li>
                </ul>
        </div>
    </ng-template>
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
  userName : string;

  constructor(private userSvc : UserService) { }

  ngOnInit() {
    this.userName = localStorage.getItem('name');
  }

}
