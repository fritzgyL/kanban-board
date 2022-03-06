import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { ElementRef } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';
import { Renderer2 } from '@angular/core';
import { SectionService } from 'src/app/services/section/section.service';
@Component({
  selector: 'app-card-modal-title',
  templateUrl: './card-modal-title.component.html',
  styleUrls: ['./card-modal-title.component.css']
})
export class CardModalTitleComponent implements OnInit {

  @ViewChild('btn') btn!: ElementRef;
  @ViewChild('updateTextareaContainer') updateTextareaContainer!: ElementRef;
  @ViewChild('updateTextarea') updateTextarea!: ElementRef;

  card: Card = new Card();
  isUpdating: boolean = false;
  newCardTitle: string = '';

  constructor(private cardService: CardService, private sectionService: SectionService, private renderer: Renderer2) {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
      this.newCardTitle = this.card.title;
      this.isUpdating = false;
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
  }

  onUpdateCardTitle() {
    this.isUpdating = true;
  }

  private onSaveNewCardTitle() {
    if (this.newCardTitle != '' && this.newCardTitle != this.card.title) {
      let newCard = this.card;
      newCard.title = this.newCardTitle;
      this.cardService.updateCard(newCard).subscribe((card) => {
        this.cardService.readCard(card.id);
        this.isUpdating = false;
        this.newCardTitle = this.card.title;
      })
    }
    else {
      this.isUpdating = false;
    }
  }
}
