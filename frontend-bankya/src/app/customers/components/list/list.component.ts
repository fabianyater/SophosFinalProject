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
  public customers: Array<CustomerModel> = [];

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe((resp: any) => {
      this.customers = resp.data;
      console.log(resp.data)
    });
  }
  goTo(id: number) {
    this.router.navigate([`/customers/${id}/products`]);
  }
}
