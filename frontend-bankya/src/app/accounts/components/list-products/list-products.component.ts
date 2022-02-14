import { Router } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';
import { ProductModel } from '../../models/product.model';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent implements OnInit {

  @Input() products: Array<ProductModel> | undefined = [];

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goTo(cId: number, id: number) {
    this.router.navigate([`/customers/${cId}/products/${id}`]);
  }

}
