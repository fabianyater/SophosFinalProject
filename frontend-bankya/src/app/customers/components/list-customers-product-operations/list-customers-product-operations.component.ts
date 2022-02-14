import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/customers/services/customer.service';
import { ProductModel } from 'src/app/accounts/models/product.model';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-customers-product-operations',
  templateUrl: './list-customers-product-operations.component.html',
  styleUrls: ['./list-customers-product-operations.component.css'],
})
export class ListCustomersProductOperationsComponent implements OnInit {
  public product!: ProductModel;
  public message: string | undefined = '';
  public messageResult: string | undefined = '';
  public errorCode: number = 0;
  public productId!: number;

  constructor(
    private location: Location,
    private customerService: CustomerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.customerService
        .getCustomerProductsById(params['userId'], params['productId'])
        .subscribe((resp) => {
          if (resp.success) {
            this.product = resp.data!;
          }
        });
      this.productId = params['productId'];
    });
  }

  back() {
    this.location.back();
  }
}
