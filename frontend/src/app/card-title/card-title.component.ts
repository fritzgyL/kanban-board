import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-title',
  templateUrl: './card-title.component.html',
  styleUrls: ['./card-title.component.css']
})
export class CardTitleComponent implements OnInit {
  @Input() title: string = '';
  constructor() { }

  ngOnInit(): void {
  }

}
