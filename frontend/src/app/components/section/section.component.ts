import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/models/card/card';
import { Section } from 'src/app/models/section/section';
import { SectionService } from 'src/app/services/section/section.service';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() section: Section = new Section();

  newCardTitle: string = '';

  constructor(private sectionService: SectionService) { }

  ngOnInit(): void {
    this.getSectionCard();
  }

  private getSectionCard() {
    this.sectionService.getSectionCards(this.section.id).subscribe(data => {
      this.section.cards = data;
    });
  }

  onAddCard() {
    if (this.newCardTitle != '') {
      const newCard = new Card();
      newCard.title = this.newCardTitle;
      this.sectionService.addCard(newCard, this.section.id).subscribe(data => {
        /** update the current state of the cards list of the section */
        this.section.cards.push(data);
        /**close the modal */
        this.closeModal();
      })
    }
  }

  closeModal() {
    const element = window.document.getElementById(`closeModalBtn${this.section.id}`);
    if (element !== null) {
      element.click();
    }
  }

  onResetNewCardTitle() {
    this.newCardTitle = '';
  }

}
