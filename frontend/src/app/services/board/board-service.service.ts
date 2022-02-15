import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Board } from '../../models/board/board';
import { Section } from 'src/app/models/section/section';
@Injectable({
  providedIn: 'root'
})
export class BoardService {


  private baseUrl = '/api/v1';
  private boards$: BehaviorSubject<Board[]> = new BehaviorSubject<Board[]>([]);
  //private selectedBoardSections$: BehaviorSubject<Section[]> = new BehaviorSubject<Section[]>([]);
  private selectedId$: BehaviorSubject<number | null> = new BehaviorSubject<number | null>(null);
  private selectedBoard$: BehaviorSubject<Board> = new BehaviorSubject<Board>(new Board());


  constructor(private httpClient: HttpClient) {
    this.loadBoards();
    this.selectedId$.subscribe((id) => {
      if (id != null) {
        this.loadBoard(id);
      }
    })
  }

  private loadBoards() {
    this.httpClient.get<Board[]>(`${this.baseUrl}/users/1/boards`).subscribe((boards) => {
      this.boards$.next(boards);
    })
  }

  private loadBoard(id: number) {
    this.httpClient.get<Board>(`${this.baseUrl}/boards/${id}`).subscribe((board) => {
      this.httpClient.get<Section[]>(`${this.baseUrl}/boards/${board.id}/sections`).subscribe((sections) => {
        const mBoard = new Board();
        mBoard.title = board.title;
        mBoard.id = board.id;
        mBoard.sections = sections;
        this.selectedBoard$.next(mBoard);
      });
    })
  }

  getBoards() {
    return this.boards$;
  }

  getBoard() {
    return this.selectedBoard$;
  }

  /*
  getBoardsSections() {
    return this.selectedBoardSections$;
  }**/

  setSelectedId(id: number) {
    this.selectedId$.next(id);
  }

}
