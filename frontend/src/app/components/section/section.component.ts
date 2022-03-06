import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { Section } from 'src/app/models/section/section';
import { BoardService } from 'src/app/services/board/board-service.service';
import { CardService } from 'src/app/services/card/card-service.service';
import { SectionService } from 'src/app/services/section/section.service';
import { closeModal } from 'src/app/utils/modal-closer';

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
    this.getSectionCard();
  }

  private getSectionCard() {
    this.sectionService.readSectionCards(this.section.id).subscribe((cards) => {
      this.section.cards = cards;
    });
  }

  onAddCard() {
    if (this.newCardTitle != '') {
      const newCard = new Card();
      newCard.title = this.newCardTitle;
      this.cardService.createCard(newCard, this.section.id).subscribe(data => {
        /** update the current state of the cards list of the section */
        this.section.cards.push(data);
        /**close the modal */
        this.closeModal();
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
    if (confirm("Are you sure?")) {
      this.boardService.deleteSection(this.section.id).subscribe(() => {
        this.boardService.loadBoard();
      })
    }

  }

}
