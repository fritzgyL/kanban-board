import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../class/board';

@Injectable({
  providedIn: 'root'
})
export class BoardService {
  private baseUrl = '/api';
  constructor(private httpClient: HttpClient) { }

  getBoards(): Observable<Board[]> {
    return this.httpClient.get<Board[]>(`${this.baseUrl}/users/1/boards`)
  }

}
