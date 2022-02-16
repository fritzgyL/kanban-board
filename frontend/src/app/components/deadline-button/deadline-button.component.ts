import { Component, OnInit } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-deadline-button',
  templateUrl: './deadline-button.component.html',
  styleUrls: ['./deadline-button.component.css'],
})
export class DeadlineButtonComponent implements OnInit {

  card: Card = new Card();
  selectedDate: any;
  popoverIsHidden: boolean = false;
  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.card = card;
      if (this.card.deadline != null) {
        this.setSelectedDate();
      }
    })
  }

  private setSelectedDate() {
    this.selectedDate = this.formatDate(this.card.deadline);
  }

  private formatDate(date: string): NgbDate {
    var parts = date.split('-');
    return new NgbDate(parseInt(parts[0]), parseInt(parts[1]), parseInt(parts[2]));
  }

  onDateSelect(date: NgbDate) {
    this.selectedDate = this.convertDateToString(date);
  }

  private convertDateToString(date: NgbDate): string {
    return date.year + '-' + date.month + '-' + date.day;
  }

  recordHidden() {
    this.setSelectedDate();
  }
}
