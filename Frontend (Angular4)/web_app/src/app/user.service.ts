import {EventEmitter, Injectable} from '@angular/core';
import {Observable} from "rxjs";
import { Http } from "@angular/http";

@Injectable()
export class UserService {

  onMainEvent:EventEmitter<any> = new EventEmitter();
  private isUserLoggedIn:boolean;
  userInfo = {
    email: "",
    password: ""
  }

  constructor(public http:Http) {
  }

  login(email:string, password:string){

    this.userInfo= {
      email: email,
      password: password
    };

    return this.http.post('http://localhost:8080/login', this.userInfo)
          .map(res =>{
            localStorage.setItem('currentUser', JSON.stringify(res));
            localStorage.setItem('loggedIn', 'true');
            return res.json();
          });
  }

  register(email:string, password:string){

    this.userInfo= {
      email: email,
      password: password
    };

    return this.http.post('http://localhost:8080/register', this.userInfo)
      .map(res =>{
        localStorage.setItem('currentUser', JSON.stringify(res));
        localStorage.setItem('loggedIn', 'true');
        return res.json();
      });
  }

  logout(){
    localStorage.clear();
    localStorage.setItem('loggedIn', 'false');
  }

  setUserLoggedIn() {
    this.isUserLoggedIn = true;
  }

  getUserLoggedIn() {
    if(localStorage.getItem('currentUser')){
      this.isUserLoggedIn = true;
    }
    else{
      this.isUserLoggedIn = false;
    }
    return this.isUserLoggedIn;
  }
}
