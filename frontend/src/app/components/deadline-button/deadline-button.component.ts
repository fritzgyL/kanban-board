import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgbDate, NgbPopover } from '@ng-bootstrap/ng-bootstrap';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-deadline-button',
  templateUrl: './deadline-button.component.html',
  styleUrls: ['./deadline-button.component.css'],
})
export class DeadlineButtonComponent implements OnInit {
  card: Card = new Card();
  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
    });
  }

  updateDueDate(date: string | null, popover: NgbPopover) {
    const updatedCard = this.card;
    updatedCard.deadline = date;
    this.cardService.updateCard(updatedCard.id!, updatedCard).subscribe((card) => {
      this.cardService.readCard(card.id!);
      popover.close();
    });
  }
}
