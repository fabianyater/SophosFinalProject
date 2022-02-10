import { RegisterComponent } from './../register/register.component';
import { LoginComponent } from './../login/login.component';
import { ListCustomerProductsComponent } from './../../../customers/components/list-customer-products/list-customer-products.component';
import { HeaderComponent } from './header.component';
import { ListComponent } from './../../../customers/components/listCustomer/list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'customers', component: ListComponent },
      {
        path: 'customers/:id/products',
        component: ListCustomerProductsComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HeaderRoutingModule {}
