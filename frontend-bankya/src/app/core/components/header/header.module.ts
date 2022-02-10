import { RegisterComponent } from './../register/register.component';
import { LoginComponent } from './../login/login.component';
import { ListCustomerProductsComponent } from './../../../customers/components/list-customer-products/list-customer-products.component';
import { AddComponent } from './../../../customers/components/add/add.component';
import { HeaderComponent } from './header.component';
import { HeaderRoutingModule } from './header-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from 'src/app/customers/components/listCustomer/list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    ListComponent,
    AddComponent,
    ListCustomerProductsComponent,
  ],
  imports: [
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    HeaderRoutingModule,
  ],
  exports: [
    HeaderComponent
  ],
})
export class HeaderModule {}
