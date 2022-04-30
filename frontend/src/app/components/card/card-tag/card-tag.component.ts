import { Component, Input, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { Tag } from 'src/app/models/tag/tag';
import { CardService } from 'src/app/services/card/card-service.service';

const DEFAULT_COLOR = '#0d6efd';
@Component({
  selector: 'app-card-tag',
  templateUrl: './card-tag.component.html',
  styleUrls: ['./card-tag.component.css']
})
export class CardTagComponent implements OnInit {
  @Input() operation = '';
  @Input() tag: Tag | undefined = undefined

  pickedColor: string = '';
  labelTitle: string = '';
  selectedCard: Card | undefined = undefined;

  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.selectedCard = card;
    })
    if (this.tag !== undefined) {
      this.pickedColor = this.tag.color;
      this.labelTitle = this.tag.title;
    } else {
      this.pickedColor = DEFAULT_COLOR;
    }
  }

  onAddTag() {
    if (this.pickedColor !== '' && this.labelTitle !== '') {
      this.cardService.addTag(this.selectedCard!.id, { title: this.labelTitle, color: this.pickedColor }).subscribe(() => {
        this.cardService.readCard(this.selectedCard!.id);
      });
    }
  }

  onUpdateTag() {
    if (this.pickedColor !== '' && this.labelTitle !== '') {
      this.cardService.updateTag(this.selectedCard!.id, { title: this.labelTitle, color: this.pickedColor }).subscribe(() => {
        this.cardService.readCard(this.selectedCard!.id);
      });
    }
  }

}
