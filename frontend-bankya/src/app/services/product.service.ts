import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { ProductModel } from '../accounts/models/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public addProduct(product: ProductModel) {
    return this.http.post<ProductModel>(`${this.url}/products`, product);
  }

  public getProducts() {
    return this.http.get<ProductModel>(`${this.url}/products`);
  }

  public getProductsById(id: number) {
    return this.http.get<ProductModel>(`${this.url}/products/${id}`);
  }

  public deleteProduct(id: number) {
    return this.http.delete<ProductModel>(`${this.url}/products/${id}`);
  }

  public getClientProductsById(id: number) {
    return this.http.get<ProductModel>(`${this.url}/products/client/${id}`);
  }

  public updateProductState(id: number, product: ProductModel) {
    return this.http.put<ProductModel>(
      `${this.url}/products/update-state/${id}`,
      product
    );
  }
  public cancelProduct(id: number, product: ProductModel,) {
    return this.http.put<ProductModel>(
      `${this.url}/products/cancel/${id}`,
      product
    );
  }
}
