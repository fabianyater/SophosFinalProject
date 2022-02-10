import { Location } from '@angular/common';
import { Component, ErrorHandler, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { OperationModel } from 'src/app/accounts/models/operations.model';
import { ProductModel } from 'src/app/accounts/models/product.model';
import { OperationsService } from 'src/app/services/operations.service';
import { ProductService } from 'src/app/services/product.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css'],
})
export class OperationsComponent implements OnInit {
  public operations: Array<OperationModel> = [];
  public products: ProductModel | undefined;
  datePipe: DatePipe = new DatePipe('en-En');
  formattedDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  submitted: boolean = false;
  registerForm!: FormGroup;
  public operationType = [
    { type: 'Deposit' },
    { type: 'Withdraw' },
    { type: 'Transfer' },
  ];
  public errorMessage: boolean = false;
  public errorMessage2: boolean = false;

  constructor(
    private readonly location: Location,
    private operationService: OperationsService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getProductById(this.route.snapshot.paramMap.get('id'));

    this.operationService
      .getProductOperationsById(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((resp: any) => {
        this.operations = resp;
      });

    this.registerForm = this.formBuilder.group({
      account_number: [''],
      operation_date: this.formattedDate,
      operation_description: [''],
      operation_type: ['', Validators.required],
      operation_value: ['', Validators.required],
      product_id: {
        product_id: Number(this.route.snapshot.paramMap.get('id')),
      },
    });
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    /* const checkData = this.operationService
      .addOperation(this.registerForm.value)
      .pipe(
        map((data) => {

          if (data === null) {
            return window.alert("Saldo insuficiente");
          }
          return data;
        })
      );

    checkData.subscribe((resp) => {
      window.location.reload();
    }); */

    this.operationService.addOperation(this.registerForm.value).subscribe(
      (resp) => {
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        if (error.status == 400 || error.status === 500) {
          this.errorMessage = true;
        }
      }
    );
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }

  getProductById(id: any): void {
    this.productService.getProductsById(id).subscribe((resp) => {
      this.products = resp;
    });
  }

  updateState(id: any, product: ProductModel) {
    this.productService.updateProductState(id, product).subscribe((resp) => {
    });
    window.location.reload();
  }

  cancelProduct(id: any, product: ProductModel) {
    this.productService.cancelProduct(id, product).subscribe(
      (resp) => {
        window.location.href = '/';
      },
      (error: HttpErrorResponse) => {
        if (error.status == 400 || error.status === 500) {
          this.errorMessage2 = true;
        }
      }
    );
  }

  back() {
    this.location.back();
  }
}
