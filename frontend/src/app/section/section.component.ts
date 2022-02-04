import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../class/section/section';
import { BoardService } from '../services/board-service.service';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() section: Section = new Section();

  constructor(private boardService: BoardService) { }

  ngOnInit(): void {
    this.getSectionCard();
  }

  private getSectionCard() {
    this.boardService.getSectionCards(this.section.id).subscribe(data => {
      this.section.cards = data;
    });
  }

}
