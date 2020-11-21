
import { Component, Inject, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { AlertaService } from '../services/alerta.service';
import { UsuarioService } from '../services/usuario.service';
import { DatosUsuario } from '../usuarios-admin/usuarios-admin.component';


@Component({
  selector: 'app-perfilusuario',
  templateUrl: './perfilusuario.component.html',
  styleUrls: ['./perfilusuario.component.css']
})
export class PerfilusuarioComponent implements OnInit {
  public user: any;
  inputnombre: any;
  inputapellido: any;
  inputpass: any;
  inputtelfno: any;
  inputcorreo: any;
  inputmodificar: any;
  inputguardar: any;
  inputcancelar: any;
  loading = false;
  submitted = false;
  Modificar = "Modificar";
  local_data: any;
 

  constructor(public dialogRef: MatDialogRef<PerfilusuarioComponent>,
    private auth: AuthService,
    public alertaService: AlertaService,
    private router: Router, 
    private userservice: UsuarioService,
    @Inject(MAT_DIALOG_DATA) public data: DatosUsuario,
    
    
    ) {
      this.local_data = { ...data };
      console.log("Inicio "+this.local_data.id)
    /*if (typeof this.local_data.id !== 'undefined') {
      this.user=this.local_data;
    }*/
     }
     ngOnInit(): void {
       this.user = {
        dni: ['', Validators.required],
        contrasena: ['', [Validators.required]],
        nombre: ['', [Validators.required]],
        apellidos: ['', Validators.required],
        correo: ['', Validators.required],
        telefono: ['', Validators.required],
       };
     }

    closeDialog(){
      this.dialogRef.close({event: 'Cancel'});
      this.router.navigate(['/carga']);
    }

    guardar() {
      this.submitted = true;
      this.alertaService.clear();

      if ((this.user.dni.length != 9)) {
        this.alertaService.error("Formato de DNI incorrecto. El DNI debe de tener 8 números y una letra", false);
        return;
    }
      if ((this.user.contrasena.length < 5)) {
          this.alertaService.error("Formato de contraseña incorrecta. La contraseña debe contener al menos 6 carácteres, mayúsuculas y minúsculas, números y algún símbolo.", false);
          return;
      }

      if ((this.user.telefono.length != 9) && (!isNaN(this.user.telefono))) {
        this.alertaService.error("Formato de número de teléfono incorrecto. El teléfono debe tener al menos 9 números", false);
        return;
    }

    if (isNaN(this.user.telefono)) {
        this.alertaService.error("Formato de número de teléfono incorrecto. El teléfono debe ser un número", false);
        return;
    }

    if (!(
        (this.user.correo.includes('@')) &&
        (this.user.correo.includes('.es') || this.user.correo.includes('.com')))
    ) {
        this.alertaService.error("Formato incorrecto del correo electrónico. ", false);
        return;
    }
    this.loading = true;
    this.userservice.update(this.user, this.auth.currentUserValue[0].dni)
        .pipe(first())
        .subscribe(
            data => {
              this.alertaService.success('Modificacion realizada', true);
              console.log(this.user)
            });
  }

    modificar(){

      if(this.Modificar=="Guardar"){
        this.Modificar="Modificar";
        this.guardar();
      }else{
    this.dialogRef.disableClose = true;  
    this.inputnombre = document.getElementById("nombre");
    this.inputnombre.disabled = false;
    this.inputapellido = document.getElementById("apellidos");
    this.inputapellido.disabled = false;
    this.inputpass = document.getElementById("contrasena");
    this.inputpass.disabled = false;
    this.inputtelfno = document.getElementById("telefono");
    this.inputtelfno.disabled = false;
    this.inputcorreo = document.getElementById("correo");
    this.inputcorreo.disabled = false;
    this.inputcancelar = document.getElementById("cancelar");
    this.inputcancelar.disabled = false;
    this.Modificar= "Guardar";
  }
    }

    


    cancelar(){
      this.closeDialog();
    }
}
