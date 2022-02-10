import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DatePipe, Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProductModel } from 'src/app/accounts/models/product.model';
import { CustomerService } from 'src/app/customers/services/customer.service';
import { CustomerModel } from '../../models/customer.model';

@Component({
  selector: 'app-list-customer-products',
  templateUrl: './list-customer-products.component.html',
  styleUrls: ['./list-customer-products.component.css'],
})
export class ListCustomerProductsComponent implements OnInit {
  public products: Array<ProductModel> | undefined = [];
  public customer: CustomerModel | undefined;
  public message: string | undefined = '';
  public messageResult: string | undefined = '';
  public errorCode: number = 0;
  public userId!: number;

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router,
    private readonly location: Location
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.customerService.getCustomerById(params['id']).subscribe((resp) => {
        if (resp.data) {
          this.customer = resp.data;
        } else {
          if (resp.errorCode == 1) {
            this.errorCode = resp.errorCode;
            this.message = resp.message;
          }
        }
      });

      this.customerService
        .getCustomerProducts(params['id'])
        .subscribe((resp) => {
          if (resp.success) {
            if (resp.errorCode == 0) {
              this.products = resp.data;
              this.messageResult = resp.messageResult;
            } else {
              if (resp.errorCode == 1 || resp.data == null) {
                this.message = resp.message;
                this.messageResult = resp.messageResult;
              }
            }
          }
        });
    });
    this.route.params.subscribe(params => {
      this.userId = params["id"]
    })

  }

  back() {
    this.router.navigateByUrl('/customers');
  }

  goTo(cId: number, id: number) {
    this.router.navigate([`/customer/${cId}/products/${id}`]);
  }
}
