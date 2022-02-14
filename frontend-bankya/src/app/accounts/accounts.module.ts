import { GreaterPipe } from './../shared/pipes/greater.pipe';
import { ValePipe } from 'src/app/shared/pipes/vale.pipe';
import { StatePipePipe } from './../shared/pipes/state-pipe.pipe';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddProductComponent } from './components/add-product/add-product.component';
import { ListProductsComponent } from './components/list-products/list-products.component';
import { ListProductComponent } from './components/list-product/list-product.component';
import { ListProductOperationsComponent } from './components/list-product-operations/list-product-operations.component';
import { DepositComponent } from './components/deposit/deposit.component';

@NgModule({
  declarations: [AddProductComponent, ListProductsComponent, StatePipePipe, ListProductComponent, ValePipe, StatePipePipe, GreaterPipe, ListProductOperationsComponent, DepositComponent],
  imports: [CommonModule, ReactiveFormsModule],
  exports: [AddProductComponent, ListProductsComponent, ListProductComponent, ListProductOperationsComponent],
})
export class AccountsModule {}
