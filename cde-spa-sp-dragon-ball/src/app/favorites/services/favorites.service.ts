import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { v4 as uuidv4 } from 'uuid';

import { Hero } from 'src/app/heroes/interfaces/hero.interface';
import { environments } from 'src/environments/environments';
import { CharacterResponse } from 'src/app/heroes/interfaces/character.interface';
import { AddFavoriteModel } from 'src/app/heroes/interfaces/add-favorite.model';
import { GenericResponse } from 'src/app/heroes/interfaces/character.interface';


@Injectable({ providedIn: 'root' })
export class FavoritesService {

  private baseUrl: string = environments.baseUrl;


  constructor(private http: HttpClient) { }

  getFavoritesFromUser(userName: string): Observable<CharacterResponse> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.get<CharacterResponse>(`${this.baseUrl}/support/dragon-ball/v1/favorites/${userName}`, { headers });
  }

  getCharacters(): Observable<CharacterResponse> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.get<CharacterResponse>(`${this.baseUrl}/support/dragon-ball/v1/characters`, { headers });
  }

  addFavorite(addFavoriteModel: AddFavoriteModel): Observable<GenericResponse> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.post<GenericResponse>(`${this.baseUrl}/support/dragon-ball/v1/favorites`, addFavoriteModel, { headers });
  }

  deletefavorite(userName: string, idCharacter: string): Observable<CharacterResponse> {
    const headers = new HttpHeaders()
      .set('x-guid', uuidv4())
      .set('x-process', 'dragonball')
      .set('x-flow', 'angular');

    return this.http.delete<CharacterResponse>(`${this.baseUrl}/support/dragon-ball/v1/favorites/${userName}/${idCharacter}`, { headers });
  }

}
