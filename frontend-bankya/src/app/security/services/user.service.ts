import { GeneralResponse } from './../../models/GeneralResponse';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDto } from '../models/user.dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  getOneUser(username: string): Observable<GeneralResponse<UserDto>> {
    return this.http.get<GeneralResponse<UserDto>>(
      `${this.url}/users/${username}`
    );
  }

  save(user: UserDto): Observable<GeneralResponse<UserDto>> {
    return this.http.post<GeneralResponse<UserDto>>(
      `${this.url}/users/create`,
      user
    );
  }

  update(user: UserDto): Observable<GeneralResponse<UserDto>> {
    return this.http.put<GeneralResponse<UserDto>>(this.url, user);
  }

  delete(userName: string): Observable<GeneralResponse<string>> {
    return this.http.delete<GeneralResponse<string>>(this.url + '/' + userName);
  }

  login(user: UserDto): Observable<GeneralResponse<UserDto>> {
    return this.http.post<GeneralResponse<UserDto>>(
      `${this.url}/users/auth`,
      user
    );
  }
}
