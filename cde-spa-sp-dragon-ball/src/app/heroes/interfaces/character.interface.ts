// Generated by https://quicktype.io

import { Hero } from "./hero.interface";

export interface CharacterResponse {
  code: string;
  message: string;
  data: Hero[];
}

export interface CharacterData {
  id: string;
  name: string;
  gender: string;
  ki: string;
  image?: string;
}

export interface SpecifcCharacterResponse {
  code: string;
  message: string;
  data: Hero;
}

export interface GenericResponse {
  code: string;
  message: string;
}