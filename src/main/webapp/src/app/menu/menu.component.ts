import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { PerfilusuarioComponent } from '../perfilusuario/perfilusuario.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public nombre: String;
  public admin: boolean;
  constructor(
    private UsuarioService: AuthService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.nombre = this.UsuarioService.currentUserValue.nombre + " " + this.UsuarioService.currentUserValue.apellidos;
    if (this.UsuarioService.currentUserValue.tipo != "Asistente") {
      this.admin = true;
    }
    else {
      this.admin = false;
    }
  }

  verPerfil(){
    const dialogRef = this.dialog.open(PerfilusuarioComponent, {
      width: '325px'
		});
    
  }

  logout() {
    this.UsuarioService.logout();
  }

}
