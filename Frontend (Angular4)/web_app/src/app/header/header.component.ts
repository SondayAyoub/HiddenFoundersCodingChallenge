import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import { FlashMessagesService } from 'angular2-flash-messages';
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn:boolean;
  EnableRegister:boolean;



  constructor(public router:Router, public user:UserService, public _flashMessagesService:FlashMessagesService) {
    this.user.onMainEvent.subscribe((onMain)=>{
      this.isLoggedIn = onMain;
    });
  }

  ngOnInit() {
    //this.isLoggedIn = localStorage.getItem('loggedIn');
    if(localStorage.getItem('currentUser')){
      this.isLoggedIn = true;
    }
    else{
      this.isLoggedIn = false;
    }

  }

  logout(){
    this.user.logout();
    this._flashMessagesService.show('You are logged out successfully', {cssClass: 'alert-success', timeout: 5000});
    this.router.navigate(['/login']);
  }

}
