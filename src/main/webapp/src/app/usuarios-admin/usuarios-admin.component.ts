import { Component} from '@angular/core';
import { MatDialog} from '@angular/material/dialog'; 
import { MatTableDataSource} from '@angular/material/table';
import { UsuarioService } from '../services/usuario.service';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup } from '@angular/forms';

export interface DatosUsuario{
  contrasena: string;
  nombre: string;
  apellidos: string;
  dni: string;
  telefono: string;
  correo: string;
  tipo: string;
}

@Component({
  selector: 'app-usuarios-admin',
  templateUrl: './usuarios-admin.component.html',
  styleUrls: ['./usuarios-admin.component.css']
})

export class UsuariosAdminComponent{
  columnas: string[] = ['contrasena', 'nombre', 'apellidos','dni', 'telefono', 'correo', 'tipo'];
  dataSource = new MatTableDataSource<DatosUsuario>();
  data: DatosUsuario[];
  constructor(
    private user: UsuarioService,
    private auth: AuthService,
    public dialog: MatDialog,
    private FormBuilder: FormBuilder 
  ) { }

  ngOnInit(): void {
    this.user.getUsuarios(this.auth.currentUserValue.dni)
    .subscribe((data: DatosUsuario[]) => {
      this.data = data;
      console.log(data)
      this.dataSource = new MatTableDataSource(data);
    });
  }
  modificar(): void{
    console.log("modificar")
  }
  eliminar(): void{
    console.log("eliminar")
  }

}
