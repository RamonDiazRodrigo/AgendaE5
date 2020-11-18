import { Inject } from '@angular/core';
import { Component } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ReunionesService } from '../reuniones.service';

export interface ReunionData {
  titulo: "";
  descripcion: "";
  organizador: "";
  fecha: "";
  horaini: "";
  horafin: "";
  listaAsistentes: Array<String>;
}

@Component({
  selector: 'app-reunion-modificar',
  templateUrl: './reunion-modificar.component.html',
  styleUrls: ['./reunion-modificar.component.css']
})
export class ReunionModificarComponent{

  reuniones: any;
  local_data: any;

  constructor(
    public dialogRef: MatDialogRef<ReunionModificarComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReunionData,
    private service: ReunionesService,
    private router: Router) {
      console.log(data)
      this.local_data = {...data};
     }

     closeDialog(){
       this.dialogRef.close({event: 'Cancel'})
     }
     modificar(){
      
     }

}

