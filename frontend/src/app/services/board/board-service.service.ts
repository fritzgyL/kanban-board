import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Board } from '../../models/board/board';
import { Section } from 'src/app/models/section/section';
@Injectable({
  providedIn: 'root'
})
export class BoardService {




  private baseUrl = '/api/v1';
  private boards$: BehaviorSubject<Board[]> = new BehaviorSubject<Board[]>([]);
  private selectedId$: BehaviorSubject<number | null> = new BehaviorSubject<number | null>(null);
  private selectedBoard$: BehaviorSubject<Board> = new BehaviorSubject<Board>(new Board());


  constructor(private httpClient: HttpClient) {
    this.loadBoard();
  }

  addBoard(userId: number, board: Board): Observable<Board> {
    return this.httpClient.post<Board>(`${this.baseUrl}/users/${userId}/boards`, board).pipe(
      tap(() => {
        this.loadBoards(userId);
      }));
  }

  loadBoards(userId: number) {
    this.httpClient.get<Board[]>(`${this.baseUrl}/users/${userId}/boards`).subscribe((boards) => {
      this.boards$.next(boards);
    })
  }

  loadBoard() {
    this.selectedId$.subscribe((id) => {
      if (id != null) {
        this.httpClient.get<Board>(`${this.baseUrl}/boards/${id}`).subscribe((board) => {
          this.httpClient.get<Section[]>(`${this.baseUrl}/boards/${board.id!}/sections`).subscribe((sections) => {
            const mBoard = new Board();
            mBoard.title = board.title;
            mBoard.id! = board.id!;
            mBoard.sections = sections;
            this.selectedBoard$.next(mBoard);
          });
        })
      }
    })
  }

  deleteBoard(userId: number, boardId: number) {
    this.httpClient.delete(`${this.baseUrl}/boards/${boardId}`).subscribe(() => {
      this.loadBoards(userId);
    })
  }

  getBoards() {
    return this.boards$;
  }

  getBoard() {
    return this.selectedBoard$;
  }


  setSelectedId(id: number) {
    this.selectedId$.next(id);
  }

  addSection(section: Section, boardId: number) {

    return this.httpClient.post<Section>(`${this.baseUrl}/boards/${boardId}/sections`, section);

  }

  deleteSection(sectionId: number) {
    return this.httpClient.delete(`${this.baseUrl}/sections/${sectionId}`);
  }

  updateBoard(board: Board) {
    this.httpClient.put<Board>(`${this.baseUrl}/boards/${board.id!}`, board).subscribe(() => {
      this.loadBoard();
    });
  }

}
