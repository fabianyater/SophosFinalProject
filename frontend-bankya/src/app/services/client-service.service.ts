import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ClientModel } from '../models/client-model';

@Injectable({
  providedIn: 'root',
})
export class ClientServiceService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public getClients() {
    return this.http.get<ClientModel>(this.url);
  }
}
