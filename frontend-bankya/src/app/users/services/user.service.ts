import { UserModel } from './../models/user.model';
import { GeneralResponse } from './../../models/GeneralResponse';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public getUser(): Observable<GeneralResponse<UserModel>> {
    return this.http.get<GeneralResponse<UserModel>>(`${this.url}/users`);
  }
}
