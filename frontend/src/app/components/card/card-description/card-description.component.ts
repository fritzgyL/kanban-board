import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-card-description',
  templateUrl: './card-description.component.html',
  styleUrls: ['./card-description.component.css']
})
export class CardDescriptionComponent implements OnInit {

  readonly MY_CONSTANT = 'Add a more detailed description...';

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

  setTextareaText() {
    if (this.card.description != '') {
      this.textareaText = this.card.description;
    }
    else {
      this.textareaText = this.MY_CONSTANT;
    }
  }

  onSaveNewDescription() {
    if (this.textareaText != '' && this.textareaText != this.MY_CONSTANT) {
      this.card.description = this.textareaText;
      this.cardService.updateCard(this.card).subscribe((card) => {
        console.log(card);
        this.cardService.readCard(card.id);
      });
      this.isUpdating = false;
      this.setTextareaText();
    }
  }

}
