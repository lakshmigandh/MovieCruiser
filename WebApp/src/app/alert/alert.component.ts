import { Component, OnInit, Input } from '@angular/core';
import { Config } from '../utils/config';

@Component({
  selector: 'alert-message',
  template: `
  <div class="page-alerts">
    <div [ngClass]="alertMessage ? 'd-lg-block' : 'd-lg-none'" [class]="getAlertClass().alertClass" class="alert page-alert">
        <i class="fa mr-2" [ngClass]="getAlertClass().alertIcon"></i>
        <button type="button" class="close"><span (click)="alertMessage = undefined" aria-hidden="true">×</span><span class="sr-only">Close</span></button>
        {{alertMessage}}
    </div>
</div>
  `,
  styles: []
})
export class AlertComponent implements OnInit {

  @Input('alertLevel')  public alertLevel : Config.AlertType;  
  @Input('alertMessage')  public alertMessage : string;

  constructor() { }

  ngOnInit() {}

  getAlertClass(){
      let alertClass = undefined;
      let alertIcon = undefined;
      switch(this.alertLevel) {
        case Config.AlertType.SUCCESS:
            alertClass = "alert-success";
            alertIcon = "fa-check";
            break;
        case Config.AlertType.ERROR:
            alertClass = "alert-danger";
            alertIcon = "fa-exclamation-triangle";
            break;
        case Config.AlertType.INFO:
            alertClass = "alert-info";
            alertIcon = "fa-info";
            break;
      }
      return {"alertClass":alertClass,"alertIcon":alertIcon};
  }

}
