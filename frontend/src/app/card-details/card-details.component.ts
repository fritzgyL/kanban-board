import { Component, Input, OnInit } from '@angular/core';
import { Card } from '../class/card/card';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

  @Input() card = new Card();

  constructor() { }

  ngOnInit(): void {
  }

}
