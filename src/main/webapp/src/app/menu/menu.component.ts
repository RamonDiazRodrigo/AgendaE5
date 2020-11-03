import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
public nombre :String;
  constructor(
    private UsuarioService: AuthService) { }

  ngOnInit(): void {
    this.nombre= this.UsuarioService.currentUserValue.nombre + " " + this.UsuarioService.currentUserValue.apellidos;
  }

  logout() {
    this.UsuarioService.logout();
  }

}
