import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {UserService} from "./user.service";
import { FlashMessagesService } from 'angular2-flash-messages';

@Injectable()
export class AuthguardGuard implements CanActivate {

  constructor(private user:UserService, private router: Router, private flashMessagesService:FlashMessagesService){}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(localStorage.getItem('currentUser')){
      return true;
    }else{
      this.router.navigate(['/login']);
      this.flashMessagesService.show('You are not authenticated', {cssClass: 'alert-danger', timeout: 5000});
      console.log('You are not authenticated');
      return this.user.getUserLoggedIn();
    }
  }
}