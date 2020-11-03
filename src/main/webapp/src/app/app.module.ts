import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ReunionesComponent } from './reuniones/reuniones.component';
import { ReunionesPreComponent } from './reuniones-pre/reuniones-pre.component';
import { MatDialogModule } from '@angular/material/dialog'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { RegistroComponent } from './registro/registro.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReunionComponent } from './reunion/reunion.component';
import { FlexLayoutModule } from "@angular/flex-layout";
import { AlertaComponent } from './alerta/alerta.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ReunionesComponent,
    ReunionesPreComponent,
    MenuComponent,
    RegistroComponent,
    ReunionComponent,
    AlertaComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    FlexLayoutModule
  ],
  entryComponents:[
    ReunionComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
