import { Observable } from 'rxjs';
import { CustomerModel } from '../models/customer.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GeneralResponse } from 'src/app/models/GeneralResponse';
import { ProductModel } from 'src/app/accounts/models/product.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public addCustomer(customers: CustomerModel) {
    return this.http.post<CustomerModel>(`${this.url}/customers`, customers);
  }

  public getCustomers(): Observable<GeneralResponse<CustomerModel[]>> {
    return this.http.get<GeneralResponse<CustomerModel[]>>(
      `${this.url}/customers`
    );
  }

  public getCustomerById(
    id: number
  ): Observable<GeneralResponse<CustomerModel>> {
    return this.http.get<GeneralResponse<CustomerModel>>(
      `${this.url}/customers/${id}`
    );
  }

  public getCustomerProducts(
    id: number
  ): Observable<GeneralResponse<ProductModel[]>> {
    return this.http.get<GeneralResponse<ProductModel[]>>(
      `${this.url}/customers/${id}/products`
    );
  }

  public deleteCustomer(id: number) {
    return this.http.delete<CustomerModel>(`${this.url}/customers/${id}`);
  }

  public editCustomer(customer: CustomerModel) {
    return this.http.put<CustomerModel>(`${this.url}/customers`, customer);
  }
}
