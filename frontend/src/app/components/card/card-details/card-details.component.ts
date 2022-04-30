import { Component, Output, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { BoardService } from 'src/app/services/board/board-service.service';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {
  @Output() closeModal: EventEmitter<Boolean> = new EventEmitter<Boolean>()

  card: Card = new Card();
  constructor(private cardService: CardService, private boardService: BoardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    })
  }

  onDelete() {
    if (confirm('Are you sure you want to delete this card?')) {
      this.cardService.deleteCard(this.card).subscribe(response => {
        this.boardService.loadBoard();
        this.closeParentModal();
      })
    }
  }

  private closeParentModal() {
    this.closeModal.emit(true);
  }

}
