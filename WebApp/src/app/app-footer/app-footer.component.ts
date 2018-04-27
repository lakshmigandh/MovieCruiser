import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  template: `
  
  <!--Footer-->
<footer class="app-footer-bg page-footer font-small blue-grey lighten-5 pt-0">

    <div>
        <div class="container">
            <!--Grid row-->
            <div class="row py-4 d-flex align-items-center">

            </div>
            <!--Grid row-->
        </div>
    </div>

    <!--Footer Links-->
    <div class="container mt-5 mb-4 text-center text-md-left">
        <div class="row mt-3">

            <!--/.First column-->
            <div class="col-md-12 col-lg-12 col-xl-12 text-center dark-grey-text">
                <h6 class="text-uppercase font-weight-bold">
                    <strong>Contact</strong>
                </h6>
                <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p>
                    <i class="fa fa-home mr-3"></i> Chennai, TN, IN</p>
                <p>
                    <i class="fa fa-envelope mr-3"></i> LakshmiGandh.M@cognizant.com</p>
                <p>
                    <i class="fa fa-phone mr-3"></i> + 91 95 00 831 005</p>
            </div>

        </div>
    </div>
    <!--/.Footer Links-->

    <!-- Copyright-->
    <div class="footer-copyright py-3 text-center">
        Copyright © 2018 All Rights Reserved
    </div>
    <!--/.Copyright -->

</footer>

  `,
  styles: []
})
export class AppFooterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
