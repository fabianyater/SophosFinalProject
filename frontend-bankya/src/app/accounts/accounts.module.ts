import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddProductComponent } from './components/add-product/add-product.component';

@NgModule({
  declarations: [AddProductComponent],
  imports: [CommonModule, ReactiveFormsModule],
  exports: [AddProductComponent],
})
export class AccountsModule {}
