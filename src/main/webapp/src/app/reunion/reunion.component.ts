import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { ReunionesService } from '../reuniones.service';

export interface ReunionData {
  titulo: string;
  descripcion: string;
  organizador: string;
  fecha: string;
  horaIni: string;
  horaFin: string;
  listaAsistentes: Array<String>;
}

@Component({
  selector: 'app-reunion',
  templateUrl: './reunion.component.html',
  styleUrls: ['./reunion.component.css']
})
export class ReunionComponent {
  reuniones: any;
  local_data: any;
  modificarB: boolean;
  form: any;
  action: string;
  modificars: string;
  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<ReunionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReunionData,
    private auth: AuthService,
    private service: ReunionesService,
    private router: Router) {
    console.log(data)
    this.local_data = { ...data };
    this.modificarB = false;
    this.action = "Eliminar";
    this.modificars = "Modificar"
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' })
  }

  modificar() {
    if (this.modificarB) {
      this.modificarB = false;
      this.action = "Eliminar"
      this.modificars= "Modificar"
      this.service.modificarReunion(this.local_data, this.auth.currentUserValue[0].dni)
        .subscribe(
          () => {
            this.closeDialog()
            this.router.navigate(['/reuniones']);
          });
    } else {
      this.modificarB = true;
      this.action = "Cancelar";
      this.modificars = "Guardar"
      this.form = document.getElementById("titulo");
      this.form.disabled = false;
      this.form = document.getElementById("descripcion");
      this.form.disabled = false;
      this.form = document.getElementById("organizador");
      this.form.disabled = false;
      this.form = document.getElementById("fecha");
      this.form.disabled = false;
      this.form = document.getElementById("horaIni");
      this.form.disabled = false;
      this.form = document.getElementById("horaFin");
      this.form.disabled = false;
      this.form = document.getElementById("listaAsistentes");
      this.form.disabled = false;
    }
  }


  eliminar() {
    if (this.action == "Eliminar") {
      this.service.cancelarReunion(this.auth.currentUserValue[0].dni, this.local_data.id)
        .subscribe(response => {
          this.reuniones = response;
          this.router.navigate(['/reuniones']);
          this.closeDialog()
        });
    }
    else{
      this.modificarB=false;
      this.action="Eliminar";
      this.modificars = "Modificar"
      this.form = document.getElementById("titulo");
      this.form.disabled = true;
      this.form = document.getElementById("descripcion");
      this.form.disabled = true;
      this.form = document.getElementById("fecha");
      this.form.disabled = true;
      this.form = document.getElementById("horaIni");
      this.form.disabled = true;
      this.form = document.getElementById("horaFin");
      this.form.disabled = true;
      this.form = document.getElementById("listaAsistentes");
      this.form.disabled = true;
    }
  }
}
