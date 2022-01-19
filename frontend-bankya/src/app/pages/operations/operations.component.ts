import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OperationModel } from 'src/app/models/operations-model';
import { ProductModel } from 'src/app/models/product.model';
import { OperationsService } from 'src/app/services/operations.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css'],
})
export class OperationsComponent implements OnInit {
  public operations: Array<OperationModel> = [];
  public products: ProductModel | undefined;

  constructor(
    private readonly location: Location,
    private operationService: OperationsService,
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getProductById(this.route.snapshot.paramMap.get('id'));
    this.operationService
      .getProductOperationsById(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((resp: any) => {
        this.operations = resp;
        console.log('Operaciones:', this.operations);
      });
  }

  getProductById(id: any): void {
    this.productService.getProductsById(id).subscribe((resp) => {
      this.products = resp;
    });
  }

  updateState(id: any, product: ProductModel) {
    this.productService.updateProductState(id, product).subscribe((resp) => {
      console.log('Actualizó gg', resp);
    });
    window.location.reload();
    /* console.log('ID: ', id, ' Cuerpo: ', product); */
  }

  back() {
    this.location.back();
  }
}
