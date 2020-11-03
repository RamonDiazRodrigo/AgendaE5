import { Component, Input, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ReunionComponent} from '../reunion/reunion.component';

@Component({
  selector: 'app-reuniones-pre',
  templateUrl: './reuniones-pre.component.html',
  styleUrls: ['./reuniones-pre.component.css']
})
export class ReunionesPreComponent implements OnInit {

  @Input() public reunion;

  constructor(
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    console.log(this.reunion);
  }

  verReunion() {
		const dialogRef = this.dialog.open(ReunionComponent, {
			width: '400px',
			data: this.reunion
		});
  }
}
