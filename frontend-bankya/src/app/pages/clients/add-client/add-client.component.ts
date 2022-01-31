import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { CustomerModel } from 'src/app/customers/models/customer.model';
import { ClientServiceService } from 'src/app/services/client-service.service';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css'],
})
export class AddClientComponent implements OnInit {
  datePipe: DatePipe = new DatePipe('en-En');
  formattedDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd hh:mm:ss');
  arrayClient: Array<CustomerModel> = [];
  submitted: boolean = false;
  registerForm!: FormGroup;
  client!: CustomerModel;
  public documents = [
    { type: 'Cédula de ciudadanía' },
    { type: 'Cédula Extranjería' },
    { type: 'Pasaporte' },
  ];

  constructor(
    private formBuilder: FormBuilder,
    //private clientService: ClientServiceService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      client_name: ['', Validators.required],
      client_lastname: ['', Validators.required],
      client_email: ['', Validators.required, Validators.email],
      client_document_type: ['', Validators.required],
      client_document_number: ['', Validators.required],
      client_birthday: ['', Validators.required],
      client_created_at: this.formattedDate,
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
    //this.clientService.addClient(this.registerForm.value).subscribe();
    location.href = '/';
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
