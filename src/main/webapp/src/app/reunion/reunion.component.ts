import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { AuthService } from '../auth.service';
import { ReunionModificarComponent } from '../reunion-modificar/reunion-modificar.component';
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
  modificarB: boolean;
  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<ReunionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReunionData,
    private auth: AuthService,
    private service: ReunionesService,
    private router: Router) {
      console.log(data)
      this.local_data = {...data};
      
      
      console.log(this.modificarB);
     }


     closeDialog(){
       this.dialogRef.close({event: 'Cancel'})
     }
     modificar(){
       this.dialogRef.updateSize("3px", "5px")
      const dialogRef1 = this.dialog.open(ReunionModificarComponent, {
        disableClose: true,
        width: '325px',
        data: this.local_data
      });
      dialogRef1.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.dialogRef.updateSize("325px", "auto")
      });
     
      
      
     }
     eliminar(){
     
      
      this.service.cancelarReunion(this.auth.currentUserValue[0].dni, this.local_data.id)
      .subscribe(response => {
        this.reuniones = response;
        this.closeDialog()
      });
      
     }
}
