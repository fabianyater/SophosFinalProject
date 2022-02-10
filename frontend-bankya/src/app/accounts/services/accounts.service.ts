import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ProductModel } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) { }

  public addProduct(product: ProductModel) {
    return this.http.post<ProductModel>(`${this.url}/products`, product);
  }

}
