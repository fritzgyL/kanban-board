import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { CardService } from 'src/app/services/card/card-service.service';

@Component({
  selector: 'app-card-tag',
  templateUrl: './card-tag.component.html',
  styleUrls: ['./card-tag.component.css']
})
export class CardTagComponent implements OnInit {

  pickedColor: string = '#0d6efd';
  labelTitle: string = '';
  selectedCard: Card | undefined = undefined;

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.selectedCard = card;
    })
  }

  onAddTag() {
    if (this.pickedColor !== '' && this.labelTitle !== '') {
      this.cardService.addTag(this.selectedCard!.id, { title: this.labelTitle, color: this.pickedColor }).subscribe(() => {
        this.cardService.readCard(this.selectedCard!.id);
      });
    }
  }

}
