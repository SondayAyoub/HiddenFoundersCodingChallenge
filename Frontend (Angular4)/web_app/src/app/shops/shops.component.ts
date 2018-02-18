import { Component, OnInit } from '@angular/core';
import { ShopsService } from './shops.service';

import { Http } from '@angular/http';
import "rxjs/add/operator/map";
import {UserService} from "../user.service";
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-shops',
  templateUrl: './shops.component.html',
  styleUrls: ['./shops.component.css']
})
export class ShopsComponent implements OnInit {

  pageShops:any;
  currentUser:string="";
  constructor(public http:Http, public shopsService:ShopsService, private user: UserService, public _flashMessagesService:FlashMessagesService) { }

  ngOnInit() {
  	this.shopsService.getShops().subscribe(data=>{
  		this.pageShops = data;
  	}, err=>{
  		console.log(err);
  	})
  }

  likeShop(shop){
    this.shopsService.favShop(shop).subscribe(data=>{
      console.log(data.toString());
    }, err=>{
      console.log(err);
    });
    this._flashMessagesService.show('Shop '+ shop.name +' added to preferred shops', {cssClass: 'alert-success', timeout: 6000});
  }

  dislikeShop(shop){
    this._flashMessagesService.show('Shop '+ shop.name +' removed from the list for 2 hours', {cssClass: 'alert-danger', timeout: 6000});
  }

}
