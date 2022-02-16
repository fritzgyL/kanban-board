import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-card-description',
  templateUrl: './card-description.component.html',
  styleUrls: ['./card-description.component.css']
})
export class CardDescriptionComponent implements OnInit {

  isUpdating: boolean = false;
  card: Card = new Card();
  textareaText: string = '';
  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
      this.setTextareaText();
    })
  }

  onUpdateDescription() {
    this.isUpdating = true;
  }

  private setTextareaText() {
    if (this.card.description != null) {
      this.textareaText = this.card.description;
    }
    else {
      this.textareaText = 'Add a more detailed description...';
    }
  }

}
