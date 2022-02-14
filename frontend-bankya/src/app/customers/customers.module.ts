import { AccountsModule } from './../accounts/accounts.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from './components/listCustomer/list.component';
import { AddComponent } from './components/add/add.component';
import { ListCustomerProductsComponent } from './components/list-customer-products/list-customer-products.component';
import { ListCustomersProductOperationsComponent } from './components/list-customers-product-operations/list-customers-product-operations.component';

@NgModule({
  declarations: [ListComponent, AddComponent, ListCustomerProductsComponent, ListCustomersProductOperationsComponent],
  imports: [CommonModule, ReactiveFormsModule, AccountsModule],
  exports: [ListComponent, AddComponent, ListCustomerProductsComponent],
})
export class CustomersModule {}
