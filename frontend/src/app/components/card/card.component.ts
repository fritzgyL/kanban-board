import { Component, OnInit, Input, Renderer2, ElementRef } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @ViewChild('btn') btn!: ElementRef;
  @ViewChild('updateTextareaContainer') updateTextareaContainer!: ElementRef;
  @ViewChild('updateTextarea') updateTextarea!: ElementRef;

  @Input() card: Card = new Card();
  isUpdating: boolean = false;
  newCardTitle: string = '';
  constructor(private cardService: CardService, private renderer: Renderer2) {
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

  onSelectCard() {
    this.cardService.setSelectedId(this.card.id);
  }

  onUpdateCardTitle() {
    this.isUpdating = true;
  }

  private onSaveNewCardTitle() {
    if (this.newCardTitle != '' && this.newCardTitle != this.card.title) {
      let newCard = this.card;
      newCard.title = this.newCardTitle;
      this.cardService.updateCard(newCard).subscribe((card) => {
        this.isUpdating = false;
        this.newCardTitle = this.card.title;
      })
    }
    else {
      this.isUpdating = false;
    }
  }

}
