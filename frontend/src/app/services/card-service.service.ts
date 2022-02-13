import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../class/board/board';
import { Card } from '../class/card/card';
@Injectable({
  providedIn: 'root'
})
export class CardService {

  private baseUrl = '/api/v1';
  constructor(private httpClient: HttpClient) { }


}
