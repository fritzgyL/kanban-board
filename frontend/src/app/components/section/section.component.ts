import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { Section } from 'src/app/models/section/section';
import { BoardService } from 'src/app/services/board/board-service.service';
import { CardService } from 'src/app/services/card/card-service.service';
import { SectionService } from 'src/app/services/section/section.service';
import { closeModal } from 'src/app/utils/modal-closer';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() section: Section = new Section();

  newCardTitle: string = '';

  constructor(private sectionService: SectionService, private cardService: CardService, private boardService: BoardService) { }

  ngOnInit(): void {
    this.getSectionCards();
  }

  private getSectionCards() {
    this.sectionService.readSectionCards(this.section.id).subscribe((cards) => {
      this.section.cards = cards.sort((a, b) => a.position - b.position);
    });
  }

  onAddCard() {
    if (this.newCardTitle != '') {
      const newCard = new Card();
      newCard.title = this.newCardTitle;
      newCard.position = this.section.cards.length;
      this.cardService.createCard(newCard, this.section.id).subscribe(data => {
        /** update the current state of the cards list of the section */
        this.section.cards.push(data);
        /**close the modal */
        this.closeModal();
        this.getSectionCards();
      })
    }
  }

  closeModal() {
    closeModal(`closeModalBtn${this.section.id}`);
  }

  onResetNewCardTitle() {
    this.newCardTitle = '';
  }

  onDeleteSection() {
    if (confirm("Are you sure you want to delete this section?")) {
      this.boardService.deleteSection(this.section.id).subscribe(() => {
        this.boardService.loadBoard();
      })
    }

  }

  drop(event: CdkDragDrop<Card[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
      this.updateCardsPositionsOnMoveItemInArray(event);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
      this.updateCardsPositionsOnTransferArrayItemInArray(event);
    }
  }

  updateCardPosition(card: Card, position: number, sectionId: number) {
    const mCard = card;
    mCard.position = position;
    if (this.section.id === sectionId) {
      this.cardService.updateCard(mCard).subscribe(() => {
        this.getSectionCards();
      });
    } else {
      this.cardService.deleteCard(card).subscribe(() => {
        this.cardService.createCard(mCard, sectionId).subscribe(() => {
          this.sectionService.readSectionCards(sectionId);
        })
      });
    }
  }

  async updateCardsPositionsByPosition(cards: Card[]) {
    if (cards.length > 0) {
      await Promise.all(
        cards.map(async (card) => {
          card.position++;
          await lastValueFrom(this.cardService.updateCard(card));
        })
      );
    }

  }
  async updateCardsPositionsByArrayIndex(cards: Card[]) {
    if (cards.length > 0) {
      await Promise.all(
        cards.map(async (card, index) => {
          card.position = index;
          await lastValueFrom(this.cardService.updateCard(card));
        })
      );
    }
  }

  async updateCardsPositionsOnMoveItemInArray(event: CdkDragDrop<Card[]>) {
    await this.updateCardsPositionsByArrayIndex(event.container.data).then(() => {
      this.getSectionCards();
    });
  }

  async updateCardsPositionsOnTransferArrayItemInArray(event: CdkDragDrop<Card[]>) {
    const card = event.item.data;
    const oldSectionId = parseInt(event.previousContainer.element.nativeElement.dataset['id']!, 10);
    const newSectionId = parseInt(event.container.element.nativeElement.dataset['id']!, 10);
    const newSectionData = event.container.data;
    const oldSectionData = event.previousContainer.data;
    const itemsToUpdate = newSectionData.slice(event.currentIndex + 1);
    await this.updateCardsPositionsByPosition(itemsToUpdate).then(() => {
      this.cardService.deleteCard(card).subscribe(async () => {
        await this.updateCardsPositionsByArrayIndex(oldSectionData).then(() => {
          card.position = event.currentIndex;
          delete card.id;
          this.cardService.createCard(card, newSectionId).subscribe(async () => {
            Promise.all(
              [await lastValueFrom(this.sectionService.readSectionCards(oldSectionId)),
              await lastValueFrom(this.sectionService.readSectionCards(newSectionId))]
            )
          })
        })
      })
    })
  }

}
