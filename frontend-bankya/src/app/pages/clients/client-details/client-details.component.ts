import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { ClientModel } from 'src/app/models/client-model';
import { ClientServiceService } from 'src/app/services/client-service.service';
import { ProductModel } from 'src/app/models/product.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css'],
})
export class ClientDetailsComponent implements OnInit {
  public client: ClientModel | undefined;
  _form!: FormGroup;
  submitted: boolean = false;
  arrayProduct: Array<ProductModel> = [];

  constructor(
    public clientService: ClientServiceService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private readonly location: Location
  ) {}

  ngOnInit(): void {
    this.getClientById(this.route.snapshot.paramMap.get('id'));
    this._form = this.formBuilder.group({
      product_ammount: ['', Validators.required],
      product_number: ['', Validators.required],
      product_type: ['', Validators.required],
      product_state: 'A',
      client_id: {
        client_id: Number(this.route.snapshot.paramMap.get('id')),
      },
    });
    console.log(Number(this.route.snapshot.paramMap.get('id')));
  }

  get form() {
    return this._form.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this._form.invalid) {
      return;
    }

    console.log(JSON.stringify(this._form.value));
    this.productService.addProduct(this._form.value).subscribe((resp) => {
      console.log('producto: ', resp);
    });
    //location.href = `/clients/${this._form.value['id']}`;
  }

  onReset() {
    this.submitted = false;
    this._form.reset();
  }

  back() {
    this.location.back();
  }

  getClientById(id: any): void {
    this.clientService.getClientById(id).subscribe(
      (client) => {
        this.client = client;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
