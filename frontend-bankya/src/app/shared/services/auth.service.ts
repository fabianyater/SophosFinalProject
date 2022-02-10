import { GeneralResponse } from './../../models/GeneralResponse';
import { Observable } from 'rxjs';
import { UserModel } from './../../users/models/user.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  public TOKEN_KEY: string | undefined = 'auth-token';
  public USER_KEY: string | undefined = 'auth-user';
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public login(user: UserModel): Observable<GeneralResponse<UserModel>> {
    return this.http.post(`${this.url}/users/auth`, user);
  }

  public register(user: UserModel): Observable<GeneralResponse<UserModel>> {
    return this.http.post(`${this.url}/users/create`, user);
  }

  public logOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(this.TOKEN_KEY!);
    window.sessionStorage.setItem(this.TOKEN_KEY!, token);
  }

  public getToken() {
    return sessionStorage.getItem(this.TOKEN_KEY!);
  }

  public saveUser(user: UserModel) {
    window.sessionStorage.removeItem(this.USER_KEY!);
    window.sessionStorage.setItem(this.USER_KEY!, JSON.stringify(user));
  }

  public getUser() {
    return sessionStorage.getItem(this.USER_KEY!);
  }
}
