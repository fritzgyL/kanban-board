import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { BoardService } from 'src/app/services/board/board-service.service';
import { CardService } from 'src/app/services/card/card-service.service';
import { closeModal } from 'src/app/utils/modal-closer';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

  card: Card = new Card();
  constructor(private cardService: CardService, private boardService: BoardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    })
  }

  onDelete() {
    if (confirm('Delete this card?')) {
      this.cardService.deleteCard(this.card).subscribe(response => {
        this.closeModal();
        this.boardService.loadBoard();
      })
    }
  }

  closeModal() {
    closeModal(`viewCardModal${this.card.id}`);
  }

}
