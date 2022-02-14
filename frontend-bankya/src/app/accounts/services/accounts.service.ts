import { OperationModel } from './../models/operations.model';
import { Observable } from 'rxjs';
import { GeneralResponse } from './../../models/GeneralResponse';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ProductModel } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class AccountsService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public addProduct(product: ProductModel) {
    return this.http.post<ProductModel>(`${this.url}/products`, product);
  }

  public getProductOperations(
    id: number
  ): Observable<GeneralResponse<OperationModel[]>> {
    return this.http.get<GeneralResponse<OperationModel[]>>(
      `${this.url}/products/${id}/operations`
    );
  }

  public deposit(
    productId: number,
    operation: OperationModel
  ): Observable<GeneralResponse<OperationModel>> {
    return this.http.post<GeneralResponse<OperationModel>>(
      `${this.url}/products/${productId}/deposit`,
      operation
    );
  }
}
