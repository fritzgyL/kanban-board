import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Card } from 'src/app/models/card/card';

@Injectable({
  providedIn: 'root'
})
export class SectionService {

  private baseUrl = '/api/v1';
  private selectedId$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  private selectedSectionCards: BehaviorSubject<Card[]> = new BehaviorSubject<Card[]>([])

  constructor(private httpClient: HttpClient) {
    this.selectedId$.subscribe((id) => {
      this.readSectionCards(id);
    })
  }

  readSectionCards(id: number) {
    this.httpClient.get<Card[]>(`${this.baseUrl}/sections/${id}/cards`).subscribe((cards) => {
      this.selectedSectionCards.next(cards);
    })
  }

  getSectionCards() {
    return this.selectedSectionCards;
  }

  setSelectedId(id: number) {
    this.selectedId$.next(id);
  }





}
