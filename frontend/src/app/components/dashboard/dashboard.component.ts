import { Component, OnInit, ViewChild } from '@angular/core';
import { Board } from 'src/app/models/board/board';
import { BoardService } from 'src/app/services/board/board-service.service';
import { AuthStore } from 'src/app/stores/auth.store';
import { ModalComponent } from '../modal/modal.component';
import { ModalConfig } from '../modal/modal.config';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @ViewChild('modal') private modalComponent!: ModalComponent

  boards: Board[] = [];
  boardTitle: string = '';
  userId: number | undefined = undefined;

  public modalConfig: ModalConfig = {
    modalTitle: "Title",
    onDismiss: () => {
      return true
    },
    dismissButtonLabel: "Dismiss",
    onClose: () => {
      return true
    },
    closeButtonLabel: "Close"
  }

  constructor(private boardService: BoardService, private auth: AuthStore) {

  }

  ngOnInit(): void {
    this.auth.user$.subscribe((user) => {
      this.userId = user?.id;
      this.getBoards();
    })
  }

  private getBoards() {
    //const user = JSON.parse(localStorage.getItem('CONNECTED_USER')!);
    this.boardService.loadBoards(this.userId!!);
    this.boardService.getBoards().subscribe((boards) => {
      this.boards = boards;
    })
  }

  onCreateBoard() {
    if (this.boardTitle != '') {
      let board = new Board();
      board.title = this.boardTitle;
      this.boardService.addBoard(this.userId!!, board).subscribe(() => {
        this.boardTitle = '';
        this.modalComponent.close();
      })
    }
    else {
      alert('The board title should not be empty');
    }
  }




}
