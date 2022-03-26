import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { map, Observable } from "rxjs";
import { AuthStore } from "../stores/auth.store";

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private auth: AuthStore, private router: Router) {

    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
        return this.auth.isLoggedIn$
            .pipe(
                map(loggedIn => loggedIn ? true : this.router.parseUrl('/login'))
            )
    }
}