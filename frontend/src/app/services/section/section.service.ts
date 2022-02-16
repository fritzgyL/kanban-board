import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Card } from 'src/app/models/card/card';

@Injectable({
  providedIn: 'root'
})
export class SectionService {

  private baseUrl = '/api/v1';
  constructor(private httpClient: HttpClient) { }

  getSectionCards(id: number) {
    return this.httpClient.get<Card[]>(`${this.baseUrl}/sections/${id}/cards`)
  }


}
