import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { BoardService } from 'src/app/services/board/board-service.service';
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
  constructor(private cardService: CardService, private boardService: BoardService) { }

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
    if (this.card.description !== '') {
      this.textareaText = this.card.description;
    }
  }

  onSaveNewDescription() {
    if (this.textareaText != this.MY_CONSTANT) {
      let newCard = this.card;
      newCard.description = this.textareaText;
      this.cardService.updateCard(newCard.id!, newCard).subscribe((card) => {
        this.cardService.readCard(card.id!);
        this.boardService.loadBoard();
      });
      this.isUpdating = false;
      this.setTextareaText();
    }
  }



}
