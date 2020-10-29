import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LoginComponent } from './login/login.component';


import { ReunionesComponent } from './reuniones/reuniones.component';
import { ReunionesPreComponent } from './reuniones-pre/reuniones-pre.component';

import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { RegistroComponent } from './registro/registro.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ReunionesComponent,
    ReunionesPreComponent,
    MenuComponent,
    RegistroComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
