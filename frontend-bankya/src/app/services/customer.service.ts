import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CustomerModel } from '../customers/models/customer.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public addClient(client: CustomerModel) {
    return this.http.post<CustomerModel>(`${this.url}/clients`, client);
  }

  public getClients() {
    return this.http.get<CustomerModel>(`${this.url}/clients`);
  }

  public getClientById(id: number) {
    return this.http.get<CustomerModel>(`${this.url}/clients/${id}`);
  }

  public deleteClient(id: number) {
    return this.http.delete<CustomerModel>(`${this.url}/clients/${id}`);
  }

  public editClient(client: CustomerModel){
    return this.http.put<CustomerModel>(`${this.url}/clients`, client);
  }



}
