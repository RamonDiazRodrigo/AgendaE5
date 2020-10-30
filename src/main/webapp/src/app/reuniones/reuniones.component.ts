import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { ReunionesService } from '../reuniones.service';

@Component({
  selector: 'app-reuniones',
  templateUrl: './reuniones.component.html',
  styleUrls: ['./reuniones.component.css']
})
export class ReunionesComponent implements OnInit {
  
  public reuniones;
  public reunionesNuevas;

  constructor(
    private service: ReunionesService,
    private auth: AuthService
  ) { }
  

  ngOnInit(): void {

    this.service.findReuniones(this.auth.currentUserValue.dni)
    .subscribe(response=>{
      this.reuniones = response;
    });
    this.service.findReunionesNuevas(this.auth.currentUserValue.dni)
    .subscribe(response=>{
      this.reunionesNuevas = response;
    });
  }

}
