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

  gridColumns = 3;

  ngOnInit(): void {


    if (this.auth.currentUserValue[0].tipo != "NJlGkLOGjTQ=")  {
      this.service.findReuniones(this.auth.currentUserValue[0].dni)
        .subscribe(response => {
          this.reuniones = response;
        });
    } else {
      this.service.findAll()
        .subscribe(response => {
          this.reuniones = response;
        });
    }
  }

}
