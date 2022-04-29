import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Assignation } from '../models/assignation/assignation';
import { User } from '../models/user/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = '/api/v1';

  private users$: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);

  constructor(private httpClient: HttpClient) {
    this.getUsers();
  }

  users() {
    return this.users$;
  }

  getUsers() {
    this.httpClient.get<User[]>(`${this.baseUrl}/users`).subscribe((response) => {
      this.users$.next(response);
    })
  }



}
