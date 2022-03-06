import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-card-resources',
  templateUrl: './card-resources.component.html',
  styleUrls: ['./card-resources.component.css']
})
export class CardResourcesComponent implements OnInit {

  card: Card = new Card();

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    })
  }

}
