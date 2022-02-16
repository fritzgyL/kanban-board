import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';


@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

  card: Card = new Card();
  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    })
  }

  onDelete() {
    if (confirm('Delete this card?')) {
      console.log('hey');
    }
  }

}
