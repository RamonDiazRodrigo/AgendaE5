import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-reuniones-pre',
  templateUrl: './reuniones-pre.component.html',
  styleUrls: ['./reuniones-pre.component.css']
})
export class ReunionesPreComponent implements OnInit {

  @Input() public reunion;

  constructor() { }

  ngOnInit(): void {
    console.log(this.reunion);
  }

}
