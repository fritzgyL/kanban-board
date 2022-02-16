import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LinkPreview } from '../models/link-preview/link-preview';

@Injectable({
  providedIn: 'root'
})

export class LinkPreviewService {

  private apiKey: string = 'b53c724f65f7b0dd4eaf58132e08202c';

  constructor(private httpClient: HttpClient) {

  }

  getLinkPreview(link: string) {
    return this.httpClient.get<LinkPreview>(`https://api.linkpreview.net/?key=${this.apiKey}&q=${link}`);
  }
}
