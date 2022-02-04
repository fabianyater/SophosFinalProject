import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListCustomerProductsComponent } from './customers/components/list-customer-products/list-customer-products.component';

import { ListComponent } from './customers/components/listCustomer/list.component';

const routes: Routes = [
  { path: '', redirectTo: 'customers', pathMatch: 'full' },
  { path: 'customers', component: ListComponent },
  { path: 'customers/:id/products', component: ListCustomerProductsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
