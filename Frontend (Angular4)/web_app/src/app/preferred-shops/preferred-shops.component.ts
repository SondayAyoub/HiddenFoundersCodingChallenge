import { Component, OnInit } from '@angular/core';
import { ShopsService } from '../shops/shops.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import {UserService} from "../user.service";

@Component({
  selector: 'app-preferred-shops',
  templateUrl: './preferred-shops.component.html',
  styleUrls: ['./preferred-shops.component.css']
})
export class PreferredShopsComponent implements OnInit {

  pageShops:any;
  currentUser:string="";

  constructor(public shopsService:ShopsService, public _flashMessagesService:FlashMessagesService) { }

  ngOnInit() {
      this.currentUser = localStorage.getItem('currentUser');
      this.pageShops = JSON.parse(JSON.parse(this.currentUser)._body).favShops;
      console.log(JSON.parse(JSON.parse(this.currentUser)._body).favShops);
  }

  removeShop(shop){
    this.shopsService.remove(shop).subscribe(data=>{
      console.log(data);
    }, err=>{
      console.log(err);
    });
    this._flashMessagesService.show('Shop '+ shop.name +' removed from preferred shops', {cssClass: 'alert-danger', timeout: 6000});
  }

}
