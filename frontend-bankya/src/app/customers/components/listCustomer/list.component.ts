import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { CustomerModel } from '../../models/customer.model';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
})
export class ListComponent implements OnInit {
  public customers: CustomerModel[] | undefined = [];
  public message: string | undefined = '';
  public messageResult: string | undefined = '';
  public errorCode: number = 0;

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe(
      (resp) => {
        if (resp.errorCode == 1) {
          this.errorCode = resp.errorCode;
          this.messageResult = resp.messageResult;
          this.message = resp.message;
        } else {
          if (resp.success) {
            if (resp.errorCode === 0) {
              this.messageResult = resp.messageResult;
              this.customers = resp.data;
            }
          }
        }
      },
      (err) => {
        this.message = err.message;
      }
    );
  }
  goTo(id: number) {
    this.router.navigate([`/customers/${id}/products`]);
  }
}
