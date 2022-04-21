import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Card } from 'src/app/models/card/card';
import { Tag } from 'src/app/models/tag/tag';
@Injectable({
  providedIn: 'root'
})
export class CardService {
  private baseUrl = '/api/v1';
  private selectedId$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  private selectedCard$: BehaviorSubject<Card> = new BehaviorSubject<Card>(new Card());

  constructor(private httpClient: HttpClient) {
    this.selectedId$.subscribe((id) => {
      if (id != 0) {
        this.readCard(id);
      }
    });
  }

  createCard(card: Card, sectionId: number) {
    return this.httpClient.post<Card>(`${this.baseUrl}/sections/${sectionId}/cards`, card)
  }

  readCard(id: number) {
    return this.httpClient.get<Card>(`${this.baseUrl}/cards/${id}`).subscribe((card) => {
      this.httpClient.get<Tag[]>(`${this.baseUrl}/cards/${card.id}/tags`).subscribe((tags) => {
        card.tags = tags;
        this.selectedCard$.next(card);
      });
    })
  }

  updateCard(card: Card) {
    return this.httpClient.put<Card>(`${this.baseUrl}/cards/${card.id}`, card);
  }

  deleteCard(card: Card) {
    return this.httpClient.delete(`${this.baseUrl}/cards/${card.id}`);
  }

  addTag(cardId: number, payload: object) {
    return this.httpClient.post(`${this.baseUrl}/cards/${cardId}/tags`, payload);
  }

  getCard() {
    return this.selectedCard$;
  }

  setSelectedId(id: number) {
    this.selectedId$.next(id);
  }


}
