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

  public addClient(client: ClientModel) {
    return this.http.post<ClientModel>(`${this.url}/clients`, client);
  }

  public getClients() {
    return this.http.get<ClientModel>(`${this.url}/clients`);
  }

  public getClientById(id: number) {
    return this.http.get<ClientModel>(`${this.url}/clients/${id}`);
  }

  public deleteClient(id: number) {
    return this.http.delete<ClientModel>(`${this.url}/clients/${id}`);
  }

  public editClient(client: ClientModel){
    return this.http.put<ClientModel>(`${this.url}/clients`, client);
  }

  

}
