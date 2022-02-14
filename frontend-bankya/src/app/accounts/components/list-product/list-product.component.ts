import { Component, OnInit, Input } from '@angular/core';
import { ProductModel } from '../../models/product.model';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css'],
})
export class ListProductComponent implements OnInit {
  @Input() product!: ProductModel;

  public errorMessage: boolean = false;

  constructor() {}

  ngOnInit(): void {
    console.log("BALANCE: ", this.product.product_type);

  }
}
