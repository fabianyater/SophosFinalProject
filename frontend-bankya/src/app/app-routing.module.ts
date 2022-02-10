import { RegisterComponent } from './auth/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListCustomerProductsComponent } from './customers/components/list-customer-products/list-customer-products.component';

import { ListComponent } from './customers/components/listCustomer/list.component';
import { LoginComponent } from './auth/login/login.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'customers', component: ListComponent },
  { path: 'customers/:id/products', component: ListCustomerProductsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
