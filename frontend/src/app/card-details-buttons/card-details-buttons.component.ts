import { Component, Input, OnInit } from '@angular/core';
import { Card } from '../class/card/card';

@Component({
  selector: 'app-card-details-buttons',
  templateUrl: './card-details-buttons.component.html',
  styleUrls: ['./card-details-buttons.component.css']
})
export class CardDetailsButtonsComponent implements OnInit {

  @Input() card: Card = new Card();
  constructor() { }

  ngOnInit(): void {
  }

}
