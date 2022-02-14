import { ActivatedRoute } from '@angular/router';
import { ProductService } from './../../../services/product.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css'],
})
export class DepositComponent implements OnInit {
  datePipe: DatePipe = new DatePipe('en-En');
  formattedDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  submitted: boolean = false;
  registerForm!: FormGroup;
  public errorMessage: boolean = false;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
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
}
