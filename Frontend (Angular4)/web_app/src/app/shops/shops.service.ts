import { Injectable } from "@angular/core";
import { Http } from '@angular/http';

@Injectable()
export class ShopsService{

  currentUser;

	constructor(public http:Http){ }

	getShops(){
		return this.http.get('http://localhost:8080/nearbyShops').map(resp=>resp.json());
  }

  favShop(shop){
    this.currentUser = JSON.parse(JSON.parse(localStorage.getItem('currentUser'))._body) ;

    return this.http.post('http://localhost:8080/user/assignShop/' + this.currentUser.id, shop)
      .map(res =>{
        localStorage.removeItem('currentUser');
        localStorage.setItem('currentUser', JSON.stringify(res));
        return res.json();
      });
  }
  remove(shop){
    this.currentUser = JSON.parse(JSON.parse(localStorage.getItem('currentUser'))._body) ;

    return this.http.post('http://localhost:8080/user/removeShop/' + this.currentUser.id, shop)
      .map(res =>{
        localStorage.removeItem('currentUser');
        localStorage.setItem('currentUser', JSON.stringify(res));
        return res.json();
      });
  }
}