import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public nombre: String;
  public admin: boolean;
  constructor(
    private UsuarioService: AuthService) { }

  ngOnInit(): void {
    this.nombre = this.UsuarioService.currentUserValue.nombre + " " + this.UsuarioService.currentUserValue.apellidos;
    if (this.UsuarioService.currentUserValue.tipo != "Asistente") {
      this.admin = true;
    }
    else {
      this.admin = false;
    }
  }

  logout() {
    this.UsuarioService.logout();
  }

}
