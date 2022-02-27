import { Component, OnInit, Input, Renderer2, ElementRef } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';
import { ViewChild } from '@angular/core';
import { ModalConfig } from '../modal/modal.config';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @ViewChild('modal') private modalComponent!: ModalComponent

  public modalConfig: ModalConfig = {
    modalTitle: "Title",
    onDismiss: () => {
      return true
    },
    dismissButtonLabel: "Dismiss",
    onClose: () => {
      return true
    },
    closeButtonLabel: "Close"
  }

  @Input() card: Card = new Card();

  constructor(private cardService: CardService) {
  }

  ngOnInit(): void {
  }

  onSelectCard() {
    this.cardService.setSelectedId(this.card.id);
    this.openModal();
  }

  async openModal() {
    await this.modalComponent.open();
  }




}
