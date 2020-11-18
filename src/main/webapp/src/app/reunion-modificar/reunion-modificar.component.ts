import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
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
export class ReunionModificarComponent implements OnInit{
  reunionForm: FormGroup;
  reuniones: any;
  local_data: any;

  constructor(
    public dialogRef: MatDialogRef<ReunionModificarComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReunionData,
    private reunionService: ReunionesService,
    private auth: AuthService,
    private formBuilder: FormBuilder
    ) {
      console.log(data)
      this.local_data = {...data};
     }

     ngOnInit(){
       this.reunionForm= this.formBuilder.group({
        titulo: [''],
        descripcion: [''],
        organizador: [''],
        fecha: [''],
        horaini: [''],
        horafin: [''],
        listaAsistentes: ['']
       });
     }

     closeDialog(){
       this.dialogRef.close({event: 'Cancel'})
     }
     modificar(){
       console.log(this.reunionForm.value)
      this.reunionService.modificarReunion(this.reunionForm.value, this.auth.currentUserValue[0].dni)
      .subscribe(
        data => {
          this.closeDialog()
        });
          
        
     }

}

