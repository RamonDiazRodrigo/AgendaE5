import { Component, OnInit } from '@angular/core';
import { ReunionesService } from '../reuniones.service';

@Component({
  selector: 'app-reuniones',
  templateUrl: './reuniones.component.html',
  styleUrls: ['./reuniones.component.css']
})
export class ReunionesComponent implements OnInit {
  
  public reuniones;

  constructor(
    private service: ReunionesService
  ) { }
  

  ngOnInit(): void {
    this.service.findAll()
    .subscribe(response=>{
      this.reuniones = response;
    });
  }

}
