import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { v4 as uuidv4 } from 'uuid';

import { Hero } from '../interfaces/hero.interface';
import { environments } from '../../../environments/environments';
import { CharacterResponse } from '../interfaces/character.interface';
import { SpecifcCharacterResponse } from '../interfaces/character.interface';
import { GenericResponse } from '../interfaces/character.interface';
import { UserModel } from '../interfaces/user.model';

@Injectable({ providedIn: 'root' })
export class HeroesService {

  private baseUrl: string = environments.baseUrl;


  constructor(private http: HttpClient) { }

  getCharacters(): Observable<CharacterResponse> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.get<CharacterResponse>(`${this.baseUrl}/support/dragon-ball/v1/characters`, { headers });
  }

  getHeroById(id: string): Observable<SpecifcCharacterResponse | undefined> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.get<SpecifcCharacterResponse>(`${this.baseUrl}/support/dragon-ball/v1/characters/${id}`, { headers })
      .pipe(
        catchError(error => of(undefined))
      );
  }

  getSuggestions(query: string): Observable<Hero[]> {
    return this.http.get<Hero[]>(`${this.baseUrl}/heroes?q=${query}&_limit=6`);
  }


  addHero(hero: Hero): Observable<Hero> {
    return this.http.post<Hero>(`${this.baseUrl}/heroes`, hero);
  }

  registerUser(user: UserModel): Observable<GenericResponse> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.post<GenericResponse>(`${this.baseUrl}/support/dragon-ball/v1/user`, user, { headers });
  }

  updateHero(hero: Hero): Observable<Hero> {
    if (!hero.id) throw Error('Hero id is required');

    return this.http.patch<Hero>(`${this.baseUrl}/heroes/${hero.id}`, hero);
  }

  deleteHeroById(id: string): Observable<boolean> {

    return this.http.delete(`${this.baseUrl}/heroes/${id}`)
      .pipe(
        map(resp => true),
        catchError(err => of(false)),
      );
  }


}
