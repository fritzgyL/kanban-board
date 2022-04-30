import { Component, OnInit, Renderer2 } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';
import { ViewChild } from '@angular/core';
import { ElementRef } from '@angular/core';
import { BoardService } from 'src/app/services/board/board-service.service';

@Component({
  selector: 'app-view-card',
  templateUrl: './view-card.component.html',
  styleUrls: ['./view-card.component.css']
})
export class ViewCardComponent implements OnInit {

  @ViewChild('btn') btn!: ElementRef;
  @ViewChild('updateTextareaContainer') updateTextareaContainer!: ElementRef;
  @ViewChild('updateTextarea') updateTextarea!: ElementRef;

  card: Card = new Card();
  isUpdating: boolean = false;
  newCardTitle: string = '';

  constructor(private cardService: CardService, private boardService: BoardService, private renderer: Renderer2) {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    });
    this.renderer.listen('window', 'click', (e: Event) => {
      const eventTarget = e.target as HTMLTextAreaElement;
      if (eventTarget.id != 'test') {
        if (this.updateTextarea != undefined) {
          if (this.updateTextarea.nativeElement != e.target) {
            this.onSaveNewCardTitle();
          }
        }
      }
    });
  }

  ngOnInit(): void {
    this.newCardTitle = this.card.title;
  }

  onUpdateCardTitle() {
    this.isUpdating = true;
  }

  private onSaveNewCardTitle() {
    if (this.newCardTitle != '' && this.newCardTitle != this.card.title) {
      let newCard = this.card;
      newCard.title = this.newCardTitle;
      this.cardService.updateCard(newCard.id!, newCard).subscribe((card) => {
        this.cardService.readCard(card.id!);
        this.boardService.loadBoard();
        this.isUpdating = false;
        this.newCardTitle = this.card.title;
      })
    }
    else {
      this.isUpdating = false;
    }
  }
}
