import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { OperationModel } from '../models/operations-model';

@Injectable({
  providedIn: 'root',
})
export class OperationsService {
  private url: string = environment.apiUlr;

  constructor(private http: HttpClient) {}

  public addOperation(operation: OperationModel) {
    return this.http.post<OperationModel>(`${this.url}/operations`, operation);
  }

  public getProductOperationsById(id: number) {
    return this.http.get<OperationModel>(`${this.url}/operations/product/${id}`);
  }

  public updateOperationState(Operation: number) {
    return this.http.put<number>(`${this.url}/operations/edit`, Operation);
  }
}
