import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';
@Component({
  selector: 'app-card-details-buttons',
  templateUrl: './card-details-buttons.component.html',
  styleUrls: ['./card-details-buttons.component.css']
})
export class CardDetailsButtonsComponent implements OnInit {

  card: Card = new Card();
  constructor(private cardService: CardService) {

  }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    })
  }

  updateDueDate(date: string) {
    const updatedCard = this.card;
    updatedCard.deadline = date;
    this.cardService.updateCard(updatedCard).subscribe((card) => {
      this.cardService.readCard(card.id);
    });
  }

}
