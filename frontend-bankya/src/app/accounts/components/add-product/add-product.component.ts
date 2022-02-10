import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccountsService } from 'src/app/accounts/services/accounts.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit {
  @Input() userId!: number;

  public datePipe: DatePipe = new DatePipe('en-En');
  public stringFormat: string = 'yyyy-MM-dd hh:mm:ss';
  public formattedDate = this.datePipe.transform(new Date(), this.stringFormat);
  public submitted: boolean = false;
  public _form!: FormGroup;
  public types = [{ type: 'Checking Account' }, { type: 'Savings Account' }];

  constructor(
    private accountService: AccountsService,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    this._form = this.formBuilder.group({
      product_ammount: 0,
      product_type: ['', Validators.required],
      product_state: 'A',
      product_created_at: this.formattedDate,
      customer_id: {
        customer_id: this.userId,
      },
    });
  }

  get form() {
    return this._form.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this._form.invalid) {
      return;
    }
    this.accountService.addProduct(this._form.value).subscribe();
    window.location.reload();
  }
}
