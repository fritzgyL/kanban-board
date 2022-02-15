import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from 'src/app/models/board/board';
import { BoardService } from 'src/app/services/board/board-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  boards: Board[] = [];

  constructor(private boardService: BoardService) { }

  ngOnInit(): void {
    this.getBoards();
  }

  private getBoards() {
    this.boardService.getBoards().subscribe((boards) => {
      this.boards = boards;
    });
  }


}
