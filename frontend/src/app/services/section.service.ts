import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../class/board/board';
import { Card } from '../class/card/card';

@Injectable({
  providedIn: 'root'
})
export class SectionService {

  private baseUrl = '/api/v1';
  constructor(private httpClient: HttpClient) { }

  getSectionCards(id: number) {
    return this.httpClient.get<Card[]>(`${this.baseUrl}/sections/${id}/cards`)
  }

  addCard(card: Card, sectionId: number): Observable<Card> {
    return this.httpClient.post<Card>(`${this.baseUrl}/sections/${sectionId}/cards`, card)
  }
}
