import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-card-description',
  templateUrl: './card-description.component.html',
  styleUrls: ['./card-description.component.css']
})
export class CardDescriptionComponent implements OnInit {

  isWriting: boolean = false
  card: Card = new Card();

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    })
  }

  onUpdateDescription() {
    this.isWriting = true;
  }

}
