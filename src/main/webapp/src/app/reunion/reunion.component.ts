import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

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
  local_data: any;
  constructor(
    public dialogRef: MatDialogRef<ReunionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReunionData) {
      console.log(data)
      this.local_data = {...data};
     }


     closeDialog(){
       this.dialogRef.close({event: 'Cancel'})
     }
}
