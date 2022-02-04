import { Component, OnInit } from '@angular/core';
import { Board } from '../class/board/board';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { BoardService } from '../services/board-service.service';
import { Section } from '../class/section/section';
@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board = new Board()
  selectedBoardId: any;
  boardSections: Section[] = []

  constructor(private route: ActivatedRoute, private boardService: BoardService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(paramMap => {
      this.selectedBoardId = paramMap.get('id');
    });
    this.getBoard(this.selectedBoardId);
    this.getBoardSections(this.selectedBoardId);
  }

  private getBoard(boardId: any) {
    this.boardService.getBoard(boardId).subscribe(data => {
      this.board = data;
    })
  }

  private getBoardSections(boardId: any) {
    this.boardService.getBoardSections(boardId).subscribe(data => {
      this.board.sections = data;
    })
  }



}
