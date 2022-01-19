import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe, Location } from '@angular/common';
import { faEdit } from '@fortawesome/free-solid-svg-icons';

import { ClientModel } from 'src/app/models/client-model';
import { ClientServiceService } from 'src/app/services/client-service.service';
import { nose, ProductModel } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css'],
})
export class ClientDetailsComponent implements OnInit {
  public datePipe: DatePipe = new DatePipe('en-En');
  public stringFormat: string = 'yyyy-MM-dd hh:mm:ss';
  public formattedDate = this.datePipe.transform(new Date(), this.stringFormat);
  public products: Array<ProductModel> = [];
  public client: ClientModel | undefined;
  public submitted: boolean = false;
  public _form!: FormGroup;
  public types = [{ type: 'Cuenta Corriente' }, { type: 'Cuenta Ahorros' }];
  public icon = faEdit;

  constructor(
    public clientService: ClientServiceService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private readonly location: Location
  ) {}

  ngOnInit(): void {
    this.getClientById(this.route.snapshot.paramMap.get('id'));

    this._form = this.formBuilder.group({
      product_ammount: 0,
      product_type: ['', Validators.required],
      product_state: 'A',
      product_created_at: this.formattedDate,
      client_id: {
        client_id: Number(this.route.snapshot.paramMap.get('id')),
      },
    });

    this.productService
      .getClientProductsById(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((resp: any) => {
        this.products = resp;
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

    this.productService.addProduct(this._form.value).subscribe();
    location.href = `/client/${Number(
      this.route.snapshot.paramMap.get('id')
    )}/products`;
  }

  getClientById(id: any): void {
    this.clientService.getClientById(id).subscribe((client) => {
      this.client = client;
    });
  }

  deleteClient(clientId: number) {
    this.productService.getClientProductsById(clientId).subscribe((resp) => {
      if (resp.product_state == 'A' || resp.product_state == 'I') {
        return (location.href = `/client/${Number(
          this.route.snapshot.paramMap.get('id')
        )}`);
      } else {
        return this.clientService.deleteClient(clientId).subscribe(() => {});
      }
    });
    location.href = '/';
  }

  onReset() {
    this.submitted = false;
    this._form.reset();
  }

  back() {
    this.location.back();
  }

  goTo(id: number) {
    this.router.navigate([`/product/${id}/operations`]);
  }
}
