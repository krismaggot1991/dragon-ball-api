import { Injectable } from '@angular/core';
import { CanMatch, CanActivate, Router, Route, UrlSegment, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, tap, map } from 'rxjs';
import { AuthService } from '../services/auth.service';


@Injectable({ providedIn: 'root' })
export class PublicGuard implements CanMatch, CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router,
  ) { }

  private checkAuthStatus(): boolean {

    const isAuthenticated = this.authService.checkAuthentication();
    console.log('Authenticated:', isAuthenticated);
    if (!isAuthenticated) {
      this.router.navigate(['./auth/login']);
    }
    return isAuthenticated;

  }


  canMatch(route: Route, segments: UrlSegment[]): boolean {
    // console.log('Can Match');
    // console.log({ route, segments })
    return true;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // console.log('Can Activate');
    // console.log({ route, state })

    return true;
  }

}
