import { Component, OnInit } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { LinkPreview } from 'src/app/models/link-preview/link-preview';
import { CardService } from 'src/app/services/card/card-service.service';
import { LinkPreviewService } from 'src/app/services/link-preview.service';

@Component({
  selector: 'app-card-link-section',
  templateUrl: './card-link-section.component.html',
  styleUrls: ['./card-link-section.component.css']
})
export class CardLinkSectionComponent implements OnInit {

  card: Card = new Card;
  linkPreview: LinkPreview = new LinkPreview();

  constructor(private cardService: CardService, private linkPreviewService: LinkPreviewService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      if (card != null) {
        this.card = card;
        this.getLinkPreview();
      }
    })
  }

  private getLinkPreview() {
    this.linkPreviewService.getLinkPreview(this.card.url).subscribe((linkPreview) => {
      this.linkPreview = linkPreview;
    })
  }

  onNavigateToLink() {
    location.href = this.linkPreview.url;
  }

  onRemoveCardLink() {
    const updatedCard = this.card;
    updatedCard.url = '';
    this.cardService.updateCard(updatedCard.id!, updatedCard).subscribe(() => {
      this.cardService.readCard(updatedCard.id!);
    })
  }

}
