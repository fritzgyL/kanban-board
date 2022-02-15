import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() card: Card = new Card();
  constructor(private cardService: CardService) { }

  ngOnInit(): void {
  }

  onSelectCard() {
    this.cardService.setSelectedId(this.card.id);
  }

}
