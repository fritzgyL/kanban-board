import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { Board } from '../../models/board/board';
import { ActivatedRoute } from '@angular/router';
import { Section } from 'src/app/models/section/section';
import { BoardService } from 'src/app/services/board/board-service.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {
  @ViewChild('updateInput') updateInput!: ElementRef;
  @ViewChild('updateBtn') updateBtn!: ElementRef;

  board: Board = new Board;
  boardSections: Section[] = [];
  newSectionTitle: string = '';
  addingSection: boolean = false;
  isUpdating: boolean = false;
  newBoardTitle: string = '';

  constructor(private route: ActivatedRoute, private boardService: BoardService, private renderer: Renderer2) {
    this.renderer.listen('window', 'click', (e: Event) => {
      const eventTarget = e.target as HTMLTextAreaElement;
      if (eventTarget.id !== 'updateBtn') {
        if (this.updateInput.nativeElement != e.target) {
          this.updateBoardTitle();
        }
      }
    });
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
      this.newBoardTitle = this.board.title;
    })
  }

  onCreateSection() {
    if (this.newSectionTitle != '') {
      const section = new Section();
      section.title = this.newSectionTitle;
      this.boardService.addSection(section, this.board.id!).subscribe(() => {
        this.boardService.loadBoard();
        this.addingSection = false;
        this.newSectionTitle = '';
      })
    }
  }

  updateBoardTitle() {
    if (this.newBoardTitle !== this.board.title) {
      this.board.title = this.newBoardTitle;
      this.boardService.updateBoard(this.board);
    }
    this.isUpdating = false;

  }






}
