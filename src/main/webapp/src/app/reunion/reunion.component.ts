import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { ReunionesService } from '../reuniones.service';

export interface ReunionData {
  titulo: string;
  descripcion: string;
  organizador: string;
  fecha: string;
  horaini: string;
  horafin: string;
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
  constructor(
    public dialogRef: MatDialogRef<ReunionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReunionData,
    private auth: AuthService,
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
     eliminar(){
     
      
      this.service.cancelarReunion(this.auth.currentUserValue[0].dni, this.local_data.id)
      .subscribe(response => {
        this.reuniones = response;
        this.closeDialog()
      });
      
     }
}
