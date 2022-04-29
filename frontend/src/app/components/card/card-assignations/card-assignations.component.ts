import { Component, Input, OnInit } from '@angular/core';
import { Assignation } from 'src/app/models/assignation/assignation';
import { CardService } from 'src/app/services/card/card-service.service';
import { lastValueFrom } from 'rxjs';
import { Card } from 'src/app/models/card/card';

@Component({
  selector: 'app-card-assignations',
  templateUrl: './card-assignations.component.html',
  styleUrls: ['./card-assignations.component.css']
})
export class CardAssignationsComponent implements OnInit {
  @Input() assignations: Assignation[] = [];
  selectedAssigneeFullName: string = '';
  selectedCard: Card = new Card();


  constructor(private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.getCard().subscribe((card) => {
      this.selectedCard = card;
    })
  }

  onClickOnAvatar(event: any) {
    this.selectedAssigneeFullName = event.$event.sourceId;
  }

  async onUnassignUser(assignationId: number) {
    await lastValueFrom(this.cardService.deleteAssignation(assignationId)).then(() => {
      this.cardService.readCard(this.selectedCard.id);
    });
  }

}
