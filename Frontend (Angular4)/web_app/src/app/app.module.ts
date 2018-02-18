import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'
import { NgxPaginationModule } from 'ngx-pagination';

import { FormsModule} from '@angular/forms'
import { ShopsService } from './shops/shops.service';
import { AppComponent } from './app.component';
import { ShopsComponent } from './shops/shops.component';
import { HeaderComponent } from './header/header.component';
import { LoginFormComponent } from './login-form/login-form.component';
import {UserService} from "./user.service";
import {AuthguardGuard} from "./authguard.guard";
import {HttpModule} from "@angular/http";
import {FlashMessagesModule} from "angular2-flash-messages";
import { RegisterComponent } from './register/register.component';
import { PreferredShopsComponent } from './preferred-shops/preferred-shops.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'myShops', component: PreferredShopsComponent, canActivate:[AuthguardGuard] },
  { path: 'shops', component: ShopsComponent, canActivate:[AuthguardGuard] },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    ShopsComponent,
    HeaderComponent,
    LoginFormComponent,
    RegisterComponent,
    PreferredShopsComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpModule,
    FormsModule,
    NgxPaginationModule,
    FlashMessagesModule.forRoot()
  ],
  providers: [ShopsService, UserService, AuthguardGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
