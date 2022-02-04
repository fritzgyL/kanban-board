import { Component, OnInit } from '@angular/core';
import { Board } from '../class/board';
import { BoardService } from '../services/board-service.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  boards: Board[] = []

  constructor(private boardService: BoardService) { }

  ngOnInit(): void {
    this.getBoards();
    console.log(this.boards);
  }

  private getBoards() {
    this.boardService.getBoards().subscribe(data => {
      this.boards = data;
    });
  }

}
