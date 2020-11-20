import { Component, Inject, OnInit } from '@angular/core';
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
export class ReunionComponent implements OnInit{
  reuniones: any;
  local_data: any;
  modificarB: boolean;
  form: any;
  btn1: string;
  btn2: string;
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
    this.btn2 = this.local_data.action
    this.btn1 = "Cancelar"
  }
  ngOnInit(): void {
    console.log("Inicio "+this.local_data.id)
    if (typeof this.local_data.id !== 'undefined') {
      this.btn1 = "Eliminar"
    } else {
      this.btn1 = "Cancelar"
      this.local_data.titulo = "";
      this.local_data.descripcion = "";
      this.local_data.fecha = "";
      this.local_data.horaIni = "";
      this.local_data.horaFin = "";
      this.local_data.listaAsistentes = "";
      this.dialogRef.disableClose = true;
      this.habilitar(false);
    }
    
  }

  closeDialog() {
    this.dialogRef.close({ event: 'Cancel' })
  }

  modificar() {
    if (this.btn2 == "Crear") {
      this.service.crearReunion(this.local_data, this.auth.currentUserValue[0].dni)
        .subscribe(
          () => {
            this.closeDialog()
            this.router.navigate(['/reuniones']);
          });
    } else {
      if (this.modificarB) {
        this.modificarB = false;
        this.btn1 = "Eliminar"
        this.btn2 = "Modificar"
        this.service.modificarReunion(this.local_data, this.auth.currentUserValue[0].dni)
          .subscribe(
            () => {
              this.closeDialog()
              this.router.navigate(['/reuniones']);
            });
      } else {
        this.dialogRef.disableClose = true;
        this.modificarB = true;
        this.btn1 = "Cancelar";
        this.btn2 = "Guardar"
        this.habilitar(false);
      }
    }
  }


  eliminar() {
    if (this.btn1 == "Eliminar") {
      this.service.cancelarReunion(this.auth.currentUserValue[0].dni, this.local_data.id)
        .subscribe(response => {
          this.reuniones = response;
          this.router.navigate(['/reuniones']);
          this.closeDialog()
        });
    }
    else if (this.btn2 == "Crear") {
      this.dialogRef.disableClose = false;
      this.closeDialog();
    } else {
      this.dialogRef.disableClose = false;
      this.modificarB = false;
      this.btn1 = "Eliminar";
      this.btn2 = "Modificar"
      this.habilitar(true);
    }
  }
  private habilitar(bolean): void {
    this.form = document.getElementById("titulo");
    this.form.disabled = bolean;
    this.form = document.getElementById("descripcion");
    this.form.disabled = bolean;
    this.form = document.getElementById("fecha");
    this.form.disabled = bolean;
    this.form = document.getElementById("horaIni");
    this.form.disabled = bolean;
    this.form = document.getElementById("horaFin");
    this.form.disabled = bolean;
    this.form = document.getElementById("listaAsistentes");
    this.form.disabled = bolean;
  }
}
