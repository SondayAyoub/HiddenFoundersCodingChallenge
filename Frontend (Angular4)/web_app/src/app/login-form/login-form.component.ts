import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../user.service";
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  email:string;
  password:string;

  rgstr:boolean=false;
   constructor(public router:Router, public user:UserService, public _flashMessagesService:FlashMessagesService) { }

  ngOnInit() {
  }

  register(){
     this.rgstr=!this.rgstr;
  }

  mySubmit({value, valid}:{value,valid:boolean}){
     if (!valid){
       this._flashMessagesService.show('Invalid data', {cssClass: 'alert-danger', timeout: 5000});
     }
     else{
       if(!this.rgstr){
         console.log("Login");
         this.user.login(this.email, this.password).subscribe(data=>{
           this._flashMessagesService.show('Succssefully logged in', {cssClass: 'alert-success', timeout: 5000});
           this.user.onMainEvent.emit(true);
           this.router.navigate(['/shops'])
         }, err=>{
           this._flashMessagesService.show(err, {cssClass: 'alert-success', timeout: 5000});
         });
       }
       else{
         console.log("Register");
         this.user.register(this.email, this.password)
           .subscribe(data=>{
           this._flashMessagesService.show('Succssefully registered', {cssClass: 'alert-success', timeout: 5000});
           this.user.onMainEvent.emit(true);
           this.router.navigate(['/shops'])
         }, err=>{
           this._flashMessagesService.show(err, {cssClass: 'alert-success', timeout: 5000});
         });
       }
     }
  }
}
