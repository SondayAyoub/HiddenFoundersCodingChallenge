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
  disliked:boolean=false;

  constructor(public http:Http, public shopsService:ShopsService, private user: UserService, public _flashMessagesService:FlashMessagesService) { }

  ngOnInit() {
  	this.shopsService.getShops().subscribe(data=>{
  		this.pageShops = data;
      this.pageShops.forEach(element => {
        element.disliked = this.disliked;
      });
  	}, err=>{
  		console.log(err);
  	})
  }

  likeShop(shop){
    this.shopsService.favShop(shop).subscribe(data=>{
      console.log("Shop added");
    }, err=>{
      console.log(err);
    });
    this._flashMessagesService.show('Shop '+ shop.name +' added to preferred shops', {cssClass: 'alert-success', timeout: 6000});
  }

  dislikeShop(shop){
    shop.disliked = true;
    setTimeout(()=>{ shop.disliked = false; }, 3600000);
    this._flashMessagesService.show('Shop '+ shop.name +' removed from the list for 2 hours', {cssClass: 'alert-danger', timeout: 6000});
  }
}