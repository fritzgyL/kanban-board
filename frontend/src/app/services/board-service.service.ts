import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../class/board/board';
import { Card } from '../class/card/card';
import { Section } from '../class/section/section';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  private baseUrl = '/api';
  constructor(private httpClient: HttpClient) { }

  getBoards(): Observable<Board[]> {
    return this.httpClient.get<Board[]>(`${this.baseUrl}/users/1/boards`)
  }

  getBoard(id: number): Observable<Board> {
    return this.httpClient.get<Board>(`${this.baseUrl}/boards/${id}`)
  }

  getBoardSections(id: number): Observable<Section[]> {
    return this.httpClient.get<Section[]>(`${this.baseUrl}/boards/${id}/sections`)
  }

  getSectionCards(id: number) {
    return this.httpClient.get<Card[]>(`${this.baseUrl}/sections/${id}/cards`)
  }

}
