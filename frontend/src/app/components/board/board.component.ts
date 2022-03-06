import { Component, OnInit } from '@angular/core';
import { Board } from '../../models/board/board';
import { ActivatedRoute } from '@angular/router';
import { Section } from 'src/app/models/section/section';
import { BoardService } from 'src/app/services/board/board-service.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board = new Board;
  boardSections: Section[] = [];
  newSectionTitle: string = '';
  addingSection: boolean = false;

  constructor(private route: ActivatedRoute, private boardService: BoardService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(paramMap => {
      let id = paramMap.get('id');
      if (id != null) {
        const res = parseInt(id);
        this.boardService.setSelectedId(res);
      }
      this.getBoard();
    });

  }

  private getBoard() {
    this.boardService.getBoard().subscribe((board) => {
      this.board = board;
    })
  }



  onCreateSection() {
    if (this.newSectionTitle != '') {
      const section = new Section();
      section.title = this.newSectionTitle;
      this.boardService.addSection(section, this.board.id).subscribe(() => {
        this.boardService.loadBoard();
        this.addingSection = false;
        this.newSectionTitle = '';
      })

    }

  }





}
