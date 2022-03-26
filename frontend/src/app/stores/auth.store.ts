import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, map, Observable, shareReplay, tap } from "rxjs";
import { User } from "../models/user/user";

@Injectable({
    providedIn: 'root'
})
export class AuthStore {
    private baseUrl = '/api/v1/login';

    private subject = new BehaviorSubject<User | null>(null);

    user$: Observable<User | null> = this.subject.asObservable();
    isLoggedIn$: Observable<boolean>;
    isLoggedOut$: Observable<boolean>;
    userId: number | null = null

    constructor(private http: HttpClient) {
        this.isLoggedIn$ = this.user$.pipe(map(user => !!user))
        this.isLoggedOut$ = this.isLoggedIn$.pipe(map(loggedIn => !loggedIn));
        const user = localStorage.getItem('CONNECTED_USER');
        if (user) {
            this.subject.next(JSON.parse(user));
        }
    }

    login(user: User): Observable<User> {
        return this.http.post<User>(this.baseUrl, user).pipe(
            tap(user => {
                this.subject.next(user);
                localStorage.setItem('CONNECTED_USER', JSON.stringify(user));
            }),
            shareReplay()
        );
    }

    signup(user: User): Observable<User> {
        return this.http.post<User>(`${this.baseUrl}/signup`, user).pipe(
            tap(user => this.subject.next(user)),
            shareReplay()
        );
    }

    logout() {
        this.subject.next(null);
        localStorage.removeItem('CONNECTED_USER');
    }

}