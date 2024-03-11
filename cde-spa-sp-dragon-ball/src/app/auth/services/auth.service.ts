import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, tap, of, map, catchError } from 'rxjs';

import { environments } from '../../../environments/environments';
import { User } from '../interfaces/user.interface';
import { LoginModel } from 'src/app/heroes/components/user.model';
import { v4 as uuidv4 } from 'uuid';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private baseUrl = environments.baseUrl;
  private user?: User;

  constructor(private http: HttpClient) { }

  get currentUser(): User | undefined {
    if (!this.user) return undefined;
    return structuredClone(this.user);
  }

  login(loginModel: LoginModel): Observable<User> {

    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.post<User>(`${this.baseUrl}/support/dragon-ball/v1/login`, loginModel, { headers })
      .pipe(
        tap(user => this.user = user),
        tap(user => localStorage.setItem('token', user.data.userName)),
      );
  }

  checkAuthentication(): boolean {


    if (!localStorage.getItem('token')) return false;

    const token = localStorage.getItem('token');

    console.info('entra');
    console.info(token);

    return true;
  }


  logout() {
    this.user = undefined;
    localStorage.clear();
  }



}
