import { OperationModel } from './../../models/operations.model';
import { Component, Input, OnInit } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';

@Component({
  selector: 'app-list-product-operations',
  templateUrl: './list-product-operations.component.html',
  styleUrls: ['./list-product-operations.component.css'],
})
export class ListProductOperationsComponent implements OnInit {
  @Input() productId!: number;
  public operations: Array<OperationModel> | undefined = [];
  public message: string = '';
  public messageResult: string = '';
  public errorCode!: number;

  constructor(private productService: AccountsService) {}

  ngOnInit(): void {
    this.productService
      .getProductOperations(this.productId)
      .subscribe((resp) => {
        if (resp.success) {
          if (resp.errorCode === 0) {
            this.operations = resp.data;
            this.messageResult = resp.messageResult!;
          } else {
            this.messageResult = resp.messageResult!;
          }
        } else {
          this.message = resp.message!;
        }
      });
  }
}
