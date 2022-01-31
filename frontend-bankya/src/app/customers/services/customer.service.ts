import { CustomerModel } from '../models/customer.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public addCustomer(customers: CustomerModel) {
    return this.http.post<CustomerModel>(`${this.url}/customers`, customers);
  }

  public getCustomers() {
    return this.http.get<CustomerModel>(`${this.url}/customers`);
  }

  public getCustomerById(id: number) {
    return this.http.get<CustomerModel>(`${this.url}/customers/${id}`);
  }

  public deleteCustomer(id: number) {
    return this.http.delete<CustomerModel>(`${this.url}/customers/${id}`);
  }

  public editCustomer(customer: CustomerModel) {
    return this.http.put<CustomerModel>(`${this.url}/customers`, customer);
  }
}
