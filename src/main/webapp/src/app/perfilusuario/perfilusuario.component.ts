import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { UsuarioService } from '../services/usuario.service';


@Component({
  selector: 'app-perfilusuario',
  templateUrl: './perfilusuario.component.html',
  styleUrls: ['./perfilusuario.component.css']
})
export class PerfilusuarioComponent implements OnInit {
  public user: any;

  constructor(public dialogRef: MatDialogRef<PerfilusuarioComponent>,
    private auth: AuthService,
    private userservice: UsuarioService) {
      dialogRef.disableClose = true;
     }
     ngOnInit(): void {
       this.user = {
         nombre: " ",
         apellidos: " ",
         dni: " ",
         contrasena: " ",
         telefono: " ",
         correo: " "

       };
      this.userservice.findUser(this.auth.currentUserValue[0].dni)
      .subscribe(response => {
        this.user = response;
      });
     }

    closeDialog(){
      this.dialogRef.close({event: 'Cancel'})
    }
}
